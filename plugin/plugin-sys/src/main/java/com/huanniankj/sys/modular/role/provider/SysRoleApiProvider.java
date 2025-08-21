package com.huanniankj.sys.modular.role.provider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huanniankj.sys.api.SysRoleApi;
import com.huanniankj.sys.core.enums.SysBuildInEnum;
import com.huanniankj.sys.modular.relation.entity.SysRelation;
import com.huanniankj.sys.modular.relation.enums.SysRelationCategoryEnum;
import com.huanniankj.sys.modular.relation.service.SysRelationService;
import com.huanniankj.sys.modular.resource.entity.SysButton;
import com.huanniankj.sys.modular.resource.entity.SysMenu;
import com.huanniankj.sys.modular.resource.enums.SysResourceCategoryEnum;
import com.huanniankj.sys.modular.resource.service.SysButtonService;
import com.huanniankj.sys.modular.resource.service.SysMenuService;
import com.huanniankj.sys.modular.role.entity.SysRole;
import com.huanniankj.sys.modular.role.param.SysRoleGrantResourceParam;
import com.huanniankj.sys.modular.role.param.SysRoleSelectorRoleParam;
import com.huanniankj.sys.modular.role.service.SysRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色API接口提供者
 *
 * @author happynewyear
 */
@Service
public class SysRoleApiProvider implements SysRoleApi {

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysMenuService sysMenuService;

    @Resource
    private SysButtonService sysButtonService;

    @Resource
    private SysRelationService sysRelationService;

    @Override
    public boolean orgHasRole(List<String> orgIdList) {
        return sysRoleService.count(new LambdaQueryWrapper<SysRole>().in(SysRole::getOrgId, orgIdList)) > 0;
    }

    @SuppressWarnings("ALL")
    @Override
    public Page<JSONObject> roleSelector(String orgId, String category, String searchKey, List<String> dataScopeList,
                                         boolean excludeSuperAdmin) {
        SysRoleSelectorRoleParam sysRoleSelectorRoleParam = new SysRoleSelectorRoleParam();
        sysRoleSelectorRoleParam.setOrgId(orgId);
        sysRoleSelectorRoleParam.setCategory(category);
        sysRoleSelectorRoleParam.setSearchKey(searchKey);
        sysRoleSelectorRoleParam.setDataScopeList(dataScopeList);
        sysRoleSelectorRoleParam.setExcludeSuperAdmin(excludeSuperAdmin);
        return BeanUtil.toBean(sysRoleService.roleSelector(sysRoleSelectorRoleParam), Page.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void grantForGenMenuAndButton(String menuId) {
        String superAdminRoleId = sysRoleService.getOne(new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getCode, SysBuildInEnum.BUILD_IN_ROLE_CODE.getValue())).getId();
        SysRoleGrantResourceParam sysRoleGrantResourceParam = new SysRoleGrantResourceParam();
        sysRoleGrantResourceParam.setId(superAdminRoleId);
        SysMenu sysMenu = sysMenuService.queryEntity(menuId);
        SysRoleGrantResourceParam.SysRoleGrantResource sysRoleGrantResource =
                new SysRoleGrantResourceParam.SysRoleGrantResource();
        sysRoleGrantResource.setMenuId(sysMenu.getId());
        List<String> buttonIdList = sysButtonService.list(new LambdaQueryWrapper<SysButton>().eq(SysButton::getParentId,
                        sysMenu.getId()).eq(SysButton::getCategory, SysResourceCategoryEnum.BUTTON.getValue())).stream()
                .map(SysButton::getId).collect(Collectors.toList());
        sysRoleGrantResource.setButtonInfo(buttonIdList);
        sysRoleGrantResourceParam.setGrantInfoList(CollectionUtil.newArrayList(sysRoleGrantResource));

        List<String> menuIdList = sysRoleGrantResourceParam.getGrantInfoList().stream()
                .map(SysRoleGrantResourceParam.SysRoleGrantResource::getMenuId).collect(Collectors.toList());
        List<String> extJsonList = sysRoleGrantResourceParam.getGrantInfoList().stream()
                .map(JSONUtil::toJsonStr).collect(Collectors.toList());

        List<String> existMenuIdList = sysMenuService.list(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getCategory,
                SysResourceCategoryEnum.MENU.getValue())).stream().map(SysMenu::getId).collect(Collectors.toList());
        if (ObjectUtil.isNotEmpty(existMenuIdList)) {
            sysRelationService.remove(new LambdaQueryWrapper<SysRelation>()
                    .eq(SysRelation::getObjectId, superAdminRoleId)
                    .eq(SysRelation::getCategory, SysRelationCategoryEnum.SYS_ROLE_HAS_RESOURCE.getValue())
                    .notIn(SysRelation::getTargetId, existMenuIdList));
        }
        sysRelationService.saveRelationBatchWithAppend(superAdminRoleId, menuIdList,
                SysRelationCategoryEnum.SYS_ROLE_HAS_RESOURCE.getValue(), extJsonList);
    }

    @Override
    public List<JSONObject> resourceTreeSelector() {
        return sysRoleService.resourceTreeSelector(false).stream().map(JSONUtil::parseObj)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> permissionTreeSelector() {
        return sysRoleService.permissionTreeSelector();
    }
    
}
