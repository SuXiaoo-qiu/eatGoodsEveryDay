package com.joeworld.service.impl;

import com.github.pagehelper.PageHelper;
import com.joeworld.common.PageInfo;
import com.joeworld.mapper.ItemsImgMapper;
import com.joeworld.pojo.ItemsImg;
import com.joeworld.service.ItemsImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ItemsImgServiceImpl implements ItemsImgService {

    @Autowired
    private ItemsImgMapper itemsImgMapper;
    
    /**
    * 分页查询所有记录
    * @param map
    * @return
    */
    @Override
    public PageInfo<ClassInfo> findPage(Map<String, Object> map) {
        PageHelper.startPage(Integer.valueOf(map.get("pageCode").toString()), Integer.valueOf(map.get("pageSize").toString()));
        List<ClassInfo> page = itemsImgMapper.findPage(map);
        return new PageInfo<>(page);
    }
    

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @Override
    public List<ItemsImg> listAll(Map<String,Object> map) {
    	return itemsImgMapper.listAll(map);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @Override
    public ItemsImg getById(String id) {
    	return itemsImgMapper.getById(id);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param itemsImg 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insert(ItemsImg itemsImg) {
    	return itemsImgMapper.insert(itemsImg);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param itemsImg 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insertIgnoreNull(ItemsImg itemsImg) {
    	return itemsImgMapper.insertIgnoreNull(itemsImg);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param itemsImg 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int update(ItemsImg itemsImg) {
    	return itemsImgMapper.update(itemsImg);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param itemsImg 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int updateIgnoreNull(ItemsImg itemsImg) {
    	return itemsImgMapper.updateIgnoreNull(itemsImg);
    }
	
    /**
     * 删除记录
     *
     * @param itemsImg 待删除的记录
     * @return 返回影响行数
     */
    @Override
    public int delete(ItemsImg itemsImg) {
    	return itemsImgMapper.delete(itemsImg);
    }


    /**
     * 根据商品外键id来查询商品图片
     * @param itemId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> getItemsId(String itemId) {
        return itemsImgMapper.getItemsId(itemId);
    }

    /**
     * 和是否主图来拆线呢商品图片
     * @param itemId
     * @param isMain
     * @return
     */
    @Override
    public String getByIdAndIsMain(String itemId, Integer isMain) {
        return itemsImgMapper.getByIdAndIsMain(itemId, isMain);
    }
}