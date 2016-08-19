package com.platform.soft.base.resource;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ImagePathFactory {
    private static final String NEWS_IMAGE_PATH = "news/pic/";
    private static final Map<String, String> IMAGE_PATH_MAP = new HashMap();

    static {
        IMAGE_PATH_MAP.put("news", NEWS_IMAGE_PATH);
    }


    public static String getImagePath(String pt) {
        return IMAGE_PATH_MAP.get(pt) + getDate("yyyyMMdd") + "/";
    }

    public static String getWholeImagePath(String pt, String imageName) {
        String fileName = "";

        if (StringUtils.isNotEmpty(imageName)) {
            fileName = imageName.substring(imageName.lastIndexOf("."));
         /*   if(fileName.equals(".jpeg") || fileName.equals(".JPEG")){
                fileName=".jpg";
            }*/

        }else {
           fileName=".jpg";
        }
        return getImagePath(pt) +/* "/" +*/ getDate("HHmmssS")+fileName;
    }

    public static String getDate(String fmt) {
        SimpleDateFormat formatter = new SimpleDateFormat(fmt);
        return formatter.format(new Date());
    }

    public static String getDefaultHeader() {
        return  "images/front/header.png";
    }

    public static String getDefaultLandByImage() {
        return "images/front/land.png";
    }
}
