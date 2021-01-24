package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.pojo.Item;
import com.jt.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.mapper.ItemMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;

	/**
	 * MP分页相关说明:
	 * 	1.构建一个分页对象(当前页,记录数,总记录数......)
	 * 	2.为分页对象进行初始化操作
	 * 	3.从分页操作中获取有效数据之后返回
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public EasyUITable findItemByPage(Integer page, Integer rows) {
		//1.定义分页对象 之后进行查询,
		IPage<Item> iPage = new Page<>(page,rows);
		//2.获取分页对象的其他数据
		QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("updated");
		iPage = itemMapper.selectPage(iPage,queryWrapper);
		long total = iPage.getTotal();		//获取总记录数
		List<Item> itemList = iPage.getRecords(); //获取当前页的数据
		return new EasyUITable(total,itemList);

	}

	@Override
	@Transactional	//添加事务控制
	public void saveItem(Item item) {
		//Date date = new Date();
		//item.setStatus(1).setCreated(date).setUpdated(date);
		item.setStatus(1);
		itemMapper.insert(item);
	}


	/**
	 * 1.方式1  查询所有的商品信息
	 * 2.方式2  自己能否手写sql实现分页查询
	 * 2.1 分页查询规则  sql: select * from tb_item limit  起始位置,记录数
	 * 		第一页		SELECT * FROM tb_item LIMIT 0,20  [0,19] 共20条
	 * 		第二页		SELECT * FROM tb_item LIMIT 20,20 [20,39]共20条
	 * 		第三页		SELECT * FROM tb_item LIMIT 40,20 [40,59]共20条
	 * 		第N页		SELECT * FROM tb_item LIMIT (page-1)*20,20  共20条
	 * 3.利用MP方式实现分页操作???
	 * @param page
	 * @param rows
	 * @return
	 */
	/*@Override
	public EasyUITable findItemByPage(Integer page, Integer rows) {
		//1.手写分页查询
		//1.1计算起始位置
		int start = (page-1)*rows;
		List<Item> itemList = itemMapper.findItemByPage(start,rows);
		//2.查询总记录数
		long total = itemMapper.selectCount(null);
		return new EasyUITable(total,itemList);
	}*/
}
