package com.service.impl;

import com.dao.DevUserDao;
import com.pojo.APPInfo;
import com.pojo.DevUser;
import com.service.DevUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevUserServiceImpl implements DevUserService {

    private final DevUserDao devUserDao;

    @Autowired
    public DevUserServiceImpl(DevUserDao devUserDao) {
        this.devUserDao = devUserDao;
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
