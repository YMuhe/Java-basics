package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 刘昱江
 * 时间 2021/1/26
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ImageVO {
    // {"error":0,"url":"图片的保存路径","width":图片的宽度,"height":图片的高度}
    private Integer error;  //0 正常   1 失败
    private String url;
    private Integer width;
    private Integer height;

    public static ImageVO fail(){

        return new ImageVO(1, null, null, null);
    }

    public static ImageVO success(String url,Integer width,Integer height){

        return new ImageVO(0, url, width, height);
    }
}
