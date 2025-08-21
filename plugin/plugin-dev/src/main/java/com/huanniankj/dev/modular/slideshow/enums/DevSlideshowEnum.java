package com.huanniankj.dev.modular.slideshow.enums;

import lombok.Getter;

/**
 * 轮播图枚举
 *
 * @author happynewyear
 */
@Getter
public enum DevSlideshowEnum {

    /**
     * 测试
     */
    TEST("TEST");

    private final String value;

    DevSlideshowEnum(String value) {
        this.value = value;
    }
}
