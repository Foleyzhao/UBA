package com.huanniankj.sys.modular.index.result;

import lombok.Getter;
import lombok.Setter;

/**
 * 基础工具数据
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysToolDataCountResult {

    /**
     * 文件数量
     */
    private Long fileCount;

    /**
     * 短信数量
     */
    private Long smsCount;

    /**
     * 邮件数量
     */
    private Long emailCount;

    /**
     * 站内信数量
     */
    private Long messageCount;
}
