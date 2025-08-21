package com.huanniankj.dev.modular.push.service.impl;

import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.page.CommonPageRequest;
import com.huanniankj.dev.api.DevConfigApi;
import com.huanniankj.dev.modular.push.entity.DevPush;
import com.huanniankj.dev.modular.push.enums.DevPushEngineTypeEnum;
import com.huanniankj.dev.modular.push.enums.DevPushMessageTypeEnum;
import com.huanniankj.dev.modular.push.enums.DevPushNoticeTypeEnum;
import com.huanniankj.dev.modular.push.mapper.DevPushMapper;
import com.huanniankj.dev.modular.push.param.DevPushDingTalkLinkParam;
import com.huanniankj.dev.modular.push.param.DevPushDingTalkMarkdownParam;
import com.huanniankj.dev.modular.push.param.DevPushDingTalkTextParam;
import com.huanniankj.dev.modular.push.param.DevPushDynamicTextParam;
import com.huanniankj.dev.modular.push.param.DevPushFeiShuTextParam;
import com.huanniankj.dev.modular.push.param.DevPushIdParam;
import com.huanniankj.dev.modular.push.param.DevPushPageParam;
import com.huanniankj.dev.modular.push.param.DevPushWorkWechatMarkdownParam;
import com.huanniankj.dev.modular.push.param.DevPushWorkWechatNewsParam;
import com.huanniankj.dev.modular.push.param.DevPushWorkWechatTextParam;
import com.huanniankj.dev.modular.push.service.DevPushService;
import com.huanniankj.dev.modular.push.util.DevPushDingTalkUtil;
import com.huanniankj.dev.modular.push.util.DevPushFeiShuUtil;
import com.huanniankj.dev.modular.push.util.DevPushWorkWechatUtil;
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
public class DevPushServiceImpl extends ServiceImpl<DevPushMapper, DevPush> implements DevPushService {

    /**
     * 默认消息引擎
     */
    private static final String SYSTEM_SYS_DEFAULT_PUSH_ENGINE_KEY = "SYSTEM_SYS_DEFAULT_PUSH_ENGINE";

    @Resource
    private DevConfigApi devConfigApi;

    @Override
    public void pushDynamicText(String engine, String content, boolean noticeAll) {
        if (engine.equals(DevPushEngineTypeEnum.DINGTALK.getValue())) {
            DevPushDingTalkTextParam devPushDingTalkTextParam = new DevPushDingTalkTextParam();
            devPushDingTalkTextParam.setContent(content);
            devPushDingTalkTextParam.setNoticeType(noticeAll ? DevPushNoticeTypeEnum.ALL.getValue() :
                    DevPushNoticeTypeEnum.NONE.getValue());
            this.pushDingTalkText(devPushDingTalkTextParam);
        } else if (engine.equals(DevPushEngineTypeEnum.FEISHU.getValue())) {
            DevPushFeiShuTextParam devPushFeiShuTextParam = new DevPushFeiShuTextParam();
            devPushFeiShuTextParam.setContent(content);
            devPushFeiShuTextParam.setNoticeType(noticeAll ? DevPushNoticeTypeEnum.ALL.getValue() :
                    DevPushNoticeTypeEnum.NONE.getValue());
            this.pushFeiShuText(devPushFeiShuTextParam);
        } else if (engine.equals(DevPushEngineTypeEnum.WORKWECHAT.getValue())) {
            DevPushWorkWechatTextParam devPushWorkWechatTextParam = new DevPushWorkWechatTextParam();
            devPushWorkWechatTextParam.setContent(content);
            devPushWorkWechatTextParam.setNoticeType(noticeAll ? DevPushNoticeTypeEnum.ALL.getValue() :
                    DevPushNoticeTypeEnum.NONE.getValue());
            this.pushWorkWechatText(devPushWorkWechatTextParam);
        } else {
            throw new CommonException("不支持的消息推送引擎：{}", engine);
        }
    }

    @Override
    public void pushDynamicText(DevPushDynamicTextParam devPushDynamicTextParam) {
        String defaultEmailEngine = devConfigApi.getValueByKey(SYSTEM_SYS_DEFAULT_PUSH_ENGINE_KEY);
        if (ObjectUtil.isEmpty(defaultEmailEngine)) {
            throw new CommonException("请联系管理员配置默认消息推送引擎");
        }
        String content = devPushDynamicTextParam.getContent();
        Boolean noticeAll = devPushDynamicTextParam.getNoticeAll();
        this.pushDynamicText(defaultEmailEngine, content, noticeAll);
    }

    @Override
    public void pushFeiShuText(DevPushFeiShuTextParam devPushFeiShuTextParam) {
        String noticeType = devPushFeiShuTextParam.getNoticeType();
        String receiptInfo;
        if (DevPushNoticeTypeEnum.NONE.getValue().equals(noticeType)) {
            receiptInfo = DevPushFeiShuUtil.pushFeiShuText(devPushFeiShuTextParam.getContent(), false);
        } else if (DevPushNoticeTypeEnum.ALL.getValue().equals(noticeType)) {
            receiptInfo = DevPushFeiShuUtil.pushFeiShuText(devPushFeiShuTextParam.getContent(), true);
        } else {
            throw new CommonException("不支持的通知类型：{}", noticeType);
        }
        DevPush devPush = new DevPush();
        devPush.setEngine(DevPushEngineTypeEnum.FEISHU.getValue());
        devPush.setType(DevPushMessageTypeEnum.TEXT.getValue());
        devPush.setTitle(devPushFeiShuTextParam.getContent());
        devPush.setContent(devPushFeiShuTextParam.getContent());
        devPush.setReceiptInfo(receiptInfo);
        this.save(devPush);
    }

    @Override
    public void pushDingTalkText(DevPushDingTalkTextParam devPushDingTalkTextParam) {
        String noticeType = devPushDingTalkTextParam.getNoticeType();
        String receiptInfo;
        if (DevPushNoticeTypeEnum.NONE.getValue().equals(noticeType)) {
            receiptInfo = DevPushDingTalkUtil.pushDingTalkText(devPushDingTalkTextParam.getContent(),
                    false, null);
        } else if (DevPushNoticeTypeEnum.ALL.getValue().equals(noticeType)) {
            receiptInfo = DevPushDingTalkUtil.pushDingTalkText(devPushDingTalkTextParam.getContent(),
                    true, null);
        } else if (DevPushNoticeTypeEnum.PHONE.getValue().equals(noticeType)) {
            if (ObjectUtil.isEmpty(devPushDingTalkTextParam.getPhones())) {
                throw new CommonException("手机号不能为空");
            } else {
                receiptInfo = DevPushDingTalkUtil.pushDingTalkText(devPushDingTalkTextParam.getContent(),
                        false, devPushDingTalkTextParam.getPhones());
            }
        } else {
            throw new CommonException("不支持的通知类型：{}", noticeType);
        }
        DevPush devPush = new DevPush();
        devPush.setEngine(DevPushEngineTypeEnum.DINGTALK.getValue());
        devPush.setType(DevPushMessageTypeEnum.TEXT.getValue());
        devPush.setTitle(devPushDingTalkTextParam.getContent());
        devPush.setContent(devPushDingTalkTextParam.getContent());
        devPush.setReceiptInfo(receiptInfo);
        this.save(devPush);
    }

    @Override
    public void pushDingTalkMarkdown(DevPushDingTalkMarkdownParam devPushDingTalkMarkdownParam) {
        String noticeType = devPushDingTalkMarkdownParam.getNoticeType();
        String receiptInfo;
        if (DevPushNoticeTypeEnum.NONE.getValue().equals(noticeType)) {
            receiptInfo = DevPushDingTalkUtil.pushDingTalkMarkdown(devPushDingTalkMarkdownParam.getTitle(),
                    devPushDingTalkMarkdownParam.getContent(), false);
        } else if (DevPushNoticeTypeEnum.ALL.getValue().equals(noticeType)) {
            receiptInfo = DevPushDingTalkUtil.pushDingTalkMarkdown(devPushDingTalkMarkdownParam.getTitle(),
                    devPushDingTalkMarkdownParam.getContent(), true);
        } else {
            throw new CommonException("不支持的通知类型：{}", noticeType);
        }
        DevPush devPush = new DevPush();
        devPush.setEngine(DevPushEngineTypeEnum.DINGTALK.getValue());
        devPush.setType(DevPushMessageTypeEnum.MARKDOWN.getValue());
        devPush.setTitle(devPushDingTalkMarkdownParam.getTitle());
        devPush.setContent(devPushDingTalkMarkdownParam.getContent());
        devPush.setReceiptInfo(receiptInfo);
        this.save(devPush);
    }

    @Override
    public void pushDingTalkLink(DevPushDingTalkLinkParam devPushDingTalkLinkParam) {
        String receiptInfo = DevPushDingTalkUtil.pushDingTalkLink(devPushDingTalkLinkParam.getTitle(),
                devPushDingTalkLinkParam.getContent(),
                devPushDingTalkLinkParam.getPicUrl(),
                devPushDingTalkLinkParam.getMessageUrl());
        DevPush devPush = new DevPush();
        devPush.setEngine(DevPushEngineTypeEnum.DINGTALK.getValue());
        devPush.setType(DevPushMessageTypeEnum.LINK.getValue());
        devPush.setTitle(devPushDingTalkLinkParam.getTitle());
        devPush.setContent(devPushDingTalkLinkParam.getContent());
        devPush.setReceiptInfo(receiptInfo);
        this.save(devPush);
    }

    @Override
    public void pushWorkWechatText(DevPushWorkWechatTextParam devPushWorkWechatTextParam) {
        String noticeType = devPushWorkWechatTextParam.getNoticeType();
        String receiptInfo;
        if (DevPushNoticeTypeEnum.NONE.getValue().equals(noticeType)) {
            receiptInfo = DevPushWorkWechatUtil.pushWorkWechatText(devPushWorkWechatTextParam.getContent(),
                    false, null);
        } else if (DevPushNoticeTypeEnum.ALL.getValue().equals(noticeType)) {
            receiptInfo = DevPushWorkWechatUtil.pushWorkWechatText(devPushWorkWechatTextParam.getContent(),
                    true, null);
        } else if (DevPushNoticeTypeEnum.PHONE.getValue().equals(noticeType)) {
            if (ObjectUtil.isEmpty(devPushWorkWechatTextParam.getPhones())) {
                throw new CommonException("手机号不能为空");
            } else {
                receiptInfo = DevPushWorkWechatUtil.pushWorkWechatText(devPushWorkWechatTextParam.getContent(),
                        false, devPushWorkWechatTextParam.getPhones());
            }
        } else {
            throw new CommonException("不支持的通知类型：{}", noticeType);
        }
        DevPush devPush = new DevPush();
        devPush.setEngine(DevPushEngineTypeEnum.WORKWECHAT.getValue());
        devPush.setType(DevPushMessageTypeEnum.TEXT.getValue());
        devPush.setTitle(devPushWorkWechatTextParam.getContent());
        devPush.setContent(devPushWorkWechatTextParam.getContent());
        devPush.setReceiptInfo(receiptInfo);
        this.save(devPush);
    }

    @Override
    public void pushWorkWechatMarkdown(DevPushWorkWechatMarkdownParam devPushWorkWechatMarkdownParam) {
        String receiptInfo = DevPushWorkWechatUtil.pushWorkWechatMarkdown(devPushWorkWechatMarkdownParam.getTitle(),
                devPushWorkWechatMarkdownParam.getContent());
        DevPush devPush = new DevPush();
        devPush.setEngine(DevPushEngineTypeEnum.WORKWECHAT.getValue());
        devPush.setType(DevPushMessageTypeEnum.MARKDOWN.getValue());
        devPush.setTitle(devPushWorkWechatMarkdownParam.getTitle());
        devPush.setContent(devPushWorkWechatMarkdownParam.getContent());
        devPush.setReceiptInfo(receiptInfo);
        this.save(devPush);
    }

    @Override
    public void pushWorkWechatNews(DevPushWorkWechatNewsParam devPushWorkWechatNewsParam) {
        String receiptInfo = DevPushWorkWechatUtil.pushWorkWechatNews(devPushWorkWechatNewsParam.getTitle(),
                devPushWorkWechatNewsParam.getContent(),
                devPushWorkWechatNewsParam.getPicUrl(),
                devPushWorkWechatNewsParam.getMessageUrl());
        DevPush devPush = new DevPush();
        devPush.setEngine(DevPushEngineTypeEnum.WORKWECHAT.getValue());
        devPush.setType(DevPushMessageTypeEnum.NEWS.getValue());
        devPush.setTitle(devPushWorkWechatNewsParam.getTitle());
        devPush.setContent(devPushWorkWechatNewsParam.getContent());
        devPush.setReceiptInfo(receiptInfo);
        this.save(devPush);
    }

    @Override
    public Page<DevPush> page(DevPushPageParam devPushPageParam) {
        QueryWrapper<DevPush> queryWrapper = new QueryWrapper<DevPush>().checkSqlInjection();
        if (ObjectUtil.isNotEmpty(devPushPageParam.getEngine())) {
            queryWrapper.lambda().eq(DevPush::getEngine, devPushPageParam.getEngine());
        }
        if (ObjectUtil.isNotEmpty(devPushPageParam.getSearchKey())) {
            queryWrapper.lambda().like(DevPush::getTitle, devPushPageParam.getSearchKey());
        }
        if (ObjectUtil.isAllNotEmpty(devPushPageParam.getSortField(), devPushPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(devPushPageParam.getSortOrder());
            queryWrapper.orderBy(true, devPushPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(devPushPageParam.getSortField()));
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<DevPushIdParam> devPushIdParamList) {
        this.removeByIds(CollStreamUtil.toList(devPushIdParamList, DevPushIdParam::getId));
    }

    @Override
    public DevPush detail(DevPushIdParam devPushIdParam) {
        return this.queryEntity(devPushIdParam.getId());
    }

    @Override
    public DevPush queryEntity(String id) {
        DevPush devPush = this.getById(id);
        if (ObjectUtil.isEmpty(devPush)) {
            throw new CommonException("消息推送记录不存在，id值为：{}", id);
        }
        return devPush;
    }
}
