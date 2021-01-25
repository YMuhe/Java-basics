package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.service.ItemService;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		 itemService.saveItem(item);
		 return SysResult.success();
		/*try {
			itemService.saveItem(item);
			return SysResult.success();
		}catch (Exception e){
			e.printStackTrace();
			return SysResult.fail();
		}*/
	 }

	/**
	 * 业务: 实现商品更新操作
	 * URL:  /item/update
	 * 参数: 整个form表单   利用对象接收
	 * 返回值: SysResult对象
	 */
	@RequestMapping("update")
	public SysResult updateItem(Item item){

		itemService.updateItem(item);
		return SysResult.success();
	}

	/**
	 * 业务说明: 实现商品删除
	 * URL地址:	 /item/delete
	 * 参数:	 ids: 1474392223,1474392222,1474392220,1474392215
	 * 返回值:   SysResult对象
	 * 核心规则: 参数的名称必须与页面中提交的名称一致!!!!
	 * 			 String request.getParameter("ids");
	 * 关于SpringMVC参数取值的说明:
	 * 	1.如果参数与参数之间采用,号的方式进行链接可以自动的将其转化为
	 * 	数组结构
	 * 	Long[]  longIds = new Long[strIds.length];
	 * 		//实现数据类型的转化
	 * 		for(int i=0;i<strIds.length;i++){
	 * 			longIds[i] = Long.parseLong(strIds[i]);
	 *                }
	 */
	@RequestMapping("/delete")
	public SysResult deleteItems(Long... ids){

		itemService.deleteItems(ids);
		return SysResult.success();
	}

	/**
	 * 业务需求: 实现商品上架/下架操作
	 * URL地址:  /item/1   /item/2
	 * 参数:  status状态码,ids参数
	 * 返回值: SysResult对象
	 */
	@RequestMapping("{status}")
	public SysResult updateStatus(@PathVariable Integer status,Long[] ids){

		itemService.updateItemStatus(ids,status);
		return SysResult.success();
	}




}
