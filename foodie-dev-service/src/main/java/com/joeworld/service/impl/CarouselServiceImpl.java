package com.joeworld.service.impl;

import com.github.pagehelper.PageHelper;
import com.joeworld.common.PageInfo;
import com.joeworld.mapper.CarouselMapper;
import com.joeworld.pojo.Carousel;
import com.joeworld.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;
    
    /**
    * 分页查询所有记录
    * @param map
    * @return
    */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public PageInfo<ClassInfo> findPage(Map<String, Object> map) {
        PageHelper.startPage(Integer.valueOf(map.get("pageCode").toString()), Integer.valueOf(map.get("pageSize").toString()));
        List<ClassInfo> page = carouselMapper.findPage(map);
        return new PageInfo<>(page);
    }
    

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @Override
    public List<Carousel> listAll(Map<String,Object> map) {
    	return carouselMapper.listAll(map);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @Override
    public Carousel getById(String id) {
    	return carouselMapper.getById(id);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param carousel 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insert(Carousel carousel) {
    	return carouselMapper.insert(carousel);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param carousel 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insertIgnoreNull(Carousel carousel) {
    	return carouselMapper.insertIgnoreNull(carousel);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param carousel 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int update(Carousel carousel) {
    	return carouselMapper.update(carousel);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param carousel 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int updateIgnoreNull(Carousel carousel) {
    	return carouselMapper.updateIgnoreNull(carousel);
    }
	
    /**
     * 删除记录
     *
     * @param carousel 待删除的记录
     * @return 返回影响行数
     */
    @Override
    public int delete(Carousel carousel) {
    	return carouselMapper.delete(carousel);
    }
//@Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void byz(Carousel carousel) {
        this.ww(carousel);
        int i =1/0;
        this.asd(carousel);
    }

    @Override
    public List<Carousel> selectListAll(Map<String, Object> map) {
        return carouselMapper.selectListAll(map);
    }









    public void asd(Carousel carousel) {

        carouselMapper.updateIgnoreNull(carousel);


    }
@Transactional(propagation = Propagation.MANDATORY)
    public void ww(Carousel carousel) {

        carouselMapper.insertIgnoreNull(carousel);
    }




    }