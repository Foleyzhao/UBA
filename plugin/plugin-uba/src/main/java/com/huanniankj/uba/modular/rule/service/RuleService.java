package com.huanniankj.uba.modular.rule.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.uba.modular.rule.entity.Rule;
import com.huanniankj.uba.modular.rule.param.RuleAddParam;
import com.huanniankj.uba.modular.rule.param.RuleEditParam;
import com.huanniankj.uba.modular.rule.param.RuleIdParam;
import com.huanniankj.uba.modular.rule.param.RuleListParam;
import com.huanniankj.uba.modular.rule.param.RulePageParam;

import java.util.List;

/**
 * 数据清洗规则服务接口
 *
 * @author happynewyear
 */
public interface RuleService extends IService<Rule> {

    /**
     * 获取数据清洗规则分页
     */
    Page<Rule> page(RulePageParam rulePageParam);

    /**
     * 获取数据清洗规则列表
     */
    List<Rule> list(RuleListParam ruleListParam);

    /**
     * 添加数据清洗规则
     */
    void add(RuleAddParam ruleAddParam);

    /**
     * 编辑数据清洗规则
     */
    void edit(RuleEditParam ruleEditParam);

    /**
     * 删除数据清洗规则
     */
    void delete(List<RuleIdParam> ruleIdParams);

    /**
     * 获取数据清洗规则详情
     */
    Rule detail(RuleIdParam ruleIdParam);

    /**
     * 获取数据清洗规则详情
     */
    Rule queryEntity(String id);

}
