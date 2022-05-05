package com.joeworld.service.impl;

import com.github.pagehelper.PageHelper;
import com.joeworld.common.PageInfo;
import com.joeworld.mapper.ItemsParamMapper;
import com.joeworld.pojo.ItemsParam;
import com.joeworld.service.ItemsParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemsParamServiceImpl implements ItemsParamService {

    @Autowired
    private ItemsParamMapper itemsParamMapper;
    
    /**
    * 分页查询所有记录
    * @param map
    * @return
    */
    @Override
    public PageInfo<ClassInfo> findPage(Map<String, Object> map) {
        PageHelper.startPage(Integer.valueOf(map.get("pageCode").toString()), Integer.valueOf(map.get("pageSize").toString()));
        List<ClassInfo> page = itemsParamMapper.findPage(map);
        return new PageInfo<>(page);
    }
    

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @Override
    public List<ItemsParam> listAll(Map<String,Object> map) {
    	return itemsParamMapper.listAll(map);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @Override
    public ItemsParam getById(String id) {
    	return itemsParamMapper.getById(id);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param itemsParam 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insert(ItemsParam itemsParam) {
    	return itemsParamMapper.insert(itemsParam);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param itemsParam 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insertIgnoreNull(ItemsParam itemsParam) {
    	return itemsParamMapper.insertIgnoreNull(itemsParam);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param itemsParam 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int update(ItemsParam itemsParam) {
    	return itemsParamMapper.update(itemsParam);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param itemsParam 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int updateIgnoreNull(ItemsParam itemsParam) {
    	return itemsParamMapper.updateIgnoreNull(itemsParam);
    }
	
    /**
     * 删除记录
     *
     * @param itemsParam 待删除的记录
     * @return 返回影响行数
     */
    @Override
    public int delete(ItemsParam itemsParam) {
    	return itemsParamMapper.delete(itemsParam);
    }

    /**
     * 根据商品外键id 查询商品参数
     * @param itemId
     * @return
     */
    @Override
    public ItemsParam getItemsId(String itemId) {
        return itemsParamMapper.getItemsId(itemId);
    }

}