package com.joeworld.service.impl;

import com.github.pagehelper.PageHelper;
import com.joeworld.common.DateUtil;
import com.joeworld.common.MD5Utils;
import com.joeworld.common.PageInfo;
import com.joeworld.enums.Sex;
import com.joeworld.mapper.UsersMapper;
import com.joeworld.pojo.Users;
import com.joeworld.pojo.vo.UserVo;
import com.joeworld.service.UsersService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public  class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private Sid sid;

    protected   static  final String  USER_FACE  = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    /**
    * 分页查询所有记录
    * @param map
    * @return
    */
    @Override
    public PageInfo<ClassInfo> findPage(Map<String, Object> map) {
        PageHelper.startPage(Integer.valueOf(map.get("pageCode").toString()), Integer.valueOf(map.get("pageSize").toString()));
        List<ClassInfo> page = usersMapper.findPage(map);
        return new PageInfo<>(page);
    }
    

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @Override
    public List<Users> listAll(Map<String,Object> map) {
    	return usersMapper.listAll(map);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @Override
    public Users getById(String id) {
    	return usersMapper.getById(id);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param users 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insert(Users users) {
    	return usersMapper.insert(users);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param users 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insertIgnoreNull(Users users) {
    	return usersMapper.insertIgnoreNull(users);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param users 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int update(Users users) {
    	return usersMapper.update(users);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param users 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int updateIgnoreNull(Users users) {
    	return usersMapper.updateIgnoreNull(users);
    }
	
    /**
     * 删除记录
     *
     * @param users 待删除的记录
     * @return 返回影响行数
     */
    @Override
    public int delete(Users users) {
    	return usersMapper.delete(users);
    }

    /**
     * 根据用户名查询用户是否注册
     */

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean selectUserNameIsExist(String username) {
        Users Restusers = usersMapper.selectUserNameIsExist(username);
        return Restusers==null ? false : true;
    }

    /**
     *
     * @param userVo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserVo userVo) {
        Users users = new Users();
        users.setId(sid.nextShort());
        //用户名
        users.setUsername(userVo.getUsername());
        try {
            users.setPassword(MD5Utils.getMD5Str(userVo.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //昵称
        users.setNickname(userVo.getUsername());
        //头像
        users.setFace(USER_FACE);
         //用户默认生日
        users.setBirthday(DateUtil.stringToDate("1900-01-01"));
        users.setSex(Sex.secret.type);
        users.setCreatedTime(new Date());
         usersMapper.insertIgnoreNull(users);
        return users;
    }

    @Override
    public Users userLogin(String username, String password) {
        return  usersMapper.userLogin(username, password);
    }
}