package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.service.ItemService;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController	//返回的数据都是JSON数据.
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	/**
	 * 业务说明: 完成商品分页查询
	 * URL地址:	http://localhost:8091/item/query?page=1 页数 &rows=20 行数
	 * 参数:	page/rows
	 * 返回值:  EasyUITable
	 */
	 @RequestMapping("/query")
	 public EasyUITable findItemByPage(Integer page,Integer rows){

	 	return itemService.findItemByPage(page,rows);
	 }

	/**
	 * 业务说明:实现商品的入库操作
	 * URL地址:	http://localhost:8091/item/save
	 * 请求参数: 整个form表单 利用对象进行接收
	 * 返回值:   SysResult对象
	 */
	 @RequestMapping("/save")
	 public SysResult saveItem(Item item){
		try {
			itemService.saveItem(item);
			return SysResult.success();
		}catch (Exception e){
			e.printStackTrace();
			return SysResult.fail();
		}
	 }





}
