package com.joeworld.service.impl;

import com.github.pagehelper.PageHelper;
import com.joeworld.mapper.ItemsMapper;
import com.joeworld.common.PageInfo;
import com.joeworld.pojo.Items;
import com.joeworld.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsMapper itemsMapper;
    
    /**
    * 分页查询所有记录
    * @param map
    * @return
    */
    @Override
    public PageInfo<ClassInfo> findPage(Map<String, Object> map) {
        PageHelper.startPage(Integer.valueOf(map.get("pageCode").toString()), Integer.valueOf(map.get("pageSize").toString()));
        List<ClassInfo> page = itemsMapper.findPage(map);
        return new PageInfo<>(page);
    }
    

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @Override
    public List<Items> listAll(Map<String,Object> map) {
    	return itemsMapper.listAll(map);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @Override
    public Items getById(String id) {
    	return itemsMapper.getById(id);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param items 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insert(Items items) {
    	return itemsMapper.insert(items);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param items 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insertIgnoreNull(Items items) {
    	return itemsMapper.insertIgnoreNull(items);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param items 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int update(Items items) {
    	return itemsMapper.update(items);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param items 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int updateIgnoreNull(Items items) {
    	return itemsMapper.updateIgnoreNull(items);
    }
	
    /**
     * 删除记录
     *
     * @param items 待删除的记录
     * @return 返回影响行数
     */
    @Override
    public int delete(Items items) {
    	return itemsMapper.delete(items);
    }
	
}