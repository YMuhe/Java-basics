package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemDescMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.mapper.ItemMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;

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

	/**
	 * 实现商品/商品分类的入库操作
	 * @param item
	 * @param itemDesc
	 * 难点分析:
	 * 		1.item表主键自增  入库之后才会有主键信息.
	 * 		2.itemDesc表 要求必须写主键. itemDesc中的主键要求和item中的值一致.
	 * 想法:
	 * 		1.刚完成入库之后,再次查询数据库记录,获取主键 之后为itemDesc属性赋值  不安全
	 * 		2.让数据库完成入库操作时,实现主键的动态回显?
	 *
	 * 实际案例:
	 * 		1.由于实现使用的是MP方式操作数据库.利用对象的方式操作
	 * 	数据库,所以入库之后,自动的完成了回显功能.
	 * 	该功能是MP中主键自增的设定方法
	 */
	@Override
	@Transactional	//添加事务控制
	public void saveItem(Item item, ItemDesc itemDesc) {
		//Date date = new Date();
		//item.setStatus(1).setCreated(date).setUpdated(date);
		item.setStatus(1);
		itemMapper.insert(item);	//实现商品入库操作
		//itemMapper.insertItem(item);
		//实现商品详情入库操作
		itemDesc.setItemId(item.getId());
		itemDescMapper.insert(itemDesc);
	}

	@Override
	@Transactional
	public void updateItem(Item item, ItemDesc itemDesc) {

		itemMapper.updateById(item);
		itemDesc.setItemId(item.getId());
		itemDescMapper.updateById(itemDesc);
	}

	/**
	 * 要求使用2种方式实现
	 * 		1.手写Sql的方式
	 * 		2.利用MP的方式实现
	 * @param ids
	 */

	@Override
	@Transactional
	public void deleteItems(Long[] ids) {

		//itemMapper.deleteItems(ids);
		//如果需要使用MP的方式实现参数的传递,则需要封装为List集合
		//参数中使用get方法获取数据
		List idList = Arrays.asList(ids);
		itemMapper.deleteBatchIds(idList);
		itemDescMapper.deleteBatchIds(idList);
	}


	//作业: 自己手写Sql完成该操作
	//Sql: update tb_item set status=#{status},updated=#{updated}/now()
	//where id in (id1,id2,id3.....)
	@Override
	@Transactional
	public void updateItemStatus(Long[] ids, Integer status) {
		//封装需要修改的数据
		Item item = new Item();
		item.setStatus(status);
		//构建where条件
		UpdateWrapper updateWrapper = new UpdateWrapper();
		updateWrapper.in("id", Arrays.asList(ids));
		itemMapper.update(item,updateWrapper);
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {

		return itemDescMapper.selectById(itemId);
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
