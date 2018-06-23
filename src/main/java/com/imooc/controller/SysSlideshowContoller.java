package com.imooc.controller;


import com.imooc.controller.web.WebContoller;
import com.imooc.pojo.IMoocJSONResult;
import com.imooc.pojo.SysSlideshow;
import com.imooc.service.SysSlideshowService;
import com.staticresource.URL;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/slideshow/")
public class SysSlideshowContoller extends WebContoller {

    @Resource
    SysSlideshowService sysSlideshowService;


    @PostMapping("uploadFile")
    public IMoocJSONResult uploadFile() {
        String path = URL.getServerPath(request);
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    String fileName = file.getOriginalFilename() + URL.dri + UUID.randomUUID();
                    try {
                        file.transferTo(new File(path + fileName));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    /**
                     * 数据库操作（增加轮播图数据）
                     * */
                    SysSlideshow sysSlideshow = new SysSlideshow();
                    sysSlideshow.setId(getId());
                    sysSlideshow.setUrl(fileName);
                    sysSlideshow.setCreateUserId(getUserId());
                    sysSlideshow.setUpdateUserId(getUserId());
                    sysSlideshowService.saveSysSlideshow(sysSlideshow);
                }
            }
        } else {
            return IMoocJSONResult.ok("上传文件失败");
        }
        return IMoocJSONResult.ok("上传文件成功");
    }

    @PostMapping("downloadFile")
    public IMoocJSONResult downloadFile(SysSlideshow sysSlideshow) {
        String path = URL.getServerPath(request);
        String url = path + sysSlideshow.getUrl();
        File file = new File(url);
        if (!file.isFile()) {
            return IMoocJSONResult.errorMsg("不存在这个文件");
        }
        InputStream inputStream = null;
        ServletOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(URL.rootUrl + file);
            outputStream = response.getOutputStream();
            IOUtils.copy(inputStream, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return IMoocJSONResult.ok("下载文件成功");
    }


    @PostMapping("deleteFile")
    public IMoocJSONResult deleteFile(String sysSlideshowId) {
        if (StringUtils.isEmpty(sysSlideshowId)) {
            return IMoocJSONResult.errorMsg("删除失败");
        }
        sysSlideshowService.deleteSysSlideshow(sysSlideshowId);
        return IMoocJSONResult.ok("删除成功");
    }

    /**
     * 系统轮播图分页查询列表
     */
    @PostMapping("querySysSlideshowListPaged")
    public IMoocJSONResult querySysSlideshowListPaged(SysSlideshow sysSlideshow, Integer page) {
        if (page == null) {
            page = 1;
        }

        List<SysSlideshow> list = sysSlideshowService.querySysSlideshowListPaged(sysSlideshow, page, pageSize);
        int count = sysSlideshowService.selectCount(sysSlideshow);
        int totalPages = (double) count / 10 > count / 10 ? (count / 10) + 1 : count / 10;

        Map result = new HashMap<>();
        result.put("totalPages", totalPages);
        result.put("list", list);
        result.put("count", count);
        result.put("page", page);

        return IMoocJSONResult.ok(result);
    }

    /**
     * 增加系统轮播图
     */
    @PostMapping("addSlideshow")
    public IMoocJSONResult addSlideshow(SysSlideshow sysSlideshow) {
        if (sysSlideshow.getUrl().length() < 1) {
            return IMoocJSONResult.errorMsg("上传路径不能为空");
        }
        sysSlideshow.setId(getId());
        sysSlideshow.setCreateUserId(getUserId());
        sysSlideshow.setUpdateUserId(getUserId());
        sysSlideshowService.saveSysSlideshow(sysSlideshow);
        return IMoocJSONResult.ok("上传成功");
    }

    /**
     * 修改系统轮播图
     */
    @PostMapping("updateSlideshow")
    public IMoocJSONResult updateSlideshow(SysSlideshow sysSlideshow) {
        sysSlideshowService.updateSysSlideshow(sysSlideshow);
        return IMoocJSONResult.ok("修改成功");
    }

    /**
     * 根据ID获取轮播图对象
     */
    @PostMapping("getSlideshow")
    public IMoocJSONResult getSlideshow(String id) {
        if (StringUtils.isEmpty(id)) {
            return IMoocJSONResult.errorMsg("上传路径不能为空");
        }
        return IMoocJSONResult.ok(sysSlideshowService.querySysSlideshowById(id));
    }
}
