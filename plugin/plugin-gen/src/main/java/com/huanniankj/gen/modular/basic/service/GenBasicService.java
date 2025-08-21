package com.huanniankj.gen.modular.basic.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.gen.modular.basic.param.GenBasicAddParam;
import com.huanniankj.gen.modular.basic.param.GenBasicEditParam;
import com.huanniankj.gen.modular.basic.param.GenBasicIdParam;
import com.huanniankj.gen.modular.basic.param.GenBasicPageParam;
import com.huanniankj.gen.modular.basic.param.GenBasicSelectorMenuParam;
import com.huanniankj.gen.modular.basic.param.GenBasicTableColumnParam;
import com.huanniankj.gen.modular.basic.result.GenBasicMobileModuleSelectorResult;
import com.huanniankj.gen.modular.basic.result.GenBasicModuleSelectorResult;
import com.huanniankj.gen.modular.basic.result.GenBasicPreviewResult;
import com.huanniankj.gen.modular.basic.result.GenBasicTableColumnResult;
import com.huanniankj.gen.modular.basic.result.GenBasicTableResult;
import jakarta.servlet.http.HttpServletResponse;
import com.huanniankj.gen.modular.basic.entity.GenBasic;

import java.io.IOException;
import java.util.List;

/**
 * 代码生成基础服务接口
 *
 * @author happynewyear
 */
public interface GenBasicService extends IService<GenBasic> {

    /**
     * 查询代码生成基础分页
     */
    Page<GenBasic> page(GenBasicPageParam genBasicPageParam);

    /**
     * 添加代码生成基础
     */
    GenBasic add(GenBasicAddParam genBasicAddParam);

    /**
     * 编辑代码生成基础
     */
    GenBasic edit(GenBasicEditParam genBasicEditParam);

    /**
     * 删除代码生成基础
     */
    void delete(List<GenBasicIdParam> genBasicIdParamList);

    /**
     * 获取代码生成基础详情
     */
    GenBasic detail(GenBasicIdParam genBasicIdParam);

    /**
     * 获取代码生成基础详情
     */
    GenBasic queryEntity(String id);

    /**
     * 获取所有表信息
     */
    List<GenBasicTableResult> tables();

    /**
     * 获取表内所有字段信息
     */
    List<GenBasicTableColumnResult> tableColumns(GenBasicTableColumnParam genBasicTableColumnParam);

    /**
     * 执行代码生成
     */
    void execGenZip(GenBasicIdParam genBasicIdParam, HttpServletResponse response) throws IOException;

    /**
     * 执行代码生成
     */
    void execGenPro(GenBasicIdParam genBasicIdParam, HttpServletResponse response) throws IOException;

    /**
     * 预览代码生成
     */
    GenBasicPreviewResult previewGen(GenBasicIdParam genBasicIdParam);

    /**
     * 获取移动端模块
     */
    List<GenBasicMobileModuleSelectorResult> mobileModuleSelector();

    /**
     * 获取模块
     */
    List<GenBasicModuleSelectorResult> moduleSelector();

    /**
     * 代码生成获取所有菜单树包括未授权的
     */
    List<Tree<String>> menuTreeSelector(GenBasicSelectorMenuParam genBasicSelectorMenuParam);
}
