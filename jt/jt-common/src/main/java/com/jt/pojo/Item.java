package com.jt.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.experimental.Accessors;

@JsonIgnoreProperties(ignoreUnknown=true) //表示JSON转化时忽略未知属性
@TableName("tb_item")
@Data
@Accessors(chain=true)
public class Item extends BasePojo{
	@TableId(type=IdType.AUTO)		//商品入库之后,才有主键
	private Long id;				//商品id
	private String title;			//商品标题
	private String sellPoint;		//商品卖点信息
									//9.90 * 100 =990   展现:990/100=9.9
	private Long   price;			//商品价格 Long > double
	private Integer num;			//商品数量
	private String barcode;			//条形码
	private String image;			//商品图片信息   1.jpg,2.jpg,3.jpg
	private Long   cid;				//表示商品的分类id
	private Integer status;			//1上架，2下架

	//为了满足页面调用需求,添加get方法
	public String[] getImages(){

		return image.split(",");
	}
}
