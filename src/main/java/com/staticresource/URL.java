package com.staticresource;

import javax.servlet.http.HttpServletRequest;
import java.io.File;


/**
 * 获取URL的工具类的
 */
public class URL {
    public static final String rootUrl = "image";
    public static final String dri = "/";


    /**
     * 获取服务器文件存储路径
     */
    public static String getServerPath(HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("/") + rootUrl;
        File file = new File(path);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        path += URL.dri;
        return path;
    }
}
