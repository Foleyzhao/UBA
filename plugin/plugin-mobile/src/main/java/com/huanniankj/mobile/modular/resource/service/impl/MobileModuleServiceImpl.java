package com.huanniankj.mobile.modular.resource.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.page.CommonPageRequest;
import com.huanniankj.mobile.modular.resource.entity.MobileMenu;
import com.huanniankj.mobile.modular.resource.entity.MobileModule;
import com.huanniankj.mobile.modular.resource.enums.MobileResourceCategoryEnum;
import com.huanniankj.mobile.modular.resource.mapper.MobileModuleMapper;
import com.huanniankj.mobile.modular.resource.param.module.MobileModuleAddParam;
import com.huanniankj.mobile.modular.resource.param.module.MobileModuleEditParam;
import com.huanniankj.mobile.modular.resource.param.module.MobileModuleIdParam;
import com.huanniankj.mobile.modular.resource.param.module.MobileModulePageParam;
import com.huanniankj.mobile.modular.resource.service.MobileMenuService;
import com.huanniankj.mobile.modular.resource.service.MobileModuleService;
import com.huanniankj.sys.api.SysRelationApi;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 模块服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class MobileModuleServiceImpl extends ServiceImpl<MobileModuleMapper, MobileModule>
        implements MobileModuleService {

    @Resource
    private MobileMenuService mobileMenuService;

    @Resource
    private SysRelationApi sysRelationApi;

    @Override
    public Page<MobileModule> page(MobileModulePageParam mobileModulePageParam) {
        QueryWrapper<MobileModule> queryWrapper = new QueryWrapper<MobileModule>().checkSqlInjection();
        queryWrapper.lambda().eq(MobileModule::getCategory, MobileResourceCategoryEnum.MODULE.getValue());
        if (ObjectUtil.isNotEmpty(mobileModulePageParam.getSearchKey())) {
            queryWrapper.lambda().like(MobileModule::getTitle, mobileModulePageParam.getSearchKey());
        }
        if (ObjectUtil.isAllNotEmpty(mobileModulePageParam.getSortField(), mobileModulePageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(mobileModulePageParam.getSortOrder());
            queryWrapper.orderBy(true, mobileModulePageParam.getSortOrder()
                            .equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(mobileModulePageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(MobileModule::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Override
    public void add(MobileModuleAddParam mobileModuleAddParam) {
        MobileModule mobileModule = BeanUtil.toBean(mobileModuleAddParam, MobileModule.class);
        boolean repeatTitle = this.count(new LambdaQueryWrapper<MobileModule>().eq(MobileModule::getCategory,
                MobileResourceCategoryEnum.MODULE.getValue()).eq(MobileModule::getTitle, mobileModule.getTitle())) > 0;
        if (repeatTitle) {
            throw new CommonException("存在重复的模块，名称为：{}", mobileModule.getTitle());
        }
        if (ObjectUtil.isEmpty(mobileModule.getCode())) {
            mobileModule.setCode(RandomUtil.randomString(10));
        }
        if (ObjectUtil.isEmpty(mobileModule.getCode())) {
            mobileModule.setCode(RandomUtil.randomString(10));
        }
        mobileModule.setCategory(MobileResourceCategoryEnum.MODULE.getValue());
        this.save(mobileModule);
    }

    @Override
    public void edit(MobileModuleEditParam mobileModuleEditParam) {
        MobileModule mobileModule = this.queryEntity(mobileModuleEditParam.getId());
        BeanUtil.copyProperties(mobileModuleEditParam, mobileModule);
        boolean repeatTitle = this.count(new LambdaQueryWrapper<MobileModule>().eq(MobileModule::getCategory,
                        MobileResourceCategoryEnum.MODULE.getValue()).eq(MobileModule::getTitle, mobileModule.getTitle())
                .ne(MobileModule::getId, mobileModule.getId())) > 0;
        if (repeatTitle) {
            throw new CommonException("存在重复的模块，名称为：{}", mobileModule.getTitle());
        }
        this.updateById(mobileModule);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<MobileModuleIdParam> mobileModuleIdParamList) {
        List<String> mobileModuleIdList = CollStreamUtil.toList(mobileModuleIdParamList, MobileModuleIdParam::getId);
        if (ObjectUtil.isNotEmpty(mobileModuleIdList)) {
            // 获取模块下的菜单
            List<MobileMenu> allMenuList = mobileMenuService.list(new LambdaQueryWrapper<MobileMenu>()
                    .in(MobileMenu::getCategory, CollectionUtil.newArrayList(MobileResourceCategoryEnum.MENU.getValue())));
            if (ObjectUtil.isNotEmpty(allMenuList)) {
                List<String> toDeleteMenuIdList = CollectionUtil.newArrayList(mobileModuleIdList);
                allMenuList.stream().filter(mobileMenu -> mobileModuleIdList.contains(mobileMenu.getModule()))
                        .toList().forEach(mobileMenu -> toDeleteMenuIdList
                                .addAll(mobileMenuService.getChildListById(allMenuList, mobileMenu.getId(), true)
                                        .stream().map(MobileMenu::getId).toList()));
                if (ObjectUtil.isNotEmpty(toDeleteMenuIdList)) {
                    // 清除对应的角色与移动端资源信息
                    sysRelationApi.removeRoleHasMobileMenuRelation(toDeleteMenuIdList);
                    // 执行删除
                    this.removeByIds(toDeleteMenuIdList);
                }
            }
        }
    }

    @Override
    public MobileModule detail(MobileModuleIdParam mobileModuleIdParam) {
        return this.queryEntity(mobileModuleIdParam.getId());
    }

    @Override
    public MobileModule queryEntity(String id) {
        MobileModule mobileModule = this.getById(id);
        if (ObjectUtil.isEmpty(mobileModule)) {
            throw new CommonException("模块不存在，id值为：{}", id);
        }
        return mobileModule;
    }

    @Override
    public List<JSONObject> mobileModuleSelector() {
        LambdaQueryWrapper<MobileModule> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(MobileModule::getId, MobileModule::getTitle);
        lambdaQueryWrapper.eq(MobileModule::getCategory, MobileResourceCategoryEnum.MODULE.getValue());
        lambdaQueryWrapper.orderByAsc(MobileModule::getSortCode);
        return this.list(lambdaQueryWrapper).stream().map(item -> JSONUtil.createObj()
                        .set("id", item.getId()).set("name", item.getTitle()))
                .collect(Collectors.toList());
    }
}
