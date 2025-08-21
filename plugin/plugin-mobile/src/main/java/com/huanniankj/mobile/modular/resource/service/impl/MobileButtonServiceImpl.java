package com.huanniankj.mobile.modular.resource.service.impl;

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
import com.huanniankj.mobile.modular.resource.entity.MobileButton;
import com.huanniankj.mobile.modular.resource.entity.MobileMenu;
import com.huanniankj.mobile.modular.resource.enums.MobileResourceCategoryEnum;
import com.huanniankj.mobile.modular.resource.mapper.MobileButtonMapper;
import com.huanniankj.mobile.modular.resource.param.button.MobileButtonAddParam;
import com.huanniankj.mobile.modular.resource.param.button.MobileButtonEditParam;
import com.huanniankj.mobile.modular.resource.param.button.MobileButtonIdParam;
import com.huanniankj.mobile.modular.resource.param.button.MobileButtonPageParam;
import com.huanniankj.mobile.modular.resource.service.MobileButtonService;
import com.huanniankj.mobile.modular.resource.service.MobileMenuService;
import com.huanniankj.sys.api.SysRelationApi;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 移动端按钮服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class MobileButtonServiceImpl extends ServiceImpl<MobileButtonMapper, MobileButton>
        implements MobileButtonService {

    @Resource
    private SysRelationApi sysRelationApi;

    @Resource
    private MobileMenuService mobileMenuService;

    @Override
    public Page<MobileButton> page(MobileButtonPageParam mobileButtonPageParam) {
        QueryWrapper<MobileButton> queryWrapper = new QueryWrapper<MobileButton>().checkSqlInjection();
        queryWrapper.lambda().eq(MobileButton::getCategory, MobileResourceCategoryEnum.BUTTON.getValue());
        if (ObjectUtil.isNotEmpty(mobileButtonPageParam.getParentId())) {
            queryWrapper.lambda().eq(MobileButton::getParentId, mobileButtonPageParam.getParentId());
        }
        if (ObjectUtil.isNotEmpty(mobileButtonPageParam.getSearchKey())) {
            queryWrapper.lambda().like(MobileButton::getTitle, mobileButtonPageParam.getSearchKey());
        }
        if (ObjectUtil.isAllNotEmpty(mobileButtonPageParam.getSortField(), mobileButtonPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(mobileButtonPageParam.getSortOrder());
            queryWrapper.orderBy(true, mobileButtonPageParam.getSortOrder()
                            .equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(mobileButtonPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(MobileButton::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Override
    public void add(MobileButtonAddParam mobileButtonAddParam) {
        MobileButton mobileButton = BeanUtil.toBean(mobileButtonAddParam, MobileButton.class);
        boolean repeatCode = this.count(new LambdaQueryWrapper<MobileButton>()
                .eq(MobileButton::getCategory, MobileResourceCategoryEnum.BUTTON.getValue())
                .eq(MobileButton::getCode, mobileButton.getCode())) > 0;
        if (repeatCode) {
            throw new CommonException("存在重复的移动端按钮，编码为：{}", mobileButton.getCode());
        }
        if (ObjectUtil.isEmpty(mobileButton.getCode())) {
            mobileButton.setCode(RandomUtil.randomString(10));
        }
        this.save(mobileButton);
    }

    @Override
    public void edit(MobileButtonEditParam mobileButtonEditParam) {
        MobileButton mobileButton = this.queryEntity(mobileButtonEditParam.getId());
        BeanUtil.copyProperties(mobileButtonEditParam, mobileButton);
        boolean repeatCode = this.count(new LambdaQueryWrapper<MobileButton>()
                .eq(MobileButton::getCategory, MobileResourceCategoryEnum.BUTTON.getValue())
                .eq(MobileButton::getCode, mobileButton.getCode())
                .ne(MobileButton::getId, mobileButtonEditParam.getId())) > 0;
        if (repeatCode) {
            throw new CommonException("存在重复的移动端按钮，编码为：{}", mobileButton.getCode());
        }
        this.updateById(mobileButton);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<MobileButtonIdParam> mobileButtonIdParamList) {
        List<String> buttonIdList = CollStreamUtil.toList(mobileButtonIdParamList, MobileButtonIdParam::getId);
        if (ObjectUtil.isNotEmpty(buttonIdList)) {
            // 获取移动端按钮的父菜单id集合
            List<String> parentMenuIdList = mobileMenuService.list(new LambdaQueryWrapper<MobileMenu>()
                            .in(MobileMenu::getId, buttonIdList)
                            .eq(MobileMenu::getCategory, MobileResourceCategoryEnum.BUTTON.getValue()))
                    .stream().map(MobileMenu::getParentId)
                    .collect(Collectors.toList());
            if (ObjectUtil.isNotEmpty(parentMenuIdList)) {
                // 清除对应的角色与移动端菜单信息中的【授权的移动端按钮信息】
                sysRelationApi.removeRoleHasMobileButtonRelation(parentMenuIdList, buttonIdList);
                // 执行删除
                this.removeByIds(buttonIdList);
            }
        }
    }

    @Override
    public MobileButton detail(MobileButtonIdParam mobileButtonIdParam) {
        return this.queryEntity(mobileButtonIdParam.getId());
    }

    @Override
    public MobileButton queryEntity(String id) {
        MobileButton mobileButton = this.getById(id);
        if (ObjectUtil.isEmpty(mobileButton)) {
            throw new CommonException("移动端按钮不存在，id值为：{}", id);
        }
        return mobileButton;
    }

}
