package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.CartMapper;
import com.jt.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(timeout = 3000)
public class DubboCartServiceImpl implements DubboCartService{

    @Autowired
    private CartMapper cartMapper;


    @Override
   public List<Cart> findCartListByUserId(long userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        return cartMapper.selectList(queryWrapper);
    }

    /**
     * 难点: 用户如果重复加购? 只做数量的更新
     * 业务操作:
     *      1.根据userId/itemId查询数据库检查是否加购.
     *      有值: 已经加购 则只更新数量
     *      没有值: 第一次加购 则直接入库即可.
     * @param cart
     */
    @Override
    public void addCart(Cart cart) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", cart.getUserId());
        queryWrapper.eq("item_id", cart.getItemId());
        Cart cartDB = cartMapper.selectOne(queryWrapper);
        if(cartDB == null){  //说明用户第一次加购
            cartMapper.insert(cart);
        }else{
            //说明用户之间添加过该商品  数量的更新
            int num = cart.getNum() + cartDB.getNum();
            Cart temp = new Cart();
            temp.setNum(num).setId(cartDB.getId());
            //根据id 更新对象中不为null的数据...
            cartMapper.updateById(temp);
        }
    }
}
