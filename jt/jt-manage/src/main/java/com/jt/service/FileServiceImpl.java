package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘昱江
 * 时间 2021/1/26
 */
@Service
public class FileServiceImpl implements FileService{

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
        return null;
    }
}
