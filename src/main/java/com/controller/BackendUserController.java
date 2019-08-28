package com.controller;

import com.pojo.APPInfo;
import com.pojo.BackendUser;
import com.service.BackendUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/BackendUser")
public class BackendUserController {

    private final BackendUserService backendUserService;

    @Autowired
    public BackendUserController(BackendUserService backendUserService) {
        this.backendUserService = backendUserService;
    }

    /**
     * 管理员登录验证
     */
    @RequestMapping("/backLogin")
    @ResponseBody
    public Map<String, Object> getBackendUserByCode(HttpServletRequest request) {
        String userCode = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(userCode + "   " + password);
        BackendUser backendUser = backendUserService.getBackendUserByCode(userCode);
        Map<String, Object> map = new HashMap<>();
        if (backendUser != null) {
            if (backendUser.getUserPassword().equals(password)) {
                map.put("success", 1);
                map.put("user", backendUser);
            } else {
                map.put("success", 0);
                map.put("message", "密码错误");
            }
        } else {
            map.put("success", 0);
            map.put("message", "账号不存在");
        }
        return map;
    }

    /**
     * 通过目录名获取下一级目录的列表
     */
    @RequestMapping("/categoryChange")
    @ResponseBody
    public Map<String, Object> categoryChange(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", backendUserService.getChildAPPCategoryList(request.getParameter("categoryName")));
        return map;
    }

    /**
     * 综合查询APP信息
     */
    @RequestMapping("/getAPPListByAttr")
    @ResponseBody
    public Map<String, Object> getAPPListByAttr(HttpServletRequest request) {
        APPInfo appInfo = new APPInfo();
        appInfo.setSoftwareName(request.getParameter("softwareName"));
//        appInfo.setSoftwareName(request.getParameter("softwareName"));
        appInfo.setStatus(Integer.valueOf(request.getParameter("appStatus")));
        appInfo.setFlatformId(Integer.valueOf(request.getParameter("appFlatForm")));
        appInfo.setDevId(Integer.valueOf(request.getParameter("userId")));
        Map<String, Object> map = new HashMap<>();
        map.put("data", backendUserService.getAPPListByAttr(appInfo));
        return map;
    }

    /**
     * 管理员审核
     */
    @RequestMapping("/checkAPP")
    @ResponseBody
    public Map<String, Object> checkAPP(HttpServletRequest request) {
        APPInfo appInfo = new APPInfo();
        appInfo.setAppId(Integer.valueOf(request.getParameter("appId")));
        appInfo.setStatus(Integer.valueOf(request.getParameter("appStatus")));
        Map<String, Object> map = new HashMap<>();
        map.put("success", backendUserService.checkAPP(appInfo));
        return map;
    }

}
