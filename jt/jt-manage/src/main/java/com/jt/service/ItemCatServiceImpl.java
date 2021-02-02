package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.util.ObjectMapperUtil;
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

    /**
     * 原理说明:
     *      1.定义存取redis中的key  业务名称+标识符  ITEMCAT_PARENTID::0
     *      2.通过key获取redis中的记录
     *      3.空:    查询数据库 将返回值结果保存到缓存中即可
     *      4.非空    直接将缓存数据获取之后,返回给用户即可.
     * @param parentId
     * @return
     */
    @Override
    public List<EasyUITree> findItemCatCache(Long parentId) {
        long startTime = System.currentTimeMillis();
        String key = "ITEMCAT_PARENTID::" + parentId;
        List treeList = new ArrayList();
        if(jedis.exists(key)){
            //如果存在则直接返回
            String json = jedis.get(key);
            treeList = ObjectMapperUtil.toObj(json, treeList.getClass());
            System.out.println("查询Redis缓存!!!");
            long endTime = System.currentTimeMillis();
            System.out.println("耗时:"+(endTime - startTime)+"毫秒");
        }else{
            //如果不存在 则查询数据库.
            treeList = findItemCatList(parentId);
            //将数据保存到缓存中
            String json = ObjectMapperUtil.toJSON(treeList);
            jedis.set(key,json);
            System.out.println("查询数据库!!!");
            long endTime = System.currentTimeMillis();
            System.out.println("耗时:"+(endTime - startTime)+"毫秒");
        }
        return treeList;
    }
}
