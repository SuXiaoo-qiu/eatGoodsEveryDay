package com.joeworld.service.impl;

import com.github.pagehelper.PageHelper;
import com.joeworld.common.PageInfo;
import com.joeworld.mapper.TUserMapper;
import com.joeworld.pojo.TUser;
import com.joeworld.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class TUserServiceImpl implements TUserService {

    @Autowired
    private TUserMapper tUserMapper;
    
    /**
    * 分页查询所有记录
    * @param map
    * @return
    */
    @Override
    public PageInfo<ClassInfo> findPage(Map<String, Object> map) {
        PageHelper.startPage(Integer.valueOf(map.get("pageCode").toString()), Integer.valueOf(map.get("pageSize").toString()));
        List<ClassInfo> page = tUserMapper.findPage(map);
        return new PageInfo<>(page);
    }
    

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @Override
    public List<TUser> listAll(Map<String,Object> map) {
    	return tUserMapper.listAll(map);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @Override
    public TUser getById(Integer id) {
    	return tUserMapper.getById(id);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param tUser 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insert(TUser tUser) {
    	return tUserMapper.insert(tUser);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param tUser 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insertIgnoreNull(TUser tUser) {
    	return tUserMapper.insertIgnoreNull(tUser);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param tUser 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int update(TUser tUser) {
    	return tUserMapper.update(tUser);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param tUser 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int updateIgnoreNull(TUser tUser) {
    	return tUserMapper.updateIgnoreNull(tUser);
    }
	
    /**
     * 删除记录
     *
     * @param tUser 待删除的记录
     * @return 返回影响行数
     */
    @Override
    public int delete(TUser tUser) {
    	return tUserMapper.delete(tUser);
    }


}