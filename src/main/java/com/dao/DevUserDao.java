package com.dao;

import com.pojo.APPInfo;
import com.pojo.APPVersion;

import java.util.List;

public interface DevUserDao {
    void addAPPInfo(APPInfo appInfo);
    void addAPPVersion(APPVersion appVersion);
    void deleteAPPInfo(String appid);
    List<APPInfo> getAPPInfo(String appId);
    List<APPVersion> getAPPVersion(String appId);
    List<APPVersion> getAPPVersionByVersionNo(String versionNo);
}
