package com.service;

import com.pojo.DevUser;

public interface DevUserService {

    /**
     * 通过账号获取开发者信息
     *
     * @param userCode 账号
     * @return 开发者对象
     */
    DevUser getDevUserByCode(String userCode);

    /**
     * 用于辅助验证apk名称是否唯一
     *
     * @param APKName apk的名称
     * @return 返回TRUE则为唯一
     */
    Boolean checkApkOnly(String APKName);

}
