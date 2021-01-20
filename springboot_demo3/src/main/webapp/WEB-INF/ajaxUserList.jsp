<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>您好Springboot</title>
<!-- 1.引入函数类库 -->
<script src="../js/jquery-3.4.1.min.js"></script>
<!-- 2.发起ajax请求 -->
<script>
	$(function(){
		//alert("页面加载完成!!!!");
		//$.get("url地址","请求参数","回调函数","返回值类型")
		
		/**
		 * 业务分析:
		 * 		url地址: /findAll
		 * 		参数:	暂时不写
		 * 				方式1:  {id:100,name:"tomcat猫"}
		 * 				方式2:  id=100&name="tomcat猫"
		 * 		回调函数: 需要获取服务器端数据 一般都是携带参数
		 * 		返回值类型: 可以为html/text/json/javaScript等 一般不写 程序自动完成解析.
		 */
		$.get("/findAll",function(result){
			
			//console.log(result);
			//遍历方式1 常规for循环
			/* for(let i=0;i<result.length;i++){
				console.log(result[i]);
			} */
			
			/* 循环方式2 in关键字 */
			/* for(let index in result){
				console.log(result[index])
			} */
			
			//循环方式3: of关键字   直接获取遍历的对象
			for(let user of result){
				console.log(user);
			}
				
			//使用原生JS方式编辑   
			let tr = "<tr><td></td><td></td><td></td><td></td></tr>"
		});
		
		
	})
</script>
</head>
<body>
	<table border="1px" width="65%" align="center">
		<tr>
			<td colspan="6" align="center"><h3>学生信息</h3></td>
		</tr>
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>性别</th>
		</tr>
	</table>
</body>
</html>