package com.jt.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

//pojo基类，完成2个任务，2个日期，实现序列化
@Data
@Accessors(chain=true)
public class BasePojo implements Serializable{
	@TableField(fill = FieldFill.INSERT)
	private Date created;	//表示入库时需要赋值
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updated;	//表示入库/更新时赋值.
}
