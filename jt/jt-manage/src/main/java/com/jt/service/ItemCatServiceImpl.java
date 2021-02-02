package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    private ItemCatMapper itemCatMapper;


    @Override
    public ItemCat findItemCatById(Long id) {

        return itemCatMapper.selectById(id);
    }

    /**
     * 核心问题: 将POJO对象转化为VO对象
     * @param parentId
     * @return
     */
    @Override
    public List<EasyUITree> findItemCatList(Long parentId) {
        //2.定义VO的返回值
        List<EasyUITree> treeList = new ArrayList<>();

        //1.根据parentId查询数据库记录
        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        List<ItemCat> itemCatList = itemCatMapper.selectList(queryWrapper);
        for (ItemCat itemCat : itemCatList){ //数据的转化
            long id = itemCat.getId();  //获取主键信息
            String text = itemCat.getName();  //获取节点的名称
            //如果是父级,默认条件下处于关闭状态
            String state = itemCat.getIsParent()?"closed":"open";
            EasyUITree easyUITree = new EasyUITree(id,text,state);
            treeList.add(easyUITree);
        }
        return treeList;
    }

    @Autowired
    private Jedis jedis;

    //实现商品分类的缓存操作!!!
    @Override
    public List<EasyUITree> findItemCatCache(Long parentId) {

        return null;
    }
}
