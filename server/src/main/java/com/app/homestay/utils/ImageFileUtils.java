package com.app.homestay.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ImageFileUtils {


    public static String loadImg(MultipartFile file){
        String result=null;//上传结果信息

        if (file.getSize()/1000>1024){
            result="图片大小不能超过1MB";
        }else {
            //判断上传文件格式
            String fileType =file.getContentType();
            if (fileType.contains("jpeg") || fileType.contains("png")){
                //获取文件名
                String fileName = file.getOriginalFilename();
                //获取文件后缀名
                int index = fileName.lastIndexOf(".");
                String suffixName;
                if(index > 0){
                    suffixName = fileName.substring(fileName.lastIndexOf("."));
                }else {
                    suffixName = ".png";
                }
                //重新生成文件名
                fileName = UUID.randomUUID()+suffixName;
                // 获取服务器路径(springboot虚拟服务器文件不适用)
                // String realPath = request.getServletContext().getRealPath("img");//文件的上传路径
                //获取项目路径
                Resource resource = new ClassPathResource("");
                try {
                    String projectPath = resource.getFile().getAbsolutePath()+ "\\static\\img";
                    if (upload(projectPath,file,fileName)){
                        //文件存放的相对路径(一般存放在数据库用于img标签的src)
                        return "img/"+fileName;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                result="图片格式不正确";
            }
        }

        return result;
    }

    private static  boolean upload(String realPath,MultipartFile file,String fileName){
        // 将img文件存入本地
        String path = realPath + "\\" + fileName;
        System.out.println("upload-------------->"+path );

        File dest = new File(path);
//        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            boolean b = dest.getParentFile().mkdir();
            if(!b){
                return b;
            }
        }
        //保存文件
        try {
            file.transferTo(dest);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
