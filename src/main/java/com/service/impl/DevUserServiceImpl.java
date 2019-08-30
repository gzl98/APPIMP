package com.service.impl;

import com.dao.DevUserDao;
import com.pojo.APPInfo;
import com.pojo.APPVersion;
import com.pojo.BackendUser;
import com.service.DevUserService;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;
import java.util.List;

@Service
public class DevUserServiceImpl implements DevUserService {

    private final DevUserDao devUserDao;

    @Autowired
    public DevUserServiceImpl(DevUserDao devUserDao) {
        this.devUserDao = devUserDao;
    }

    @Override
    public boolean addAPPInfo(APPInfo appInfo) {
        appInfo.setCreatedBy(appInfo.getDevId());
        appInfo.setCreationDate(new Date());
        appInfo.setVersionId(1);
        try {
            devUserDao.addAPPInfo(appInfo);
            return true;
        }catch (SqlSessionException e){
            e.printStackTrace();
            return  false;
        }
    }

    @Override
    public boolean addAPPVersion(APPVersion appVersion) {
        appVersion.setCreationDate(new Date());
        try {
            List<APPVersion> appVersions=devUserDao.getAPPVersionByVersionNo(appVersion.getVersionNo());
            if (appVersions.size()==0)
                return false;
            devUserDao.addAPPVersion(appVersion);
            return true;
        }
        catch (SqlSessionException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAPPInfo(String appid) {
        try{
            devUserDao.deleteAPPInfo(appid);
            return true;
        }catch (SqlSessionException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public APPInfo getAPPInfo(String appId) {
        List<APPInfo> appInfos=devUserDao.getAPPInfo(appId);
        if (appInfos!=null && appInfos.size()!=0)
            return appInfos.get(0);
        else
            return null;
    }

    @Override
    public List<APPVersion> getAPPVersion(String appId) {
        return devUserDao.getAPPVersion(appId);
    }
}
