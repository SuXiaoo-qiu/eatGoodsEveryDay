package com.joeworld.service;


import com.joeworld.common.PageInfo;
import com.joeworld.pojo.Users;
import com.joeworld.pojo.bo.center.CenterUserBo;
import com.joeworld.pojo.vo.UserVo;
import org.springframework.cglib.core.ClassInfo;

import java.util.List;
import java.util.Map;

public interface UsersService {

    /**
    *   分页查询所有记录
    * @param map
    * @return
    */
    PageInfo<ClassInfo> findPage(Map<String,Object> map);
   
	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<Users> listAll(Map<String,Object> map);

	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	Users getById(String id);
	
	/**
     * 新增，插入所有字段
     *
     * @param users 新增的记录
     * @return 返回影响行数
     */
	int insert(Users users);
	
	/**
     * 新增，忽略null字段
     *
     * @param users 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(Users users);
	
	/**
     * 修改，修改所有字段
     *
     * @param users 修改的记录
     * @return 返回影响行数
     */
	int update(Users users);
	
	/**
     * 修改，忽略null字段
     *
     * @param users 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(Users users);
	
	/**
     * 删除记录
     *
     * @param users 待删除的记录
     * @return 返回影响行数
     */
	int delete(Users users);

	public boolean selectUserNameIsExist(String username);

	/**
	 * 创建用户
	 * @param userVo
	 * @return
	 */
	public Users createUser(UserVo userVo);

	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	public Users userLogin(String username, String password);

}