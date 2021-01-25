package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUITree {       //封装节点信息

    private Long id;        //节点编号
    private String text;    //文本内容
    private String state;   //open 开启状态 | closed 关闭状态

}
