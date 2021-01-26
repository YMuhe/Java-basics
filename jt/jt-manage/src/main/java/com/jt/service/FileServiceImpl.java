package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 刘昱江
 * 时间 2021/1/26
 */
@Service
public class FileServiceImpl implements FileService{

    //方式1: 利用集合实现数据的校验
    private static Set<String> typeSet = new HashSet<>();

    static {//静态代码块 为属性赋值,初始化实例对象的.
        typeSet.add(".jpg");
        typeSet.add(".png");
        typeSet.add(".gif");
    }


    /**
     * 知识点:
     *      1.代码的安全性
     *      2.多问一下自己如果什么什么 应该怎么办??
     *
     * 文件上传业务说明:
     * 1.校验文件上传是否为图片类型? jpg|png|gif....
     * 2.为了防止恶意程序 木马.exe.jpg
     * 3.为了保证检索速度,需要将图片 分目录存储.
     * 4.如何防止文件重名???
     * @param uploadFile
     * @return
     */
    @Override
    public ImageVO upload(MultipartFile uploadFile) {
        //1.校验是否为图片类型
        String fileName = uploadFile.getOriginalFilename();
        //1.1由于大小写问题 导致校验异常bug 所有全部转化为小写
        fileName = fileName.toLowerCase();
        //1.2截取下标
        int index = fileName.lastIndexOf(".");
        //1.3 如果下标为0表示没有后缀 应该提前结束
        if(index == 0){
            //没有后缀.程序应该提前结束
            return ImageVO.fail();
        }

        //1.4校验是否为图片类型
        String fileType = fileName.substring(index);
        if(!typeSet.contains(fileType)){
            //图片类型不符
            return ImageVO.fail();
        }
            return null;
    }
}
