package com.jt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author 刘昱江
 * 时间 2021/1/25
 */
@RestController     //表示返回的数据为JSON
public class FileController {

    /**
     * url地址:/file
     * 参数: fileImage
     * 返回值: 返回成功的提示信息
     *
     * 知识回顾:    IO流的操作的方式  作业
     *  1.低级流  FileInputStreamFile/FileOutputStream
     *  2.缓存流  BufferedInputStream/BufferedOutputStream
     *  3.高级流字符流  Reader/Writer
     *
     *  最大的困难:   1.API不好记.....  多
     *               2.代码的格式 不好理解
     *               3.经常性的出现不关流的失误.
     *
     *  MultipartFile: SpringMVC专对文件的上传开发的API
     *                 默认的最大只允许上传1M的数据
     * @return
     */

    @RequestMapping("/file")
    public String upload(MultipartFile fileImage) throws IOException {
        //1.获取图片的真实名称
        String fileName = fileImage.getOriginalFilename();
        //2.设定文件上传的地址  写成/更加符合Linux的语法特点 为了以后通用使用/
        String dir = "E:/JT_IMAGE";
        //3.最好对目录进行校验  判断是否存在.
        File dirFile = new File(dir);
        if(!dirFile.exists()){
            //如果文件不存在,则创建一个文件目录
            dirFile.mkdirs();
        }
        //4.实现文件上传 需要指定文件的全路径 目录路径/文件名称
        String filePath = dir + "/" + fileName;
        //5.实现文件上传操作
        fileImage.transferTo(new File(filePath));

        return "文件上传成功!!!!";
    }
}
