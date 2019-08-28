package com.service;

import com.pojo.APPCategory;
import com.pojo.APPInfo;
import com.pojo.BackendUser;

import java.util.List;

public interface BackendUserService {

    /**
     * 通过账号获取管理员信息
     *
     * @param userCode 账号
     * @return 管理员对象
     */
    BackendUser getBackendUserByCode(String userCode);

    /**
     * 通过目录名获取下一级目录的列表
     *
     * @param categoryName 目录名
     * @return 下一级目录的List
     */
    List<APPCategory> getChildAPPCategoryList(String categoryName);

    /**
     * 综合查询APP信息
     *
     * @param appInfo 查询条件的包装对象
     * @return 符合条件的集合
     */
    List<APPInfo> getAPPListByAttr(APPInfo appInfo);

}
