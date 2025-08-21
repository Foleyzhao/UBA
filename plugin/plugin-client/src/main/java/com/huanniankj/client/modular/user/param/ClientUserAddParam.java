package com.huanniankj.client.modular.user.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户添加参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class ClientUserAddParam {

    /**
     * 账号
     */
    @Schema(description = "账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "account不能为空")
    private String account;

    /**
     * 姓名
     */
    @Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "name不能为空")
    private String name;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

    /**
     * 头像
     */
    @Schema(description = "头像，图片base64")
    private String avatar;

    /**
     * 签名
     */
    @Schema(description = "签名，图片base64")
    private String signature;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private String gender;

    /**
     * 年龄
     */
    @Schema(description = "年龄")
    private String age;

    /**
     * 出生日期
     */
    @Schema(description = "出生日期")
    private String birthday;

    /**
     * 民族
     */
    @Schema(description = "民族")
    private String nation;

    /**
     * 籍贯
     */
    @Schema(description = "籍贯")
    private String nativePlace;

    /**
     * 家庭住址
     */
    @Schema(description = "家庭住址")
    private String homeAddress;

    /**
     * 通信地址
     */
    @Schema(description = "通信地址")
    private String mailingAddress;

    /**
     * 证件类型
     */
    @Schema(description = "证件类型")
    private String idCardType;

    /**
     * 证件号码
     */
    @Schema(description = "证件号码")
    private String idCardNumber;

    /**
     * 文化程度
     */
    @Schema(description = "文化程度")
    private String cultureLevel;

    /**
     * 政治面貌
     */
    @Schema(description = "政治面貌")
    private String politicalOutlook;

    /**
     * 毕业院校
     */
    @Schema(description = "毕业院校")
    private String college;

    /**
     * 学历
     */
    @Schema(description = "学历")
    private String education;

    /**
     * 学制
     */
    @Schema(description = "学制")
    private String eduLength;

    /**
     * 学位
     */
    @Schema(description = "学位")
    private String degree;

    /**
     * 手机
     */
    @Schema(description = "手机")
    private String phone;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 家庭电话
     */
    @Schema(description = "家庭电话")
    private String homeTel;

    /**
     * 办公电话
     */
    @Schema(description = "办公电话")
    private String officeTel;

    /**
     * 紧急联系人
     */
    @Schema(description = "紧急联系人")
    private String emergencyContact;

    /**
     * 紧急联系人电话
     */
    @Schema(description = "紧急联系人电话")
    private String emergencyPhone;

    /**
     * 紧急联系人地址
     */
    @Schema(description = "紧急联系人地址")
    private String emergencyAddress;

    /**
     * 排序码
     */
    @Schema(description = "排序码")
    private Integer sortCode;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    private String extJson;
}
