package com.huanniankj.uba.modular.dict.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.lang.tree.parser.DefaultNodeParser;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fhs.trans.service.impl.DictionaryTransService;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.page.CommonPageRequest;
import com.huanniankj.uba.modular.dict.entity.Dict;
import com.huanniankj.uba.modular.dict.enums.DictCategoryEnum;
import com.huanniankj.uba.modular.dict.mapper.DictMapper;
import com.huanniankj.uba.modular.dict.param.DictAddParam;
import com.huanniankj.uba.modular.dict.param.DictEditParam;
import com.huanniankj.uba.modular.dict.param.DictIdParam;
import com.huanniankj.uba.modular.dict.param.DictListParam;
import com.huanniankj.uba.modular.dict.param.DictPageParam;
import com.huanniankj.uba.modular.dict.param.DictTreeParam;
import com.huanniankj.uba.modular.dict.service.DictService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 数据处理字典服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService, InitializingBean {

    private static final String ROOT_PARENT_ID = "0";

    @Resource
    private DictionaryTransService dictionaryTransService;

    @Override
    public Page<Dict> page(DictPageParam devDictPageParam) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<Dict>().checkSqlInjection();
        // 查询部分字段
        queryWrapper.lambda().select(Dict::getId, Dict::getParentId, Dict::getCategory, Dict::getDictLabel,
                Dict::getDictValue, Dict::getSortCode);
        if (ObjectUtil.isNotEmpty(devDictPageParam.getParentId())) {
            queryWrapper.lambda().and(q ->
                    q.eq(Dict::getParentId, devDictPageParam.getParentId())
                            .or().eq(Dict::getId, devDictPageParam.getParentId()));
        }
        if (ObjectUtil.isNotEmpty(devDictPageParam.getCategory())) {
            queryWrapper.lambda().eq(Dict::getCategory, devDictPageParam.getCategory());
        }
        if (ObjectUtil.isNotEmpty(devDictPageParam.getSearchKey())) {
            queryWrapper.lambda().like(Dict::getDictLabel, devDictPageParam.getSearchKey());
        }
        if (ObjectUtil.isAllNotEmpty(devDictPageParam.getSortField(), devDictPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(devDictPageParam.getSortOrder());
            queryWrapper.orderBy(true, devDictPageParam.getSortOrder()
                            .equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(devDictPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(Dict::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Override
    public List<Dict> list(DictListParam devDictListParam) {
        LambdaQueryWrapper<Dict> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtil.isNotEmpty(devDictListParam.getParentId())) {
            lambdaQueryWrapper.eq(Dict::getParentId, devDictListParam.getParentId());
        }
        if (ObjectUtil.isNotEmpty(devDictListParam.getCategory())) {
            lambdaQueryWrapper.eq(Dict::getCategory, devDictListParam.getCategory());
        }
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public List<Tree<String>> tree(DictTreeParam devDictTreeParam) {
        LambdaQueryWrapper<Dict> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 查询部分字段
        lambdaQueryWrapper.select(Dict::getId, Dict::getParentId, Dict::getCategory, Dict::getDictLabel,
                Dict::getDictValue, Dict::getSortCode);
        lambdaQueryWrapper.orderByAsc(Dict::getSortCode);
        if (ObjectUtil.isNotEmpty(devDictTreeParam.getCategory())) {
            lambdaQueryWrapper.eq(Dict::getCategory, devDictTreeParam.getCategory());
        }
        List<Dict> devDictList = this.list(lambdaQueryWrapper);
        List<TreeNode<String>> treeNodeList = devDictList.stream().map(devDict ->
                        new TreeNode<>(devDict.getId(), devDict.getParentId(),
                                devDict.getDictLabel(), devDict.getSortCode()).setExtra(JSONUtil.parseObj(devDict)))
                .collect(Collectors.toList());
        // 精简冗余字段(sortCode、weight字段合并)
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("sortCode");
        return TreeUtil.build(treeNodeList, "0", treeNodeConfig, new DefaultNodeParser<>());
    }

    @Override
    public void add(DictAddParam devDictAddParam) {
        checkParam(devDictAddParam);
        Dict devDict = BeanUtil.toBean(devDictAddParam, Dict.class);
        devDict.setCode(RandomUtil.randomString(10));
        this.save(devDict);
        refreshTransCache();
    }

    private void checkParam(DictAddParam devDictAddParam) {
        DictCategoryEnum.validate(devDictAddParam.getCategory());
        boolean hasSameDictLabel = this.count(new LambdaQueryWrapper<Dict>()
                .eq(Dict::getParentId, devDictAddParam.getParentId())
                .eq(Dict::getCategory, devDictAddParam.getCategory())
                .eq(Dict::getDictLabel, devDictAddParam.getDictLabel())) > 0;
        if (hasSameDictLabel) {
            throw new CommonException("存在重复的字典文字，名称为：{}", devDictAddParam.getDictLabel());
        }

        boolean hasSameDictValue = this.count(new LambdaQueryWrapper<Dict>()
                .eq(Dict::getParentId, devDictAddParam.getParentId())
                .eq(Dict::getDictValue, devDictAddParam.getDictValue())) > 0;
        if (hasSameDictValue) {
            throw new CommonException("存在重复的字典值，名称为：{}", devDictAddParam.getDictValue());
        }
    }

    @Override
    public void edit(DictEditParam devDictEditParam) {
        Dict devDict = this.queryEntity(devDictEditParam.getId());
        checkParam(devDictEditParam);
        BeanUtil.copyProperties(devDictEditParam, devDict);
        this.updateById(devDict);
        refreshTransCache();
    }

    private void checkParam(DictEditParam devDictEditParam) {
        DictCategoryEnum.validate(devDictEditParam.getCategory());
        boolean hasSameDictLabel = this.count(new LambdaQueryWrapper<Dict>()
                .eq(Dict::getParentId, devDictEditParam.getParentId())
                .eq(Dict::getCategory, devDictEditParam.getCategory())
                .eq(Dict::getDictLabel, devDictEditParam.getDictLabel())
                .ne(Dict::getId, devDictEditParam.getId())) > 0;
        if (hasSameDictLabel) {
            throw new CommonException("存在重复的字典文字，名称为：{}", devDictEditParam.getDictLabel());
        }

        boolean hasSameDictValue = this.count(new LambdaQueryWrapper<Dict>()
                .eq(Dict::getParentId, devDictEditParam.getParentId())
                .eq(Dict::getDictValue, devDictEditParam.getDictValue())
                .ne(Dict::getId, devDictEditParam.getId())) > 0;
        if (hasSameDictValue) {
            throw new CommonException("存在重复的字典值，名称为：{}", devDictEditParam.getDictValue());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<DictIdParam> devDictIdParamList) {
        List<String> devDictIdList = CollStreamUtil.toList(devDictIdParamList, DictIdParam::getId);
        if (ObjectUtil.isNotEmpty(devDictIdList)) {
            // 删除
            this.removeByIds(devDictIdList);
        }
    }

    @Override
    public Dict detail(DictIdParam devDictIdParam) {
        return this.queryEntity(devDictIdParam.getId());
    }

    @Override
    public Dict queryEntity(String id) {
        Dict devDict = this.getById(id);
        if (ObjectUtil.isEmpty(devDict)) {
            throw new CommonException("字典不存在，id值为：{}", id);
        }
        return devDict;
    }

    @Override
    public void afterPropertiesSet() {
        refreshTransCache();
    }

    /**
     * 刷新字典缓存
     */
    private void refreshTransCache() {
        // 异步不阻塞主线程，不会 增加启动用时
        CompletableFuture.supplyAsync(() -> {
            // 使用redis能解决共享问题，但是性能没有直接取缓存的好。
            dictionaryTransService.makeUseRedis();
            List<Dict> devDictList = super.list(new LambdaQueryWrapper<>());
            // 非root级别的字典根据ParentId分组
            Map<String, List<Dict>> devDictGroupByPIDMap = devDictList.stream()
                    .filter(dict -> !ROOT_PARENT_ID
                            .equals(dict.getParentId())).collect(Collectors.groupingBy(Dict::getParentId));
            Map<String, String> parentDictIdValMap = devDictList.stream().filter(dict -> ROOT_PARENT_ID
                    .equals(dict.getParentId())).collect(Collectors.toMap(Dict::getId, Dict::getDictValue));
            for (String parentId : parentDictIdValMap.keySet()) {
                if (devDictGroupByPIDMap.containsKey(parentId)) {
                    dictionaryTransService.refreshCache(parentDictIdValMap.get(parentId),
                            devDictGroupByPIDMap.get(parentId).stream()
                                    .collect(Collectors.toMap(Dict::getDictValue, Dict::getDictLabel)));
                }
            }
            return null;
        });
    }

    @Override
    public String getDictLabel(String typeCode, String value) {
        List<Dict> devDictList = this.list(new LambdaQueryWrapper<Dict>().eq(Dict::getDictValue, typeCode));
        if (ObjectUtil.isNotEmpty(devDictList) && devDictList.size() == 1) {
            Dict devDictClone = devDictList.get(0);
            Dict devDict = this.getOne(new LambdaQueryWrapper<Dict>()
                    .eq(Dict::getParentId, devDictClone.getId())
                    .eq(Dict::getDictValue, value));
            if (ObjectUtil.isNotEmpty(devDict)) {
                return devDict.getDictLabel();
            } else {
                return null;
            }
        }
        return null;
    }
}
