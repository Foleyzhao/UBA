package com.huanniankj.uba.modular.rule.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.uba.modular.rule.entity.RuleItem;
import com.huanniankj.uba.modular.rule.param.RuleItemAddParam;
import com.huanniankj.uba.modular.rule.param.RuleItemEditParam;
import com.huanniankj.uba.modular.rule.param.RuleItemIdParam;
import com.huanniankj.uba.modular.rule.param.RuleItemListParam;
import com.huanniankj.uba.modular.rule.param.RuleItemPageParam;

import java.util.List;

/**
 * 数据清洗规则项服务接口
 *
 * @author happynewyear
 */
public interface RuleItemService extends IService<RuleItem> {

    /**
     * 获取数据清洗规则项分页
     */
    Page<RuleItem> page(RuleItemPageParam ruleItemPageParam);

    /**
     * 获取数据清洗规则项列表
     */
    List<RuleItem> list(RuleItemListParam ruleItemListParam);

    /**
     * 添加数据清洗规则项
     */
    void add(RuleItemAddParam ruleItemAddParam);

    /**
     * 编辑数据清洗规则项
     */
    void edit(RuleItemEditParam ruleItemEditParam);

    /**
     * 删除数据清洗规则项
     */
    void delete(List<RuleItemIdParam> ruleItemIdParams);

    /**
     * 获取数据清洗规则项详情
     */
    RuleItem detail(RuleItemIdParam ruleItemIdParam);

    /**
     * 获取数据清洗规则项详情
     */
    RuleItem queryEntity(String id);

    /**
     * 禁用数据清洗规则项
     */
    void disable(RuleItemIdParam ruleItemIdParam);

    /**
     * 启用数据清洗规则项
     */
    void enable(RuleItemIdParam ruleItemIdParam);

}
