package com.huanniankj.uba.modular.rule.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.page.CommonPageRequest;
import com.huanniankj.uba.modular.rule.entity.RuleItem;
import com.huanniankj.uba.modular.rule.enums.RuleItemStatusEnum;
import com.huanniankj.uba.modular.rule.mapper.RuleItemMapper;
import com.huanniankj.uba.modular.rule.param.RuleItemAddParam;
import com.huanniankj.uba.modular.rule.param.RuleItemEditParam;
import com.huanniankj.uba.modular.rule.param.RuleItemIdParam;
import com.huanniankj.uba.modular.rule.param.RuleItemListParam;
import com.huanniankj.uba.modular.rule.param.RuleItemPageParam;
import com.huanniankj.uba.modular.rule.service.RuleItemService;
import com.huanniankj.uba.modular.rule.service.RuleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据清洗规则项服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class RuleItemServiceImpl extends ServiceImpl<RuleItemMapper, RuleItem> implements RuleItemService {

    @Resource
    private RuleService ruleService;

    @Override
    public Page<RuleItem> page(RuleItemPageParam ruleItemPageParam) {
        QueryWrapper<RuleItem> queryWrapper = new QueryWrapper<RuleItem>().checkSqlInjection();
        // 查询部分字段
        queryWrapper.lambda().select(RuleItem::getId, RuleItem::getRuleId, RuleItem::getStatus, RuleItem::getContent,
                RuleItem::getResult, RuleItem::getSortCode);
        if (ObjectUtil.isNotEmpty(ruleItemPageParam.getRuleId())) {
            queryWrapper.lambda().eq(RuleItem::getRuleId, ruleItemPageParam.getRuleId());
        }
        if (ObjectUtil.isAllNotEmpty(ruleItemPageParam.getSortField(), ruleItemPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(ruleItemPageParam.getSortOrder());
            queryWrapper.orderBy(true, ruleItemPageParam.getSortOrder()
                            .equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(ruleItemPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(RuleItem::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Override
    public List<RuleItem> list(RuleItemListParam ruleItemListParam) {
        LambdaQueryWrapper<RuleItem> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtil.isNotEmpty(ruleItemListParam.getRuleId())) {
            lambdaQueryWrapper.eq(RuleItem::getRuleId, ruleItemListParam.getRuleId());
        }
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public void add(RuleItemAddParam ruleItemAddParam) {
        checkParam(ruleItemAddParam);
        RuleItem ruleItem = BeanUtil.toBean(ruleItemAddParam, RuleItem.class);
        ruleItem.setCode(RandomUtil.randomString(10));
        this.save(ruleItem);
    }

    private void checkParam(RuleItemAddParam ruleItemAddParam) {
        boolean hasRule = ruleService.getById(ruleItemAddParam.getRuleId()) != null;
        if (!hasRule) {
            throw new CommonException("不存在数据清洗规则ID，ID为：{}", ruleItemAddParam.getRuleId());
        }
    }

    @Override
    public void edit(RuleItemEditParam ruleItemEditParam) {
        RuleItem ruleItem = this.queryEntity(ruleItemEditParam.getId());
        checkParam(ruleItemEditParam);
        BeanUtil.copyProperties(ruleItemEditParam, ruleItem);
        this.updateById(ruleItem);
    }

    private void checkParam(RuleItemEditParam ruleItemEditParam) {
        boolean hasRule = ruleService.getById(ruleItemEditParam.getRuleId()) != null;
        if (!hasRule) {
            throw new CommonException("不存在数据清洗规则ID，ID为：{}", ruleItemEditParam.getRuleId());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<RuleItemIdParam> ruleItemIdParams) {
        List<String> ruleItemIdList = CollStreamUtil.toList(ruleItemIdParams, RuleItemIdParam::getId);
        if (ObjectUtil.isNotEmpty(ruleItemIdList)) {
            // 删除
            this.removeByIds(ruleItemIdList);
        }
    }

    @Override
    public RuleItem detail(RuleItemIdParam ruleItemIdParam) {
        return this.queryEntity(ruleItemIdParam.getId());
    }

    @Override
    public RuleItem queryEntity(String id) {
        RuleItem ruleItem = this.getById(id);
        if (ObjectUtil.isEmpty(ruleItem)) {
            throw new CommonException("数据清洗规则项不存在，id值为：{}", id);
        }
        return ruleItem;
    }

    @Override
    public void disable(RuleItemIdParam ruleItemIdParam) {
        this.update(new LambdaUpdateWrapper<RuleItem>().eq(RuleItem::getId,
                ruleItemIdParam.getId()).set(RuleItem::getStatus, RuleItemStatusEnum.DISABLE.getValue()));
    }

    @Override
    public void enable(RuleItemIdParam ruleItemIdParam) {
        this.update(new LambdaUpdateWrapper<RuleItem>().eq(RuleItem::getId,
                ruleItemIdParam.getId()).set(RuleItem::getStatus, RuleItemStatusEnum.ENABLE.getValue()));
    }

}
