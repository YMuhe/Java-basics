package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName("tb_item_cat")
@Data
@Accessors(chain = true)
public class ItemCat extends BasePojo{

    @TableId(type = IdType.AUTO)
    private Long id;            //商品分类id 主键自增
    private Long parentId;      //定义父级分类Id
    private String name;        //商品分类名称
    private Integer status;     //指定状态  1正常   2删除
    private Integer sortOrder;  //排序号
    private Boolean isParent;   //是否为父级
}
