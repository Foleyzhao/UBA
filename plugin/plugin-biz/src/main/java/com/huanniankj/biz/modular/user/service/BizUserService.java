package com.huanniankj.biz.modular.user.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.biz.modular.user.param.BizUserAddParam;
import com.huanniankj.biz.modular.user.param.BizUserEditParam;
import com.huanniankj.biz.modular.user.param.BizUserExportParam;
import com.huanniankj.biz.modular.user.param.BizUserGrantRoleParam;
import com.huanniankj.biz.modular.user.param.BizUserIdParam;
import com.huanniankj.biz.modular.user.param.BizUserPageParam;
import com.huanniankj.biz.modular.user.param.BizUserSelectorOrgListParam;
import com.huanniankj.biz.modular.user.param.BizUserSelectorPositionParam;
import com.huanniankj.biz.modular.user.param.BizUserSelectorRoleParam;
import com.huanniankj.biz.modular.user.param.BizUserSelectorUserParam;
import jakarta.servlet.http.HttpServletResponse;
import com.huanniankj.biz.modular.org.entity.BizOrg;
import com.huanniankj.biz.modular.position.entity.BizPosition;
import com.huanniankj.biz.modular.user.entity.BizUser;
import com.huanniankj.biz.modular.user.result.BizUserRoleResult;

import java.io.IOException;
import java.util.List;

/**
 * 人员服务接口
 *
 * @author happynewyear
 */
public interface BizUserService extends IService<BizUser> {

    /**
     * 获取人员分页
     */
    Page<BizUser> page(BizUserPageParam bizUserPageParam);

    /**
     * 添加人员
     */
    void add(BizUserAddParam bizUserAddParam, String sourceFromType);

    /**
     * 编辑人员
     */
    void edit(BizUserEditParam bizUserEditParam);

    /**
     * 删除人员
     */
    void delete(List<BizUserIdParam> bizUserIdParamList);

    /**
     * 获取人员详情
     */
    BizUser detail(BizUserIdParam bizUserIdPara);

    /**
     * 获取人员详情
     */
    BizUser queryEntity(String id);

    /**
     * 禁用人员
     */
    void disableUser(BizUserIdParam bizUserIdParam);

    /**
     * 启用人员
     */
    void enableUser(BizUserIdParam bizUserIdParam);

    /**
     * 重置人员密码
     */
    void resetPassword(BizUserIdParam bizUserIdParam);

    /**
     * 获取人员拥有角色
     */
    List<String> ownRole(BizUserIdParam bizUserIdParam);

    /**
     * 给人员授权角色
     */
    void grantRole(BizUserGrantRoleParam bizUserGrantRoleParam);

    /**
     * 用户导出
     */
    void exportUser(BizUserExportParam bizUserExportParam, HttpServletResponse response) throws IOException;

    /**
     * 导出用户个人信息
     */
    void exportUserInfo(BizUserIdParam bizUserIdParam, HttpServletResponse response) throws IOException;

    /* ====人员部分所需要用到的选择器==== */

    /**
     * 获取机构树选择器
     */
    List<Tree<String>> orgTreeSelector();

    /**
     * 获取机构列表选择器
     */
    Page<BizOrg> orgListSelector(BizUserSelectorOrgListParam bizUserSelectorOrgListParam);

    /**
     * 获取岗位选择器
     */
    Page<BizPosition> positionSelector(BizUserSelectorPositionParam bizUserSelectorPositionParam);

    /**
     * 获取角色选择器
     */
    Page<BizUserRoleResult> roleSelector(BizUserSelectorRoleParam bizUserSelectorRoleParam);

    /**
     * 获取人员选择器
     */
    Page<BizUser> userSelector(BizUserSelectorUserParam bizUserSelectorUserParam);
}
