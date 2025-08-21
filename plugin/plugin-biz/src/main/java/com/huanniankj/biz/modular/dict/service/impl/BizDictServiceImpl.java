package com.huanniankj.biz.modular.dict.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fhs.trans.service.impl.DictionaryTransService;
import com.huanniankj.biz.modular.dict.entity.BizDict;
import com.huanniankj.biz.modular.dict.enums.BizDictCategoryEnum;
import com.huanniankj.biz.modular.dict.mapper.BizDictMapper;
import com.huanniankj.biz.modular.dict.param.BizDictEditParam;
import com.huanniankj.biz.modular.dict.param.BizDictPageParam;
import com.huanniankj.biz.modular.dict.service.BizDictService;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.page.CommonPageRequest;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 字典服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class BizDictServiceImpl extends ServiceImpl<BizDictMapper, BizDict>
        implements BizDictService, InitializingBean {

    private static final String ROOT_PARENT_ID = "0";

    @Resource
    private DictionaryTransService dictionaryTransService;

    @Override
    public Page<BizDict> page(BizDictPageParam bizDictPageParam) {
        QueryWrapper<BizDict> queryWrapper = new QueryWrapper<BizDict>().checkSqlInjection();
        // 查询部分字段
        queryWrapper.lambda().select(BizDict::getId, BizDict::getParentId, BizDict::getCategory, BizDict::getDictLabel,
                BizDict::getDictValue, BizDict::getSortCode).eq(BizDict::getCategory, BizDictCategoryEnum.BIZ.getValue());
        if (ObjectUtil.isNotEmpty(bizDictPageParam.getParentId())) {
            queryWrapper.lambda().and(q ->
                    q.eq(BizDict::getParentId, bizDictPageParam.getParentId())
                            .or().eq(BizDict::getId, bizDictPageParam.getParentId()));
        }
        if (ObjectUtil.isNotEmpty(bizDictPageParam.getSearchKey())) {
            queryWrapper.lambda().like(BizDict::getDictLabel, bizDictPageParam.getSearchKey());
        }
        if (ObjectUtil.isAllNotEmpty(bizDictPageParam.getSortField(), bizDictPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(bizDictPageParam.getSortOrder());
            queryWrapper.orderBy(true, bizDictPageParam.getSortOrder()
                            .equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(bizDictPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(BizDict::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Override
    public List<Tree<String>> tree() {
        LambdaQueryWrapper<BizDict> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(BizDict::getCategory, BizDictCategoryEnum.BIZ.getValue()).orderByAsc(BizDict::getSortCode);
        List<BizDict> bizDictList = this.list(lambdaQueryWrapper);
        List<TreeNode<String>> treeNodeList = bizDictList.stream().map(bizDict ->
                        new TreeNode<>(bizDict.getId(), bizDict.getParentId(),
                                bizDict.getDictLabel(), bizDict.getSortCode()).setExtra(JSONUtil.parseObj(bizDict)))
                .collect(Collectors.toList());
        return TreeUtil.build(treeNodeList, "0");
    }

    @Override
    public List<Tree<String>> treeAll() {
        List<BizDict> bizDictList = this.list();
        List<TreeNode<String>> treeNodeList = bizDictList.stream().map(bizDict ->
                        new TreeNode<>(bizDict.getId(), bizDict.getParentId(),
                                bizDict.getDictLabel(), bizDict.getSortCode()).setExtra(JSONUtil.parseObj(bizDict)))
                .collect(Collectors.toList());
        return TreeUtil.build(treeNodeList, "0");
    }

    @Override
    public void edit(BizDictEditParam bizDictEditParam) {
        BizDict bizDict = this.queryEntity(bizDictEditParam.getId());
        checkParam(bizDictEditParam);
        BeanUtil.copyProperties(bizDictEditParam, bizDict);
        this.updateById(bizDict);
        refreshTransCache();
    }

    private void checkParam(BizDictEditParam bizDictEditParam) {
        boolean hasSameDictLabel = this.count(new LambdaQueryWrapper<BizDict>()
                .eq(BizDict::getCategory, BizDictCategoryEnum.BIZ.getValue())
                .eq(BizDict::getDictLabel, bizDictEditParam.getDictLabel())
                .ne(BizDict::getId, bizDictEditParam.getId())) > 0;
        if (hasSameDictLabel) {
            throw new CommonException("存在重复的字典文字，名称为：{}", bizDictEditParam.getDictLabel());
        }
    }

    @Override
    public BizDict queryEntity(String id) {
        BizDict bizDict = this.getById(id);
        if (ObjectUtil.isEmpty(bizDict)) {
            throw new CommonException("字典不存在，id值为：{}", id);
        }
        return bizDict;
    }

    @Override
    public void afterPropertiesSet() {
        refreshTransCache();
    }

    private void refreshTransCache() {
        // 异步不阻塞主线程，不会 增加启动用时
        CompletableFuture.supplyAsync(() -> {
            // 使用redis能解决共享问题，但是性能没有直接取缓存的好。
            dictionaryTransService.makeUseRedis();
            List<BizDict> bizDictList = super.list(new LambdaQueryWrapper<>());
            // 非root级别的字典根据ParentId分组
            Map<String, List<BizDict>> bizDictGroupByPIDMap = bizDictList.stream().filter(dict -> !ROOT_PARENT_ID
                    .equals(dict.getParentId())).collect(Collectors.groupingBy(BizDict::getParentId));
            Map<String, String> parentDictIdValMap = bizDictList.stream().filter(dict -> ROOT_PARENT_ID
                    .equals(dict.getParentId())).collect(Collectors.toMap(BizDict::getId, BizDict::getDictValue));
            for (String parentId : parentDictIdValMap.keySet()) {
                if (bizDictGroupByPIDMap.containsKey(parentId)) {
                    dictionaryTransService.refreshCache(parentDictIdValMap.get(parentId),
                            bizDictGroupByPIDMap.get(parentId).stream()
                                    .collect(Collectors.toMap(BizDict::getDictValue, BizDict::getDictLabel)));
                }
            }
            return null;
        });
    }
}
