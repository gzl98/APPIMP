package com.controller;

import com.pojo.APPInfo;
import com.pojo.APPVersion;
import com.service.DevUserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;


@Controller
@RequestMapping("/DevUser")
public class DevUserController {

    private final DevUserService devUserService;

    @Autowired
    public DevUserController(DevUserService devUserService) {
        this.devUserService = devUserService;
    }

    /**
     * 增加App信息
     * @param request
     * @return map
     */
    @RequestMapping("/addAPPInfo")
    @ResponseBody
    public Map<String,Object> addAPPInfo(HttpServletRequest request){
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
        if(devUserService.addAPPInfo(appInfo))
            map.put("success",1);
        else
            map.put("success",0);
        return  map;
    }

    /**
     * 增加版本信息
     */
    @RequestMapping("/addAPPVersion")
    @ResponseBody
    public Map<String,Object> addAPPVersion(HttpServletRequest request){
        APPVersion appVersion=new APPVersion();
        appVersion.setAppVersionId(Integer.valueOf(request.getParameter("versionNo")));
        appVersion.setAppId(Integer.valueOf(request.getParameter("appId")));
        appVersion.setCreatedBy(Integer.valueOf(request.getParameter("userId")));
        appVersion.setPublishStatus(Integer.valueOf(request.getParameter("publishStatus")));
        appVersion.setVersionSize(Double.valueOf(request.getParameter("versionSize")));
        appVersion.setVersionInfo(request.getParameter("versionInfo"));
        appVersion.setApkLocPath(request.getParameter("versionId"));
        Map<String, Object> map = new HashMap<>();
        if(devUserService.addAPPVersion(appVersion))
            map.put("success",1);
        else
            map.put("success",0);
        return  map;
    }

    /**
     * 删除APP
     */
    @RequestMapping("/deleteAPPInfo")
    @ResponseBody
    public Map<String, Object> deleteAPPInfo(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        String appid=request.getParameter("appId");
        if (devUserService.deleteAPPInfo(appid)){
            map.put("success",1);
        }
        else {
            map.put("success",0);
        }
        return  map;
    }

    /**
     *查看APP信息
     */
    @RequestMapping("/getAPPInfo")
    @ResponseBody
    public Map<String, Object> getAPPInfo(HttpServletRequest request){
        String appId=request.getParameter("appId");
        Map<String, Object> map = new HashMap<>();
        APPInfo appInfo=devUserService.getAPPInfo(appId);
        List<APPVersion> appVersions=devUserService.getAPPVersion(appId);
        map.put("appInfo",appInfo);
        map.put("version",appVersions);
        return map;
    }

    /**
     * 文件上传
     */
    @RequestMapping("/submitFile")
    @ResponseBody
    public Map<String, Object> submitFile(HttpServletRequest request){
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        Map<String, Object> map = new HashMap<>();
        upload.setHeaderEncoding("utf-8");

        try {
            List<FileItem> list= upload.parseRequest(request);
            for (FileItem item:list){ //保存文件
                if(!item.isFormField()){
                    String file_name = item.getName();
                    if (file_name != null && !file_name.equals("")) {
                        String extension = FilenameUtils.getExtension(file_name);
                        String save_path,file_link;
                        if (extension.equals("apk")){
                            save_path=request.getServletContext().getRealPath("files/apk_files");
                            file_link=request.getContextPath()+"/files/apk_files/"+file_name;
                        }
                        else{
                            save_path=request.getServletContext().getRealPath("files/image_files");
                            file_link=request.getContextPath()+"/files/apk_files/"+file_name;
                        }
                        create_dir(save_path);
                        try {
                            InputStream in = item.getInputStream();
                            String file_path=save_path+"\\"+file_name;
                            FileOutputStream out = new FileOutputStream(file_path);
                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len=in.read(buffer)) > 0){
                                out.write(buffer,0,len);
                            }
                            in.close();
                            out.close();
                            item.delete();
                            map.put("fileLink",file_link);
                            map.put("sucess",1);
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
        map.put("fileLink","");
        map.put("success",0);
        return map;
    }

    private void create_dir(String dir_name){
        File f=new File(dir_name);
        if(!f.exists()){
            f.mkdirs();
        }
    }
}
