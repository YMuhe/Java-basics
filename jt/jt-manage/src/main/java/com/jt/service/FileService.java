package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘昱江
 * 时间 2021/1/26
 */
public interface FileService {
    ImageVO upload(MultipartFile uploadFile);
}
