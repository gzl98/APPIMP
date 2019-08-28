package com.dao;

import com.pojo.DevUser;

public interface DevUserDao {

    /**
     * 通过账号获取开发者信息
     *
     * @param devCode 账号
     * @return 开发者对象
     */
    DevUser getDevUserByCode(String devCode);

    /**
     * 用于辅助验证apk名称是否唯一，查询后返回APP的ID值
     *
     * @param APKName apk的名称
     * @return APP的ID值
     */
    Integer checkApkOnly(String APKName);
}
