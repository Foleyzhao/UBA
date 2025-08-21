package com.huanniankj.uba.modular.tag.service.impl;

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
import com.huanniankj.uba.modular.tag.entity.Tag;
import com.huanniankj.uba.modular.tag.enums.TagCategoryEnum;
import com.huanniankj.uba.modular.tag.enums.TagLevelEnum;
import com.huanniankj.uba.modular.tag.enums.TagSourceEnum;
import com.huanniankj.uba.modular.tag.enums.TagTypeEnum;
import com.huanniankj.uba.modular.tag.mapper.TagMapper;
import com.huanniankj.uba.modular.tag.param.TagAddParam;
import com.huanniankj.uba.modular.tag.param.TagEditParam;
import com.huanniankj.uba.modular.tag.param.TagIdParam;
import com.huanniankj.uba.modular.tag.param.TagListParam;
import com.huanniankj.uba.modular.tag.param.TagPageParam;
import com.huanniankj.uba.modular.tag.param.TagTreeParam;
import com.huanniankj.uba.modular.tag.service.TagService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 标签服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService, InitializingBean {

    private static final String ROOT_PARENT_ID = "0";

    @Resource
    private DictionaryTransService dictionaryTransService;

    @Override
    public Page<Tag> page(TagPageParam tagPageParam) {
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<Tag>().checkSqlInjection();
        // 查询部分字段
        queryWrapper.lambda().select(Tag::getId, Tag::getParentId, Tag::getCategory, Tag::getLevel, Tag::getType,
                Tag::getTagSource, Tag::getTagName, Tag::getTagDesc, Tag::getSortCode);
        if (ObjectUtil.isNotEmpty(tagPageParam.getCategory())) {
            queryWrapper.lambda().eq(Tag::getCategory, tagPageParam.getCategory());
        }
        if (ObjectUtil.isNotEmpty(tagPageParam.getParentId())) {
            queryWrapper.lambda().and(q -> q.eq(Tag::getParentId, tagPageParam.getParentId()));
        }
        if (ObjectUtil.isNotEmpty(tagPageParam.getSearchKey())) {
            queryWrapper.lambda().like(Tag::getTagName, tagPageParam.getSearchKey());
        }
        if (ObjectUtil.isAllNotEmpty(tagPageParam.getSortField(), tagPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(tagPageParam.getSortOrder());
            queryWrapper.orderBy(true, tagPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(tagPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(Tag::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Override
    public List<Tag> list(TagListParam tagListParam) {
        LambdaQueryWrapper<Tag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtil.isNotEmpty(tagListParam.getParentId())) {
            lambdaQueryWrapper.eq(Tag::getParentId, tagListParam.getParentId());
        }
        if (ObjectUtil.isNotEmpty(tagListParam.getCategory())) {
            lambdaQueryWrapper.eq(Tag::getCategory, tagListParam.getCategory());
        }
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public List<Tree<String>> tree(TagTreeParam tagTreeParam) {
        LambdaQueryWrapper<Tag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 查询部分字段
        lambdaQueryWrapper.select(Tag::getId, Tag::getParentId, Tag::getCategory, Tag::getLevel, Tag::getType,
                Tag::getTagSource, Tag::getTagName, Tag::getTagDesc, Tag::getSortCode);
        lambdaQueryWrapper.orderByAsc(Tag::getSortCode);
        if (ObjectUtil.isNotEmpty(tagTreeParam.getCategory())) {
            lambdaQueryWrapper.eq(Tag::getCategory, tagTreeParam.getCategory());
        }
        List<Tag> devTagList = this.list(lambdaQueryWrapper);
        List<TreeNode<String>> treeNodeList = devTagList.stream().map(
                devTag -> new TreeNode<>(devTag.getId(), devTag.getParentId(), devTag.getTagName(),
                        devTag.getSortCode()).setExtra(JSONUtil.parseObj(devTag))).collect(Collectors.toList());
        // 精简冗余字段(sortCode、weight字段合并)
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("sortCode");
        return TreeUtil.build(treeNodeList, "0", treeNodeConfig, new DefaultNodeParser<>());
    }

    @Override
    public void add(TagAddParam tagAddParam) {
        checkParam(tagAddParam);
        Tag devTag = BeanUtil.toBean(tagAddParam, Tag.class);
        devTag.setCode(RandomUtil.randomString(10));
        this.save(devTag);
        refreshTransCache();
    }

    private void checkParam(TagAddParam tagAddParam) {
        TagCategoryEnum.validate(tagAddParam.getCategory());
        TagLevelEnum.validate(tagAddParam.getLevel());
        TagTypeEnum.validate(tagAddParam.getType());
        TagSourceEnum.validate(tagAddParam.getTagSource());
        boolean hasSameTagName = this.count(new LambdaQueryWrapper<Tag>().
                eq(Tag::getParentId, tagAddParam.getParentId())
                .eq(Tag::getCategory, tagAddParam.getCategory())
                .eq(Tag::getTagName, tagAddParam.getTagName())) > 0;
        if (hasSameTagName) {
            throw new CommonException("存在重复的标签名称，名称为：{}", tagAddParam.getTagName());
        }
    }

    @Override
    public void edit(TagEditParam tagEditParam) {
        Tag devTag = this.queryEntity(tagEditParam.getId());
        checkParam(tagEditParam);
        BeanUtil.copyProperties(tagEditParam, devTag);
        this.updateById(devTag);
        refreshTransCache();
    }

    private void checkParam(TagEditParam tagEditParam) {
        TagCategoryEnum.validate(tagEditParam.getCategory());
        TagLevelEnum.validate(tagEditParam.getLevel());
        TagTypeEnum.validate(tagEditParam.getType());
        TagSourceEnum.validate(tagEditParam.getTagSource());
        boolean hasSameTagName = this.count(new LambdaQueryWrapper<Tag>()
                .eq(Tag::getParentId, tagEditParam.getParentId())
                .eq(Tag::getCategory, tagEditParam.getCategory())
                .eq(Tag::getTagName, tagEditParam.getTagName())
                .ne(Tag::getId, tagEditParam.getId())) > 0;
        if (hasSameTagName) {
            throw new CommonException("存在重复的标签名称，名称为：{}", tagEditParam.getTagName());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<TagIdParam> tagIdParamList) {
        List<String> tagIdList = CollStreamUtil.toList(tagIdParamList, TagIdParam::getId);
        if (ObjectUtil.isNotEmpty(tagIdList)) {
            // 删除
            this.removeByIds(tagIdList);
        }
    }

    @Override
    public Tag detail(TagIdParam tagIdParam) {
        return this.queryEntity(tagIdParam.getId());
    }

    @Override
    public Tag queryEntity(String id) {
        Tag devTag = this.getById(id);
        if (ObjectUtil.isEmpty(devTag)) {
            throw new CommonException("标签不存在，id值为：{}", id);
        }
        return devTag;
    }

    @Override
    public void afterPropertiesSet() {
        refreshTransCache();
    }

    /**
     * 刷新标签缓存
     */
    private void refreshTransCache() {
        // 异步不阻塞主线程，不会增加启动用时
        CompletableFuture.supplyAsync(() -> {
            // 使用redis能解决共享问题，但是性能没有直接取缓存的好。
            dictionaryTransService.makeUseRedis();
            List<Tag> devTagList = super.list(new LambdaQueryWrapper<>());
            // 非root级别的标签根据ParentId分组
            // 非root级别的标签根据ParentId分组y
            Map<String, List<Tag>> devTagGroupByPIDMap = devTagList.stream()
                    .filter(tag -> !ROOT_PARENT_ID.equals(tag.getParentId()))
                    .collect(Collectors.groupingBy(Tag::getParentId));
            Map<String, String> parentTagIdValMap = devTagList.stream()
                    .filter(tag -> ROOT_PARENT_ID.equals(tag.getParentId()))
                    .collect(Collectors.toMap(Tag::getId, Tag::getTagName));
            for (String parentId : parentTagIdValMap.keySet()) {
                if (devTagGroupByPIDMap.containsKey(parentId)) {
                    dictionaryTransService.refreshCache(parentTagIdValMap.get(parentId),
                            devTagGroupByPIDMap.get(parentId).stream()
                                    .collect(Collectors.toMap(Tag::getTagDesc, Tag::getTagName)));
                }
            }
            return null;
        });
    }

}
