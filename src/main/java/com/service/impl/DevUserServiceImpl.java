package com.service.impl;

import com.dao.DevUserDao;
import com.pojo.APPInfo;
import com.pojo.APPVersion;
import com.pojo.BackendUser;
import com.pojo.APPInfo;
import com.pojo.DevUser;
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
            if (appVersions.size()!=0)
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
    /**
     * 通过账号获取开发者信息
     *
     * @param userCode 账号
     * @return 开发者对象
     */
    public DevUser getDevUserByCode(String userCode) {
        return devUserDao.getDevUserByCode(userCode);
    }

    /**
     * 用于辅助验证apk名称是否唯一
     *
     * @param APKName apk的名称
     * @return 返回TRUE则为唯一
     */
    public Boolean checkApkOnly(String APKName) {
        return devUserDao.checkApkOnly(APKName) == null;
    }

    /**
     * 改变上架、下架的状态
     *
     * @param appInfo 修改条件的包装对象
     * @return 修改是否成功（1：成功，0失败）
     */
    public Integer changeShelfStatus(APPInfo appInfo) {
        try {
            devUserDao.changeShelfStatus(appInfo);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }
}
