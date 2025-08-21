package com.huanniankj.sys.modular.relation.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.huanniankj.sys.modular.relation.entity.SysRelation;
import com.huanniankj.sys.modular.relation.mapper.SysRelationMapper;
import com.huanniankj.sys.modular.relation.service.SysRelationService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 关系服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class SysRelationServiceImpl extends ServiceImpl<SysRelationMapper, SysRelation> implements SysRelationService {

    @Transactional(rollbackFor = Exception.class)
    public void saveRelation(String objectId, String targetId, String category, String extJson, boolean clear) {
        // 是否需要先删除关系
        if (clear) {
            this.remove(new LambdaQueryWrapper<SysRelation>().eq(SysRelation::getObjectId, objectId)
                    .eq(SysRelation::getCategory, category));
        }
        SysRelation sysRelation = new SysRelation();
        sysRelation.setObjectId(objectId);
        sysRelation.setTargetId(targetId);
        sysRelation.setCategory(category);
        sysRelation.setExtJson(extJson);
        this.save(sysRelation);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveRelationBatch(String objectId, List<String> targetIdList, String category,
                                  List<String> extJsonList, boolean clear) {
        // 是否需要先删除关系
        if (clear) {
            this.remove(new LambdaQueryWrapper<SysRelation>().eq(SysRelation::getObjectId, objectId)
                    .eq(SysRelation::getCategory, category));
        }
        List<SysRelation> sysRelationList = CollectionUtil.newArrayList();
        for (int i = 0; i < targetIdList.size(); i++) {
            SysRelation sysRelation = new SysRelation();
            sysRelation.setObjectId(objectId);
            sysRelation.setTargetId(targetIdList.get(i));
            sysRelation.setCategory(category);
            if (ObjectUtil.isNotEmpty(extJsonList)) {
                sysRelation.setExtJson(extJsonList.get(i));
            }
            sysRelationList.add(sysRelation);
        }
        if (ObjectUtil.isNotEmpty(sysRelationList)) {
            this.saveBatch(sysRelationList);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRelationWithAppend(String objectId, String targetId, String category) {
        this.saveRelation(objectId, targetId, category, null, false);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRelationWithAppend(String objectId, String targetId, String category, String extJson) {
        this.saveRelation(objectId, targetId, category, extJson, false);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRelationBatchWithAppend(String objectId, List<String> targetIdList, String category) {
        this.saveRelationBatch(objectId, targetIdList, category, null, false);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRelationBatchWithAppend(String objectId, List<String> targetIdList, String category,
                                            List<String> extJsonList) {
        this.saveRelationBatch(objectId, targetIdList, category, extJsonList, false);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRelationWithClear(String objectId, String targetId, String category) {
        this.saveRelation(objectId, targetId, category, null, true);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRelationWithClear(String objectId, String targetId, String category, String extJson) {
        this.saveRelation(objectId, targetId, category, extJson, true);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRelationBatchWithClear(String objectId, List<String> targetIdList, String category) {
        this.saveRelationBatch(objectId, targetIdList, category, null, true);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRelationBatchWithClear(String objectId, List<String> targetIdList, String category,
                                           List<String> extJsonList) {
        this.saveRelationBatch(objectId, targetIdList, category, extJsonList, true);
    }

    @Override
    public List<SysRelation> getRelationListByObjectId(String objectId) {
        return this.getRelationListByObjectIdAndCategory(objectId, null);
    }

    @Override
    public List<SysRelation> getRelationListByObjectIdList(List<String> objectIdList) {
        return this.getRelationListByObjectIdListAndCategory(objectIdList, null);
    }

    @Override
    public List<SysRelation> getRelationListByObjectIdAndCategory(String objectId, String category) {
        LambdaQueryWrapper<SysRelation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysRelation::getObjectId, objectId);
        if (ObjectUtil.isNotEmpty(category)) {
            lambdaQueryWrapper.eq(SysRelation::getCategory, category);
        }
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public List<SysRelation> getRelationListByObjectIdListAndCategory(List<String> objectIdList, String category) {
        LambdaQueryWrapper<SysRelation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SysRelation::getObjectId, objectIdList);
        if (ObjectUtil.isNotEmpty(category)) {
            lambdaQueryWrapper.eq(SysRelation::getCategory, category);
        }
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public List<SysRelation> getRelationListByTargetId(String targetId) {
        return this.getRelationListByTargetIdAndCategory(targetId, null);
    }

    @Override
    public List<SysRelation> getRelationListByTargetIdList(List<String> targetIdList) {
        return this.getRelationListByTargetIdListAndCategory(targetIdList, null);
    }

    @Override
    public List<SysRelation> getRelationListByTargetIdAndCategory(String targetId, String category) {
        LambdaQueryWrapper<SysRelation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysRelation::getTargetId, targetId);
        if (ObjectUtil.isNotEmpty(category)) {
            lambdaQueryWrapper.eq(SysRelation::getCategory, category);
        }
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public List<SysRelation> getRelationListByTargetIdListAndCategory(List<String> targetIdList, String category) {
        LambdaQueryWrapper<SysRelation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SysRelation::getTargetId, targetIdList);
        if (ObjectUtil.isNotEmpty(category)) {
            lambdaQueryWrapper.eq(SysRelation::getCategory, category);
        }
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public List<String> getRelationTargetIdListByObjectId(String objectId) {
        return this.getRelationTargetIdListByObjectIdAndCategory(objectId, null);
    }

    @Override
    public List<String> getRelationTargetIdListByObjectIdList(List<String> objectIdList) {
        return this.getRelationTargetIdListByObjectIdListAndCategory(objectIdList, null);
    }

    @Override
    public List<String> getRelationTargetIdListByObjectIdAndCategory(String objectId, String category) {
        return this.getRelationListByObjectIdAndCategory(objectId, category).stream()
                .map(SysRelation::getTargetId).collect(Collectors.toList());
    }

    @Override
    public List<String> getRelationTargetIdListByObjectIdListAndCategory(List<String> objectIdList, String category) {
        return this.getRelationListByObjectIdListAndCategory(objectIdList, category).stream()
                .map(SysRelation::getTargetId).collect(Collectors.toList());
    }

    @Override
    public List<String> getRelationObjectIdListByTargetId(String targetId) {
        return this.getRelationObjectIdListByTargetIdAndCategory(targetId, null);
    }

    @Override
    public List<String> getRelationObjectIdListByTargetIdList(List<String> targetIdList) {
        return this.getRelationObjectIdListByTargetIdListAndCategory(targetIdList, null);
    }

    @Override
    public List<String> getRelationObjectIdListByTargetIdAndCategory(String targetId, String category) {
        return this.getRelationListByTargetIdAndCategory(targetId, category).stream()
                .map(SysRelation::getObjectId).collect(Collectors.toList());
    }

    @Override
    public List<String> getRelationObjectIdListByTargetIdListAndCategory(List<String> targetIdList, String category) {
        return this.getRelationListByTargetIdListAndCategory(targetIdList, category).stream()
                .map(SysRelation::getObjectId).collect(Collectors.toList());
    }
}
