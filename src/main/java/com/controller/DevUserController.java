package com.controller;

import com.pojo.APPInfo;
import com.pojo.DevUser;
import com.service.DevUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/DevUser")
public class DevUserController {

    private final DevUserService devUserService;

    @Autowired
    public DevUserController(DevUserService devUserService) {
        this.devUserService = devUserService;
    }


    /**
     * 开发者登录验证
     */
    @RequestMapping("/devLogin")
    @ResponseBody
    public Map<String, Object> getDevUserByCode(HttpServletRequest request) {
        String userCode = request.getParameter("username");
        String password = request.getParameter("password");
        DevUser devUser = devUserService.getDevUserByCode(userCode);
        Map<String, Object> map = new HashMap<>();
        if (devUser != null) {
            if (devUser.getDevPassword().equals(password)) {
                map.put("success", 1);
                map.put("user", devUser);
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
     * 验证APK名称的唯一性
     */
    @RequestMapping("/checkApkOnly")
    @ResponseBody
    public Map<String, Object> checkApkOnly(HttpServletRequest request) {
        String apkName = request.getParameter("apkName");
        Map<String, Object> map = new HashMap<>();
        if (devUserService.checkApkOnly(apkName)) {
            map.put("only", 1);
        } else {
            map.put("only", 0);
        }
        return map;
    }

    /**
     * 改变上架、下架的状态
     */
    @RequestMapping("/changeShelfStatus")
    @ResponseBody
    public Map<String, Object> changeShelfStatus(HttpServletRequest request) {
        Integer appId = Integer.valueOf(request.getParameter("appId"));
        Integer appStatus = Integer.valueOf(request.getParameter("appStatus"));
        APPInfo appInfo = new APPInfo();
        appInfo.setAppId(appId);
        appInfo.setStatus(appStatus);
        Map<String, Object> map = new HashMap<>();
        map.put("success", devUserService.changeShelfStatus(appInfo));
        return map;
    }

}
