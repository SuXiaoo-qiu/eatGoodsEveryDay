package com.joeworld.service.impl;

import com.github.pagehelper.PageHelper;
import com.joeworld.common.PageInfo;
import com.joeworld.mapper.ItemsSpecMapper;
import com.joeworld.pojo.ItemsImg;
import com.joeworld.pojo.ItemsSpec;
import com.joeworld.service.ItemsSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ItemsSpecServiceImpl implements ItemsSpecService {

    @Autowired
    private ItemsSpecMapper itemsSpecMapper;
    
    /**
    * 分页查询所有记录
    * @param map
    * @return
    */
    @Override
    public PageInfo<ClassInfo> findPage(Map<String, Object> map) {
        PageHelper.startPage(Integer.valueOf(map.get("pageCode").toString()), Integer.valueOf(map.get("pageSize").toString()));
        List<ClassInfo> page = itemsSpecMapper.findPage(map);
        return new PageInfo<>(page);
    }
    

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @Override
    public List<ItemsSpec> listAll(Map<String,Object> map) {
    	return itemsSpecMapper.listAll(map);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @Override
    public ItemsSpec getById(String id) {
    	return itemsSpecMapper.getById(id);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param itemsSpec 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insert(ItemsSpec itemsSpec) {
    	return itemsSpecMapper.insert(itemsSpec);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param itemsSpec 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insertIgnoreNull(ItemsSpec itemsSpec) {
    	return itemsSpecMapper.insertIgnoreNull(itemsSpec);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param itemsSpec 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int update(ItemsSpec itemsSpec) {
    	return itemsSpecMapper.update(itemsSpec);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param itemsSpec 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int updateIgnoreNull(ItemsSpec itemsSpec) {
    	return itemsSpecMapper.updateIgnoreNull(itemsSpec);
    }
	
    /**
     * 删除记录
     *
     * @param itemsSpec 待删除的记录
     * @return 返回影响行数
     */
    @Override
    public int delete(ItemsSpec itemsSpec) {
    	return itemsSpecMapper.delete(itemsSpec);
    }

    @Override
    public List<ItemsSpec> getItemsId(String itemId) {
        return itemsSpecMapper.getItemsId(itemId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int decreaseItemSpecStock(String specId, int pendingCounts) {
       return  itemsSpecMapper.decreaseItemSpecStock(specId, pendingCounts);

    }

}