package com.dao;

import com.pojo.APPInfo;
import com.pojo.APPVersion;

import java.util.List;

import com.pojo.APPInfo;
import com.pojo.DevUser;
import com.sun.deploy.ui.AppInfo;
import org.apache.ibatis.annotations.Param;
import sun.text.normalizer.VersionInfo;

import java.util.List;

public interface DevUserDao {
    void addAPPInfo(APPInfo appInfo);
    void addAPPVersion(APPVersion appVersion);
    void deleteAPPInfo(String appid);
    void deleteAPPVersion(String appid);
    List<APPInfo> getAPPInfo(String appId);
    List<APPVersion> getAPPVersion(String appId);
    List<APPVersion> getAPPVersionByVersionNo(String versionNo);

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

    /**
     * 改变上架、下架的状态
     *
     * @param appInfo 修改条件的包装对象
     * @return 修改是否成功（1：成功，0失败）
     */
    Integer changeShelfStatus(APPInfo appInfo);

    /**
     * 修改appinfo的version id
     * @param appId
     * @param appVersionId
     */
    void updateVersionId(@Param("appId")Integer appId,@Param("appVersionId")Integer appVersionId);

    /**
     * 通过appid,verionno来查询versionid
     * @param appId
     * @param versionNo
     * @return
     */
    List<Integer> getVersionId(@Param("appId")Integer appId, @Param("versionNo")String versionNo);

    /**
     * 更新apkname更新apkinfo
     * @param appInfo
     */
    void updateAPPInfo(APPInfo appInfo);

    /**
     * 根据verionid更新appversion
     * @param appVersion
     */
    void updateAPPLatestVersion(APPVersion appVersion);
}
