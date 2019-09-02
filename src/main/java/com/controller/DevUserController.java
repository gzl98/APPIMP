package com.controller;

import com.pojo.APPInfo;
import com.pojo.APPVersion;
import com.pojo.DevUser;
import com.service.DevUserService;
import com.utils.ConstVar;
import com.utils.FileUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    @CrossOrigin
    public Map<String, Object> getDevUserByCode(HttpServletRequest request) {
        String userCode = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(userCode + "   " + password);
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
    @CrossOrigin
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
    @CrossOrigin
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

    /**
     * 增加App信息
     *
     * @param request
     * @return map
     */
    @RequestMapping("/addAPPInfo")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> addAPPInfo(HttpServletRequest request) {
        APPInfo appInfo = new APPInfo();
        appInfo.setSoftwareName(request.getParameter("softwareName"));
        appInfo.setAPKName(request.getParameter("apkName"));
        appInfo.setSupportROM(request.getParameter("supportROM"));
        appInfo.setInterfaceLanguage(request.getParameter("interfaceLanguage"));
        appInfo.setSoftwareSize(Double.valueOf(request.getParameter("softwareSize")));
        appInfo.setDownloads(Integer.valueOf(request.getParameter("downloads")));
        appInfo.setFlatformId(Integer.valueOf(request.getParameter("flatFormId")));
        appInfo.setCategoryLevel1(Integer.valueOf(request.getParameter("categoryLevel1")));
        appInfo.setCategoryLevel2(Integer.valueOf(request.getParameter("categoryLevel2")));
        appInfo.setCategoryLevel3(Integer.valueOf(request.getParameter("categoryLevel3")));
        appInfo.setStatus(Integer.valueOf(request.getParameter("status")));
        appInfo.setAppInfo(request.getParameter("appInfo"));
        appInfo.setLogoPicPath(request.getParameter("logoPicPath"));
        appInfo.setDevId(Integer.valueOf(request.getParameter("userId")));
        Map<String, Object> map = new HashMap<>();
        if (devUserService.addAPPInfo(appInfo))
            map.put("success", 1);
        else
            map.put("success", 0);
        return map;
    }

    /**
     * 增加版本信息
     */
    @RequestMapping("/addAPPVersion")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> addAPPVersion(HttpServletRequest request) {
        APPVersion appVersion = new APPVersion();
        appVersion.setVersionNo(request.getParameter("versionNo"));
        appVersion.setAppId(Integer.valueOf(request.getParameter("appId")));
        appVersion.setCreatedBy(Integer.valueOf(request.getParameter("userId")));
        appVersion.setPublishStatus(Integer.valueOf(request.getParameter("publishStatus")));
        appVersion.setVersionSize(Double.valueOf(request.getParameter("versionSize")));
        appVersion.setVersionInfo(request.getParameter("versionInfo"));
        appVersion.setApkLocPath(request.getParameter("apkLocPath"));
        Map<String, Object> map = new HashMap<>();
        if (devUserService.addAPPVersion(appVersion))
            map.put("success", 1);
        else
            map.put("success", 0);
        return map;
    }

    /**
     * 删除APP
     */
    @RequestMapping("/deleteAPPInfo")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> deleteAPPInfo(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String appid = request.getParameter("appId");
        if (devUserService.deleteAPPInfo(appid)) {
            map.put("success", 1);
        } else {
            map.put("success", 0);
        }
        return map;
    }

    /**
     * 查看APP信息
     */
    @RequestMapping("/getAPPInfo")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> getAPPInfo(HttpServletRequest request) {
        String appId = request.getParameter("appId");
        Map<String, Object> map = new HashMap<>();
        APPInfo appInfo = devUserService.getAPPInfo(appId);
        List<APPVersion> appVersions = devUserService.getAPPVersion(appId);
        map.put("appInfo", appInfo);
        map.put("version", appVersions);
        return map;
    }

    /**
     * 文件上传
     */
    @RequestMapping("/submitFile")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> submitFile(HttpServletRequest request) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        Map<String, Object> map = new HashMap<>();
        upload.setHeaderEncoding("utf-8");

        try {
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) { //保存文件
                if (!item.isFormField()) {
                    String file_name = item.getName();
                    if (file_name != null && !file_name.equals("")) {
                        String extension = FilenameUtils.getExtension(file_name);
                        String save_path, file_link;
                        if (extension.equals("apk")) {
                            save_path = request.getServletContext().getRealPath("files/apk_files");
                            file_link = new StringBuilder().append(ConstVar.domain).append("/").
                                    append(request.getContextPath()).append("/files/apk_files/").append(file_name).toString();
                        } else {
                            save_path = request.getServletContext().getRealPath("files/image_files");
                            file_link = new StringBuilder().append(ConstVar.domain).append("/").
                                    append(request.getContextPath()).append("/files/image_files/").append(file_name).toString();
                        }
                        FileUtils.create_dir(save_path);
                        try {
                            InputStream in = item.getInputStream();
                            String file_path = save_path + "\\" + file_name;
                            FileOutputStream out = new FileOutputStream(file_path);
                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = in.read(buffer)) > 0) {
                                out.write(buffer, 0, len);
                            }
                            in.close();
                            out.close();
                            item.delete();
                            map.put("fileLink", file_link);
                            map.put("sucess", 1);
                            return map; //save file successfully
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        map.put("fileLink", "");
        map.put("success", 0);
        return map;
    }

    /**
     *修改APP基础信息（根据apk名称修改）
     */
    @RequestMapping("/updateAPPInfo")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> updateAPPInfo(HttpServletRequest request) {
        APPInfo appInfo=new APPInfo();
        Map<String, Object> map = new HashMap<>();
        appInfo.setAPKName(request.getParameter("apkName"));
        appInfo.setSoftwareName(request.getParameter("softwareName"));
        appInfo.setSupportROM(request.getParameter("supportROM"));
        appInfo.setInterfaceLanguage(request.getParameter("interfaceLanguage"));
        appInfo.setSoftwareSize(Double.valueOf(request.getParameter("softwareSize")));
        appInfo.setDownloads(Integer.valueOf(request.getParameter("downloads")));
        appInfo.setFlatformId(Integer.valueOf(request.getParameter("flatFormId")));
        appInfo.setStatus(Integer.valueOf(request.getParameter("status")));
        appInfo.setCategoryLevel1(Integer.valueOf(request.getParameter("categoryLevel1")));
        appInfo.setCategoryLevel2(Integer.valueOf(request.getParameter("categoryLevel2")));
        appInfo.setCategoryLevel3(Integer.valueOf(request.getParameter("categoryLevel3")));
        appInfo.setAppInfo(request.getParameter("appInfo"));
        appInfo.setLogoPicPath(request.getParameter("logoPicPath"));
        appInfo.setModifyBy(Integer.valueOf(request.getParameter("userId")));
        appInfo.setModifyDate(new Date());
        if (devUserService.updateAPPInfo(appInfo))
            map.put("success", 1);
        else
            map.put("success", 0);
        return map;
    }

    /**
     * 修改APP最新版本信息
     * @param request
     * @return
     */
    @RequestMapping("/updateAPPLatestVersion")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> updateAPPLatestVersion(HttpServletRequest request) {
        APPVersion version=new APPVersion();
        version.setAppVersionId(Integer.valueOf(request.getParameter("versionId")));
        version.setModifyBy(Integer.valueOf(request.getParameter("userId")));
        version.setVersionSize(Double.valueOf(request.getParameter("versionSize")));
        version.setVersionInfo(request.getParameter("versionInfo"));
        version.setApkLocPath(request.getParameter("apkLocPath"));
        version.setModifyDate(new Date());
        Map<String, Object> map = new HashMap<>();

        if(devUserService.updateAPPLatestVersion(version))
            map.put("success", 1);
        else
            map.put("success", 0);
        return map;
    }
}
