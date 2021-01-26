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
        //1.校验是否为图片类型   BUG 一般条件下不出问题,当传入某些特定数据时 可能出问题
        //1.1 获取用户上传文件的类型   a.jpg
        String fileName = uploadFile.getOriginalFilename();
        //1.2获取图片类型
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        //1.3校验是否为图片类型
        if(!typeSet.contains(fileType)){
            //图片类型不符
            return ImageVO.fail();
        }
            return null;
    }
}
