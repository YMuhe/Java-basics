package com.jt.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.OrderItemMapper;
import com.jt.mapper.OrderMapper;
import com.jt.mapper.OrderShippingMapper;
import com.jt.pojo.Order;
import com.jt.pojo.OrderItem;
import com.jt.pojo.OrderShipping;

@Service
public class OrderServiceImpl implements DubboOrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderShippingMapper orderShippingMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;

	/**
	 * 完成3张表入库操作
	 * order表入库
	 * orderItem表入库
	 * OrderShipping表入库
	 * 动态生成orderId.设定默认状态
	 * 注意数据库事务控制
	 * @param order
	 * @return
	 */
	@Override
	@Transactional
	public String saveOrder(Order order) {
		//动态生成主键
		String orderId = "" + order.getUserId() + System.currentTimeMillis();
		//1.完成订单入库操作
		order.setOrderId(orderId).setStatus(1);
		orderMapper.insert(order);
		System.out.println("订单入库操作成功!!!");

		//2.完成订单物流入库
		OrderShipping orderShipping = order.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShippingMapper.insert(orderShipping);
		System.out.println("订单物流入库成功!!!");

		//3.完成订单商品入库操作
		List<OrderItem> orderItems = order.getOrderItems();
		for (OrderItem orderItem : orderItems){
			orderItem.setOrderId(orderId);
			orderItemMapper.insert(orderItem);
		}
		System.out.println("订单全部入库成功!!!!");
		return orderId;
	}

	@Override
	public Order findOrderById(String id) {

		Order order = orderMapper.selectById(id);
		OrderShipping orderShipping = orderShippingMapper.selectById(id);
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq("order_id", id);
		List<OrderItem> orderItems = orderItemMapper.selectList(queryWrapper);
		return order.setOrderShipping(orderShipping).setOrderItems(orderItems);
	}
}
