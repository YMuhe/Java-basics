package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {
	//@RequestMapping("/")
	/*@RequestMapping("/")
	public String index(){

		return "index";
	}*/

	/**
	 * restFul风格(一)
	 *	url1:/page/item-add         item-add.jsp
	 *  url2:/page/item-list		item-list.jsp
	 *  url3:/page/item-param-list  item-param-list.jsp
	 *
	 * restFul风格(二)
	 * 	根据不同的请求类型,执行不同的业务操作
	 * 	GET	 查询操作
	 * 	POST 新增操作
	 * 	PUT  更新操作
	 * 	DELETE	删除操作
	 * 				  /page/item-list
	 * 				  /page/item-add
	 * 				  /page/item-edit
	 * */
	@RequestMapping("/page/{moduleName}")	//可以接收任意请求类型
	//@GetMapping
	//@PostMapping
	public String module(@PathVariable String moduleName) {

		return moduleName;
	}
}
