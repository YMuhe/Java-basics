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
	 * 1.方式1 查询所有的商品信息
	 * 2.方式2  自己能否手写sql实现分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public EasyUITable findItemByPage(Integer page, Integer rows) {

		//1.查询全部数据   不需要where条件查询是全部
		List<Item> itemList = itemMapper.selectList(null);
		//2.查询总记录数
		long total = itemMapper.selectCount(null);

		return new EasyUITable(total,itemList);
	}
}
