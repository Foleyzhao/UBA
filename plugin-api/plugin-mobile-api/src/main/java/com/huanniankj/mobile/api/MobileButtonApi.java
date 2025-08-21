package com.huanniankj.mobile.api;

import java.util.List;

/**
 * 移动端按钮API
 *
 * @author happynewyear
 */
public interface MobileButtonApi {

    /**
     * 根据按钮id集合获取按钮码列表
     */
    List<String> listButtonCodeListByIdList(List<String> idList);
}
