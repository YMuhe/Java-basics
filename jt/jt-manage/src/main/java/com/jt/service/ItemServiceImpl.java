package com.jt.service;

import com.jt.pojo.Item;
import com.jt.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.mapper.ItemMapper;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;

	/**
	 * 1.方式1  查询所有的商品信息
	 * 2.方式2  自己能否手写sql实现分页查询
	 * 2.1 分页查询规则  sql: select * from tb_item limit  起始位置,记录数
	 * 		第一页		SELECT * FROM tb_item LIMIT 0,20  [0,19] 共20条
	 * 		第二页		SELECT * FROM tb_item LIMIT 20,20 [20,39]共20条
	 * 		第三页		SELECT * FROM tb_item LIMIT 40,20 [40,59]共20条
	 * 		第N页		SELECT * FROM tb_item LIMIT (page-1)*20,20  共20条
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public EasyUITable findItemByPage(Integer page, Integer rows) {

		//1.手写分页查询
		//1.1计算起始位置
		int start = (page-1)*rows;
		List<Item> itemList = itemMapper.findItemByPage(start,rows);
		//2.查询总记录数
		long total = itemMapper.selectCount(null);

		return new EasyUITable(total,itemList);
	}
}
