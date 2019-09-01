package com.service.impl;

import com.dao.BackendUserDao;
import com.pojo.APPCategory;
import com.pojo.APPInfo;
import com.pojo.BackendUser;
import com.service.BackendUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackendUserServiceImpl implements BackendUserService {

    private final BackendUserDao backendUserDao;

    @Autowired
    public BackendUserServiceImpl(BackendUserDao backendUserDao) {
        this.backendUserDao = backendUserDao;
    }


    /**
     * 通过账号获取管理员信息
     *
     * @param userCode 账号
     * @return 管理员对象
     */
    @Override
    public BackendUser getBackendUserByCode(String userCode) {
        return backendUserDao.getBackendUserByCode(userCode);
    }

    /**
     * 通过目录Id取下一级目录的列表
     *
     * @param categoryId 目录Id
     * @return 下一级目录的List
     */
    public List<APPCategory> getChildAPPCategoryList(Integer categoryId) {
        return backendUserDao.getChildAPPCategoryList(categoryId);
    }


    /**
     * 综合查询APP信息
     *
     * @param appIds appId的集合
     * @param offset 分页的偏移量
     * @param limit  每页的限制条数，即数据库每次的查询条数
     * @return 符合条件的集合
     */
    public List<APPInfo> getAPPListByAttr(@Param("appIds") Integer[] appIds, @Param("offset") Integer offset, @Param("limit") Integer limit) {
        return backendUserDao.getAPPListByAttr(appIds, offset, limit);
    }

    /**
     * 综合查询APP数量
     *
     * @param appInfo 修改条件的包装对象
     * @return 符合条件的APP ID集合
     */
    public List<Integer> getAPPCountByAttr(APPInfo appInfo) {
        return backendUserDao.getAPPCountByAttr(appInfo);
    }

    /**
     * 管理员审核APP
     *
     * @param appInfo 修改条件的包装对象
     * @return 审核是否成功（1：成功，0失败）
     */
    public Integer checkAPP(APPInfo appInfo) {
        try {
            backendUserDao.checkAPP(appInfo);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }
}
