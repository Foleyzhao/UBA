package com.huanniankj.dev.modular.relation.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.dev.modular.relation.entity.DevRelation;
import com.huanniankj.dev.modular.relation.mapper.DevRelationMapper;
import com.huanniankj.dev.modular.relation.service.DevRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 关系服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class DevRelationServiceImpl extends ServiceImpl<DevRelationMapper, DevRelation> implements DevRelationService {

    @Transactional(rollbackFor = Exception.class)
    public void saveRelation(String objectId, String targetId, String category, String extJson, boolean clear) {
        // 是否需要先删除关系
        if (clear) {
            this.remove(new LambdaQueryWrapper<DevRelation>().eq(DevRelation::getObjectId, objectId)
                    .eq(DevRelation::getCategory, category));
        }
        DevRelation devRelation = new DevRelation();
        devRelation.setObjectId(objectId);
        devRelation.setTargetId(targetId);
        devRelation.setCategory(category);
        devRelation.setExtJson(extJson);
        this.save(devRelation);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveRelationBatch(String objectId, List<String> targetIdList, String category,
                                  List<String> extJsonList, boolean clear) {
        // 是否需要先删除关系
        if (clear) {
            this.remove(new LambdaQueryWrapper<DevRelation>().eq(DevRelation::getObjectId, objectId)
                    .eq(DevRelation::getCategory, category));
        }
        List<DevRelation> devRelationList = CollectionUtil.newArrayList();
        for (int i = 0; i < targetIdList.size(); i++) {
            DevRelation devRelation = new DevRelation();
            devRelation.setObjectId(objectId);
            devRelation.setTargetId(targetIdList.get(i));
            devRelation.setCategory(category);
            if (ObjectUtil.isNotEmpty(extJsonList)) {
                devRelation.setExtJson(extJsonList.get(i));
            }
            devRelationList.add(devRelation);
        }
        if (ObjectUtil.isNotEmpty(devRelationList)) {
            this.saveBatch(devRelationList);
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
    public List<DevRelation> getRelationListByObjectId(String objectId) {
        return this.getRelationListByObjectIdAndCategory(objectId, null);
    }

    @Override
    public List<DevRelation> getRelationListByObjectIdList(List<String> objectIdList) {
        return this.getRelationListByObjectIdListAndCategory(objectIdList, null);
    }

    @Override
    public List<DevRelation> getRelationListByObjectIdAndCategory(String objectId, String category) {
        LambdaQueryWrapper<DevRelation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DevRelation::getObjectId, objectId);
        if (ObjectUtil.isNotEmpty(category)) {
            lambdaQueryWrapper.eq(DevRelation::getCategory, category);
        }
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public List<DevRelation> getRelationListByObjectIdListAndCategory(List<String> objectIdList, String category) {
        LambdaQueryWrapper<DevRelation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(DevRelation::getObjectId, objectIdList);
        if (ObjectUtil.isNotEmpty(category)) {
            lambdaQueryWrapper.eq(DevRelation::getCategory, category);
        }
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public List<DevRelation> getRelationListByTargetId(String targetId) {
        return this.getRelationListByTargetIdAndCategory(targetId, null);
    }

    @Override
    public List<DevRelation> getRelationListByTargetIdList(List<String> targetIdList) {
        return this.getRelationListByTargetIdListAndCategory(targetIdList, null);
    }

    @Override
    public List<DevRelation> getRelationListByTargetIdAndCategory(String targetId, String category) {
        LambdaQueryWrapper<DevRelation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DevRelation::getTargetId, targetId);
        if (ObjectUtil.isNotEmpty(category)) {
            lambdaQueryWrapper.eq(DevRelation::getCategory, category);
        }
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public List<DevRelation> getRelationListByTargetIdListAndCategory(List<String> targetIdList, String category) {
        LambdaQueryWrapper<DevRelation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(DevRelation::getTargetId, targetIdList);
        if (ObjectUtil.isNotEmpty(category)) {
            lambdaQueryWrapper.eq(DevRelation::getCategory, category);
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
                .map(DevRelation::getTargetId).collect(Collectors.toList());
    }

    @Override
    public List<String> getRelationTargetIdListByObjectIdListAndCategory(List<String> objectIdList, String category) {
        return this.getRelationListByObjectIdListAndCategory(objectIdList, category).stream()
                .map(DevRelation::getTargetId).collect(Collectors.toList());
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
                .map(DevRelation::getObjectId).collect(Collectors.toList());
    }

    @Override
    public List<String> getRelationObjectIdListByTargetIdListAndCategory(List<String> targetIdList, String category) {
        return this.getRelationListByTargetIdListAndCategory(targetIdList, category).stream()
                .map(DevRelation::getObjectId).collect(Collectors.toList());
    }
}
