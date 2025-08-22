package com.huanniankj.uba.modular.rule.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.page.CommonPageRequest;
import com.huanniankj.uba.modular.rule.entity.Rule;
import com.huanniankj.uba.modular.rule.enums.RuleCategoryEnum;
import com.huanniankj.uba.modular.rule.mapper.RuleMapper;
import com.huanniankj.uba.modular.rule.param.RuleAddParam;
import com.huanniankj.uba.modular.rule.param.RuleEditParam;
import com.huanniankj.uba.modular.rule.param.RuleIdParam;
import com.huanniankj.uba.modular.rule.param.RuleListParam;
import com.huanniankj.uba.modular.rule.param.RulePageParam;
import com.huanniankj.uba.modular.rule.service.RuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据清洗规则服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class RuleServiceImpl extends ServiceImpl<RuleMapper, Rule> implements RuleService {

    @Override
    public Page<Rule> page(RulePageParam rulePageParam) {
        QueryWrapper<Rule> queryWrapper = new QueryWrapper<Rule>().checkSqlInjection();
        // 查询部分字段
        queryWrapper.lambda().select(Rule::getId, Rule::getCategory, Rule::getName, Rule::getField, Rule::getSortCode);
        if (ObjectUtil.isNotEmpty(rulePageParam.getCategory())) {
            queryWrapper.lambda().eq(Rule::getCategory, rulePageParam.getCategory());
        }
        if (ObjectUtil.isNotEmpty(rulePageParam.getSearchKey())) {
            queryWrapper.lambda().like(Rule::getName, rulePageParam.getSearchKey());
        }
        if (ObjectUtil.isAllNotEmpty(rulePageParam.getSortField(), rulePageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(rulePageParam.getSortOrder());
            queryWrapper.orderBy(true, rulePageParam.getSortOrder()
                            .equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(rulePageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(Rule::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Override
    public List<Rule> list(RuleListParam ruleListParam) {
        LambdaQueryWrapper<Rule> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtil.isNotEmpty(ruleListParam.getCategory())) {
            lambdaQueryWrapper.eq(Rule::getCategory, ruleListParam.getCategory());
        }
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public void add(RuleAddParam ruleAddParam) {
        checkParam(ruleAddParam);
        Rule devRule = BeanUtil.toBean(ruleAddParam, Rule.class);
        devRule.setCode(RandomUtil.randomString(10));
        this.save(devRule);
    }

    private void checkParam(RuleAddParam ruleAddParam) {
        RuleCategoryEnum.validate(ruleAddParam.getCategory());
        boolean hasSameRuleName = this.count(new LambdaQueryWrapper<Rule>()
                .eq(Rule::getCategory, ruleAddParam.getCategory())
                .eq(Rule::getName, ruleAddParam.getName())) > 0;
        if (hasSameRuleName) {
            throw new CommonException("存在重复的数据清洗规则名称，名称为：{}", ruleAddParam.getName());
        }
    }

    @Override
    public void edit(RuleEditParam ruleEditParam) {
        Rule rule = this.queryEntity(ruleEditParam.getId());
        checkParam(ruleEditParam);
        BeanUtil.copyProperties(ruleEditParam, rule);
        this.updateById(rule);
    }

    private void checkParam(RuleEditParam ruleEditParam) {
        RuleCategoryEnum.validate(ruleEditParam.getCategory());
        boolean hasSameRuleName = this.count(new LambdaQueryWrapper<Rule>()
                .eq(Rule::getCategory, ruleEditParam.getCategory())
                .eq(Rule::getName, ruleEditParam.getName())
                .ne(Rule::getId, ruleEditParam.getId())) > 0;
        if (hasSameRuleName) {
            throw new CommonException("存在重复的数据清洗规则名称，名称为：{}", ruleEditParam.getName());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<RuleIdParam> ruleIdParams) {
        List<String> ruleIdList = CollStreamUtil.toList(ruleIdParams, RuleIdParam::getId);
        if (ObjectUtil.isNotEmpty(ruleIdList)) {
            // 删除
            this.removeByIds(ruleIdList);
        }
    }

    @Override
    public Rule detail(RuleIdParam ruleIdParam) {
        return this.queryEntity(ruleIdParam.getId());
    }

    @Override
    public Rule queryEntity(String id) {
        Rule rule = this.getById(id);
        if (ObjectUtil.isEmpty(rule)) {
            throw new CommonException("数据清洗规则不存在，id值为：{}", id);
        }
        return rule;
    }

}
