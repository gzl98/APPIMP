package com.service;

import com.pojo.APPInfo;
import com.pojo.APPVersion;
import com.pojo.BackendUser;

import java.util.List;

public interface DevUserService {
    boolean addAPPInfo(APPInfo appInfo);
    boolean addAPPVersion(APPVersion appVersion);
    boolean deleteAPPInfo(String appid);
    APPInfo getAPPInfo(String appId);
    List<APPVersion> getAPPVersion(String appId);
}
