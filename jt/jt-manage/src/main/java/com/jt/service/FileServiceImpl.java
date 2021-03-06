package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author 刘昱江
 * 时间 2021/1/26
 */
@Service
@PropertySource(value = "classpath:/properties/image.properties",encoding = "UTF-8")
public class FileServiceImpl implements FileService{

    //方式1: 利用集合实现数据的校验
    private static Set<String> typeSet = new HashSet<>();
    @Value("${image.localDirPath}")
    private String localDirPath;    // = "E:/JT_IMAGE";
    @Value("${image.urlPath}")
    private String urlPath;         // = "http://image.jt.com";

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
     *
     * 正则语法:
     *  1.{n} 指定次数
     *  2.[a-z] 字符必须满足集合中的一个元素
     *  3.(a|n|z) 分组的写法  满足a或者n或者z
     */
    @Override
    public ImageVO upload(MultipartFile uploadFile){
        //1.校验文件类型  abc.jpg
        String fileName = uploadFile.getOriginalFilename().toLowerCase();
        //1.1 利用正则表达式校验是否满足图片格式要求
        if(!fileName.matches("^.+\\.(jpg|png|gif)$")){
            return ImageVO.fail();
        }

        //2.校验是否为图片对象
        try {
            BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            if(width==0 || height==0){
                return ImageVO.fail();
            }

            //3.实现分目录存储
            //3.1 动态生成hashcode编码 之后2位一隔 生成多级目录.目录层级太深 笛卡尔积较大 遍历不便
            //3.2 可以动态以当前的时间为存储的目录结构 yyyy/MM/dd
            String dateDirPath =
                new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());

            //文件存储的目录  E:/JT_IMAGE/2021/01/26/
            String fileDirPath = localDirPath + dateDirPath;
            File fileDir = new File(fileDirPath);
            if(!fileDir.exists()){  //判断文件目录是否存在

                fileDir.mkdirs();  //创建目录
            }

            //4.利用UUID动态生图片名称   uuid.jpg
            String uuid =
                    UUID. randomUUID().toString().replace("-", "");
            //abc.jpg
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = uuid + fileType;
            //5.实现文件上传
            File realFile = new File(fileDirPath + newFileName);
            uploadFile.transferTo(realFile);

            //6.编辑图片的虚拟路径
            //6.1磁盘地址: E:\JT_IMAGE\2021\01\26\1b0e435933ac42cabec53b20ffbcfe90.png
            //6.2虚拟地址  http://image.jt.com\2021\01\26\1b0e435933ac42cabec53b20ffbcfe90.png
            String url = urlPath + dateDirPath + newFileName;
            return ImageVO.success(url,width,height);

        } catch (IOException e) {
            e.printStackTrace();
            return ImageVO.fail();
        }
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
   /* @Override
    public ImageVO upload(MultipartFile uploadFile) {
        //1.校验是否为图片类型
        String fileName = uploadFile.getOriginalFilename();
        //1.1由于大小写问题 导致校验异常bug 所有全部转化为小写
        fileName = fileName.toLowerCase();
        //1.2截取下标
        int index = fileName.lastIndexOf(".");
        //1.3 如果下标为-1表示没有后缀 应该提前结束
        if(index == -1){
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
    }*/
}
