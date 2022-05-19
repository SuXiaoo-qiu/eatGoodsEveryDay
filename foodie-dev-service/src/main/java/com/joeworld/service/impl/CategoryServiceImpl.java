package com.joeworld.service.impl;

import com.github.pagehelper.PageHelper;
import com.joeworld.common.PageInfo;
import com.joeworld.mapper.CategoryMapper;
import com.joeworld.pojo.Category;
import com.joeworld.pojo.vo.CateGoryVo;
import com.joeworld.pojo.vo.NewCateVo;
import com.joeworld.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    
    /**
    * 分页查询所有记录
    * @param map
    * @return
    */
    @Override
    public PageInfo<ClassInfo> findPage(Map<String, Object> map) {
        PageHelper.startPage(Integer.valueOf(map.get("pageCode").toString()), Integer.valueOf(map.get("pageSize").toString()));
        List<ClassInfo> page = categoryMapper.findPage(map);
        return new PageInfo<>(page);
    }
    

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @Override
    public List<Category> listAll(Map<String,Object> map) {
    	return categoryMapper.listAll(map);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @Override
    public Category getById(Integer id) {
    	return categoryMapper.getById(id);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param category 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insert(Category category) {
    	return categoryMapper.insert(category);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param category 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insertIgnoreNull(Category category) {
    	return categoryMapper.insertIgnoreNull(category);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param category 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int update(Category category) {
    	return categoryMapper.update(category);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param category 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int updateIgnoreNull(Category category) {
    	return categoryMapper.updateIgnoreNull(category);
    }
	
    /**
     * 删除记录
     *
     * @param category 待删除的记录
     * @return 返回影响行数
     */
    @Override
    public int delete(Category category) {
    	return categoryMapper.delete(category);
    }

    @Override
    public List<CateGoryVo> selectTwoCarousel(Integer fatherId) {
        return categoryMapper.selectTwoCarousel(fatherId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<NewCateVo> getSixNewItemsLazy(Integer rootCatId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("rootCatId",rootCatId);
        return categoryMapper.getSixNewItemsLazy(map);
    }

}