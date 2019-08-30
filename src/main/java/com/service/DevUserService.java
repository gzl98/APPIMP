package com.service;

import com.pojo.APPInfo;
import com.pojo.APPVersion;
import com.pojo.BackendUser;
import com.pojo.APPInfo;
import com.pojo.DevUser;

import java.util.List;

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

    /**
     * 改变上架、下架的状态
     *
     * @param appInfo 修改条件的包装对象
     * @return 修改是否成功（1：成功，0失败）
     */
    Integer changeShelfStatus(APPInfo appInfo);

    boolean addAPPInfo(APPInfo appInfo);
    boolean addAPPVersion(APPVersion appVersion);
    boolean deleteAPPInfo(String appid);
    APPInfo getAPPInfo(String appId);
    List<APPVersion> getAPPVersion(String appId);
}
