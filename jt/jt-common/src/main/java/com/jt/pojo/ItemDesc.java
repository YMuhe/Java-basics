package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 刘昱江
 * 时间 2021/1/25
 */

@TableName("tb_item_desc")
@Data
@Accessors(chain = true) //重构了set方法 返回值 void 现在变成this对象
public class ItemDesc extends BasePojo{

    @TableId                    //需要与商品Id号保持一致
    private Long itemId;        //商品ID号
    private String itemDesc;    //商品详情信息

}
