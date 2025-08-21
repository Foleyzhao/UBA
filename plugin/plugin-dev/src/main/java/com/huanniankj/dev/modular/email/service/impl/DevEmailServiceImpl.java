package com.huanniankj.dev.modular.email.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailAccount;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.page.CommonPageRequest;
import com.huanniankj.common.util.CommonEmailUtil;
import com.huanniankj.dev.api.DevConfigApi;
import com.huanniankj.dev.modular.email.entity.DevEmail;
import com.huanniankj.dev.modular.email.enums.DevEmailEngineTypeEnum;
import com.huanniankj.dev.modular.email.mapper.DevEmailMapper;
import com.huanniankj.dev.modular.email.param.DevEmailIdParam;
import com.huanniankj.dev.modular.email.param.DevEmailPageParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendAliyunHtmlParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendAliyunTmpParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendAliyunTxtParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendDynamicHtmlParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendDynamicTxtParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendLocalHtmlParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendLocalTxtParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendTencentHtmlParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendTencentTmpParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendTencentTxtParam;
import com.huanniankj.dev.modular.email.service.DevEmailService;
import com.huanniankj.dev.modular.email.util.DevEmailAliyunUtil;
import com.huanniankj.dev.modular.email.util.DevEmailLocalUtil;
import com.huanniankj.dev.modular.email.util.DevEmailTencentUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 邮件服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class DevEmailServiceImpl extends ServiceImpl<DevEmailMapper, DevEmail> implements DevEmailService {

    /**
     * 默认邮件引擎
     */
    private static final String SYSTEM_SYS_DEFAULT_EMAIL_ENGINE_KEY = "SYSTEM_SYS_DEFAULT_EMAIL_ENGINE";

    @Resource
    private DevConfigApi devConfigApi;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void sendDynamicTxt(String engine, String receiveAccounts, String subject, String content) {
        if (engine.equals(DevEmailEngineTypeEnum.LOCAL.getValue())) {
            DevEmailSendLocalTxtParam devEmailSendLocalTxtParam = new DevEmailSendLocalTxtParam();
            devEmailSendLocalTxtParam.setReceiveAccounts(receiveAccounts);
            devEmailSendLocalTxtParam.setSubject(subject);
            devEmailSendLocalTxtParam.setContent(content);
            this.sendLocal(devEmailSendLocalTxtParam);
        } else if (engine.equals(DevEmailEngineTypeEnum.ALIYUN.getValue())) {
            DevEmailSendAliyunTxtParam devEmailSendAliyunTxtParam = new DevEmailSendAliyunTxtParam();
            devEmailSendAliyunTxtParam.setReceiveAccounts(receiveAccounts);
            devEmailSendAliyunTxtParam.setSubject(subject);
            devEmailSendAliyunTxtParam.setContent(content);
            this.sendAliyun(devEmailSendAliyunTxtParam);
        } else if (engine.equals(DevEmailEngineTypeEnum.TENCENT.getValue())) {
            DevEmailSendTencentTxtParam devEmailSendTencentTxtParam = new DevEmailSendTencentTxtParam();
            devEmailSendTencentTxtParam.setReceiveAccounts(receiveAccounts);
            devEmailSendTencentTxtParam.setSubject(subject);
            devEmailSendTencentTxtParam.setContent(content);
            this.sendTencent(devEmailSendTencentTxtParam);
        } else {
            throw new CommonException("不支持的邮件引擎：{}", engine);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void sendDynamicHtml(String engine, String receiveAccounts, String subject, String content) {
        if (engine.equals(DevEmailEngineTypeEnum.LOCAL.getValue())) {
            DevEmailSendLocalHtmlParam devEmailSendLocalHtmlParam = new DevEmailSendLocalHtmlParam();
            devEmailSendLocalHtmlParam.setReceiveAccounts(receiveAccounts);
            devEmailSendLocalHtmlParam.setSubject(subject);
            devEmailSendLocalHtmlParam.setContent(content);
            this.sendLocal(devEmailSendLocalHtmlParam);
        } else if (engine.equals(DevEmailEngineTypeEnum.ALIYUN.getValue())) {
            DevEmailSendAliyunHtmlParam devEmailSendAliyunHtmlParam = new DevEmailSendAliyunHtmlParam();
            devEmailSendAliyunHtmlParam.setReceiveAccounts(receiveAccounts);
            devEmailSendAliyunHtmlParam.setSubject(subject);
            devEmailSendAliyunHtmlParam.setContent(content);
            this.sendAliyun(devEmailSendAliyunHtmlParam);
        } else if (engine.equals(DevEmailEngineTypeEnum.TENCENT.getValue())) {
            DevEmailSendTencentHtmlParam devEmailSendTencentHtmlParam = new DevEmailSendTencentHtmlParam();
            devEmailSendTencentHtmlParam.setReceiveAccounts(receiveAccounts);
            devEmailSendTencentHtmlParam.setSubject(subject);
            devEmailSendTencentHtmlParam.setContent(content);
            this.sendTencent(devEmailSendTencentHtmlParam);
        } else {
            throw new CommonException("不支持的邮件引擎：{}", engine);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void sendDynamicTxt(DevEmailSendDynamicTxtParam devEmailSendDynamicTxtParam) {
        String defaultEmailEngine = devConfigApi.getValueByKey(SYSTEM_SYS_DEFAULT_EMAIL_ENGINE_KEY);
        if (ObjectUtil.isEmpty(defaultEmailEngine)) {
            throw new CommonException("请联系管理员配置默认邮件发送引擎");
        }
        String receiveAccounts = devEmailSendDynamicTxtParam.getReceiveAccounts();
        String subject = devEmailSendDynamicTxtParam.getSubject();
        String content = devEmailSendDynamicTxtParam.getContent();
        this.sendDynamicTxt(defaultEmailEngine, receiveAccounts, subject, content);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void sendDynamicHtml(DevEmailSendDynamicHtmlParam devEmailSendDynamicHtmlParam) {
        String defaultEmailEngine = devConfigApi.getValueByKey(SYSTEM_SYS_DEFAULT_EMAIL_ENGINE_KEY);
        if (ObjectUtil.isEmpty(defaultEmailEngine)) {
            throw new CommonException("请联系管理员配置默认邮件发送引擎");
        }
        String receiveAccounts = devEmailSendDynamicHtmlParam.getReceiveAccounts();
        String subject = devEmailSendDynamicHtmlParam.getSubject();
        String content = devEmailSendDynamicHtmlParam.getContent();
        this.sendDynamicHtml(defaultEmailEngine, receiveAccounts, subject, content);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void sendLocal(DevEmailSendLocalTxtParam devEmailSendLocalTxtParam) {
        CommonEmailUtil.validEmail(devEmailSendLocalTxtParam.getReceiveAccounts());
        String receiptInfo = DevEmailLocalUtil.sendTextEmail(devEmailSendLocalTxtParam.getReceiveAccounts(),
                devEmailSendLocalTxtParam.getSubject(), devEmailSendLocalTxtParam.getContent(),
                devEmailSendLocalTxtParam.getFiles());
        DevEmail devEmail = new DevEmail();
        BeanUtil.copyProperties(devEmailSendLocalTxtParam, devEmail);
        devEmail.setEngine(DevEmailEngineTypeEnum.LOCAL.getValue());
        devEmail.setReceiptInfo(receiptInfo);
        MailAccount client = DevEmailLocalUtil.getClient();
        devEmail.setSendAccount(client.getFrom());
        devEmail.setSendUser(client.getUser());
        this.save(devEmail);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void sendLocal(DevEmailSendLocalHtmlParam devEmailSendLocalHtmlParam) {
        CommonEmailUtil.validEmail(devEmailSendLocalHtmlParam.getReceiveAccounts());
        String receiptInfo = DevEmailLocalUtil.sendHtmlEmail(devEmailSendLocalHtmlParam.getReceiveAccounts(),
                devEmailSendLocalHtmlParam.getSubject(), devEmailSendLocalHtmlParam.getContent(),
                devEmailSendLocalHtmlParam.getImageMap(),
                devEmailSendLocalHtmlParam.getFiles());
        DevEmail devEmail = new DevEmail();
        BeanUtil.copyProperties(devEmailSendLocalHtmlParam, devEmail);
        devEmail.setEngine(DevEmailEngineTypeEnum.LOCAL.getValue());
        devEmail.setReceiptInfo(receiptInfo);
        MailAccount client = DevEmailLocalUtil.getClient();
        devEmail.setSendAccount(client.getFrom());
        devEmail.setSendUser(client.getUser());
        this.save(devEmail);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void sendAliyun(DevEmailSendAliyunTxtParam devEmailSendAliyunTxtParam) {
        CommonEmailUtil.validEmail(devEmailSendAliyunTxtParam.getReceiveAccounts());
        String receiptInfo = DevEmailAliyunUtil.sendTextEmail(devEmailSendAliyunTxtParam.getSendAccount(),
                devEmailSendAliyunTxtParam.getSendUser(), devEmailSendAliyunTxtParam.getReceiveAccounts(),
                devEmailSendAliyunTxtParam.getSubject(), devEmailSendAliyunTxtParam.getContent());
        DevEmail devEmail = new DevEmail();
        BeanUtil.copyProperties(devEmailSendAliyunTxtParam, devEmail);
        devEmail.setEngine(DevEmailEngineTypeEnum.ALIYUN.getValue());
        devEmail.setReceiptInfo(receiptInfo);
        this.save(devEmail);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void sendAliyun(DevEmailSendAliyunHtmlParam devEmailSendAliyunHtmlParam) {
        CommonEmailUtil.validEmail(devEmailSendAliyunHtmlParam.getReceiveAccounts());
        String receiptInfo = DevEmailAliyunUtil.sendHtmlEmail(devEmailSendAliyunHtmlParam.getSendAccount(),
                devEmailSendAliyunHtmlParam.getSendUser(), devEmailSendAliyunHtmlParam.getReceiveAccounts(),
                devEmailSendAliyunHtmlParam.getSubject(), devEmailSendAliyunHtmlParam.getContent());
        DevEmail devEmail = new DevEmail();
        BeanUtil.copyProperties(devEmailSendAliyunHtmlParam, devEmail);
        devEmail.setEngine(DevEmailEngineTypeEnum.ALIYUN.getValue());
        devEmail.setReceiptInfo(receiptInfo);
        this.save(devEmail);
    }

    @Override
    public void sendAliyun(DevEmailSendAliyunTmpParam devEmailSendAliyunTmpParam) {
        CommonEmailUtil.validEmail(devEmailSendAliyunTmpParam.getReceiveAccounts());
        String receiptInfo = DevEmailAliyunUtil.sendEmailWithTemplate(devEmailSendAliyunTmpParam.getSendAccount(),
                devEmailSendAliyunTmpParam.getTagName(), devEmailSendAliyunTmpParam.getReceiveAccounts(),
                devEmailSendAliyunTmpParam.getTemplateName());
        DevEmail devEmail = new DevEmail();
        BeanUtil.copyProperties(devEmailSendAliyunTmpParam, devEmail);
        devEmail.setEngine(DevEmailEngineTypeEnum.ALIYUN.getValue());
        devEmail.setReceiptInfo(receiptInfo);
        this.save(devEmail);
    }

    @Override
    public void sendTencent(DevEmailSendTencentTxtParam devEmailSendTencentTxtParam) {
        CommonEmailUtil.validEmail(devEmailSendTencentTxtParam.getReceiveAccounts());
        String receiptInfo = DevEmailTencentUtil.sendTextEmail(devEmailSendTencentTxtParam.getSendAccount(),
                devEmailSendTencentTxtParam.getSendUser(), devEmailSendTencentTxtParam.getReceiveAccounts(),
                devEmailSendTencentTxtParam.getSubject(), devEmailSendTencentTxtParam.getContent(),
                devEmailSendTencentTxtParam.getAttachmentList());
        DevEmail devEmail = new DevEmail();
        BeanUtil.copyProperties(devEmailSendTencentTxtParam, devEmail);
        devEmail.setEngine(DevEmailEngineTypeEnum.TENCENT.getValue());
        devEmail.setReceiptInfo(receiptInfo);
        this.save(devEmail);
    }

    @Override
    public void sendTencent(DevEmailSendTencentHtmlParam devEmailSendTencentHtmlParam) {
        CommonEmailUtil.validEmail(devEmailSendTencentHtmlParam.getReceiveAccounts());
        String receiptInfo = DevEmailTencentUtil.sendHtmlEmail(devEmailSendTencentHtmlParam.getSendAccount(),
                devEmailSendTencentHtmlParam.getSendUser(), devEmailSendTencentHtmlParam.getReceiveAccounts(),
                devEmailSendTencentHtmlParam.getSubject(), devEmailSendTencentHtmlParam.getContent(),
                devEmailSendTencentHtmlParam.getAttachmentList());
        DevEmail devEmail = new DevEmail();
        BeanUtil.copyProperties(devEmailSendTencentHtmlParam, devEmail);
        devEmail.setEngine(DevEmailEngineTypeEnum.TENCENT.getValue());
        devEmail.setReceiptInfo(receiptInfo);
        this.save(devEmail);
    }

    @Override
    public void sendTencent(DevEmailSendTencentTmpParam devEmailSendTencentTmpParam) {
        CommonEmailUtil.validEmail(devEmailSendTencentTmpParam.getReceiveAccounts());
        String receiptInfo = DevEmailTencentUtil.sendEmailWithTemplate(devEmailSendTencentTmpParam.getSendAccount(),
                devEmailSendTencentTmpParam.getSendUser(), devEmailSendTencentTmpParam.getReceiveAccounts(),
                devEmailSendTencentTmpParam.getTemplateName(), devEmailSendTencentTmpParam.getTemplateParam(),
                devEmailSendTencentTmpParam.getSubject(), CollectionUtil.newArrayList());
        DevEmail devEmail = new DevEmail();
        BeanUtil.copyProperties(devEmailSendTencentTmpParam, devEmail);
        devEmail.setEngine(DevEmailEngineTypeEnum.TENCENT.getValue());
        devEmail.setReceiptInfo(receiptInfo);
        this.save(devEmail);
    }

    @Override
    public Page<DevEmail> page(DevEmailPageParam devEmailPageParam) {
        QueryWrapper<DevEmail> queryWrapper = new QueryWrapper<DevEmail>().checkSqlInjection();
        if (ObjectUtil.isNotEmpty(devEmailPageParam.getEngine())) {
            queryWrapper.lambda().eq(DevEmail::getEngine, devEmailPageParam.getEngine());
        }
        if (ObjectUtil.isNotEmpty(devEmailPageParam.getSearchKey())) {
            queryWrapper.lambda().like(DevEmail::getSubject, devEmailPageParam.getSearchKey());
        }
        if (ObjectUtil.isAllNotEmpty(devEmailPageParam.getSortField(), devEmailPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(devEmailPageParam.getSortOrder());
            queryWrapper.orderBy(true, devEmailPageParam.getSortOrder()
                            .equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(devEmailPageParam.getSortField()));
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<DevEmailIdParam> devEmailIdParamList) {
        this.removeByIds(CollStreamUtil.toList(devEmailIdParamList, DevEmailIdParam::getId));
    }

    @Override
    public DevEmail detail(DevEmailIdParam devEmailIdParam) {
        return this.queryEntity(devEmailIdParam.getId());
    }

    @Override
    public DevEmail queryEntity(String id) {
        DevEmail devEmail = this.getById(id);
        if (ObjectUtil.isEmpty(devEmail)) {
            throw new CommonException("邮件发送记录不存在，id值为：{}", id);
        }
        return devEmail;
    }
}
