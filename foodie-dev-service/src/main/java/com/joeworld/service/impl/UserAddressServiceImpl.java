package com.joeworld.service.impl;


import com.github.pagehelper.PageHelper;
import com.joeworld.common.PageInfo;
import com.joeworld.enums.YesOrNo;
import com.joeworld.mapper.UserAddressMapper;
import com.joeworld.pojo.UserAddress;
import com.joeworld.pojo.bo.AddressBo;
import com.joeworld.service.UserAddressService;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private Sid sid;
    
    /**
    * 分页查询所有记录
    * @param map
    * @return
    */
    @Override
    public PageInfo<ClassInfo> findPage(Map<String, Object> map) {
        PageHelper.startPage(Integer.valueOf(map.get("pageCode").toString()), Integer.valueOf(map.get("pageSize").toString()));
        List<ClassInfo> page = userAddressMapper.findPage(map);
        return new PageInfo<>(page);
    }
    

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @Override
    public List<UserAddress> listAll(Map<String,Object> map) {
    	return userAddressMapper.listAll(map);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @Override
    public UserAddress getById(String id) {
    	return userAddressMapper.getById(id);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param userAddress 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insert(UserAddress userAddress) {
    	return userAddressMapper.insert(userAddress);
    }

    /**
     * 新增，忽略null字段
     * @param addressBo 新增的记录
     * @return返回影响行数
     */
    @Override
    public int insertIgnoreNull(AddressBo addressBo) {
        // 1. 判断当前用户是否存在地址，如果没有，则新增为‘默认地址’
        Integer isDefault = 0;
        HashMap hashMap = new HashMap();
        hashMap.put("userId",addressBo.getUserId());
        hashMap.put("isDefault",1);

        List<UserAddress> addressList = this.listAll(hashMap);
        if (addressList == null || addressList.isEmpty() || addressList.size() == 0) {
            isDefault = 1;
        }
        // 生辰固定的id  返回固定16位的字母数字混编的字符串。
        String addressId = sid.nextShort();

        // 2. 保存地址到数据库
        UserAddress newAddress = new UserAddress();
        // 对象拷贝
        BeanUtils.copyProperties(addressBo, newAddress);
        if (StringUtils.isNotBlank(addressBo.getAddressId())) {
            newAddress.setId(addressBo.getAddressId());
            newAddress.setIsDefault(isDefault);
            newAddress.setUpdatedTime(new Date());
            return userAddressMapper.updateIgnoreNull(newAddress);
        }else {
            newAddress.setId(addressId);
            newAddress.setIsDefault(isDefault);
            newAddress.setCreatedTime(new Date());
            newAddress.setUpdatedTime(new Date());
            return userAddressMapper.insertIgnoreNull(newAddress);
        }


    }
	
    /**
     * 修改，修改所有字段
     *
     * @param userAddress 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int update(UserAddress userAddress) {
    	return userAddressMapper.update(userAddress);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param userAddress 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int updateIgnoreNull(UserAddress userAddress) {
    	return userAddressMapper.updateIgnoreNull(userAddress);
    }
	
    /**
     * 删除记录
     *
     * @param userAddress 待删除的记录
     * @return 返回影响行数
     */
    @Override
    public int delete(UserAddress userAddress) {
    	return userAddressMapper.delete(userAddress);
    }

    @Override
    public int setDefault(UserAddress userAddress) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",userAddress.getId());
        map.put("userId",userAddress.getUserId());
        List<UserAddress> userAddresses = userAddressMapper.listAll(map);
        if (null!=userAddresses && userAddresses.size() > 0 ) {

            for (UserAddress userAddressList : userAddresses) {
                UserAddress userAddressinfo = new UserAddress();
                userAddressinfo.setUserId(userAddressList.getUserId());
                userAddressinfo.setId(userAddressList.getId());
                userAddressinfo.setIsDefault(YesOrNo.no.type);
                userAddressMapper.updateIgnoreNull(userAddressinfo);
            }

        }
        UserAddress userAddress1 = new UserAddress();
        userAddress1.setUserId(userAddress.getUserId());
        userAddress1.setId(userAddress.getId());
        userAddress1.setIsDefault(YesOrNo.yes.type);
        userAddress1.setUpdatedTime(new Date());
        return userAddressMapper.updateIgnoreNull(userAddress1);
    }

    @Override
    public UserAddress selectAddressByidAndUserId(UserAddress address) {
        return userAddressMapper.selectAddressByidAndUserId(address);
    }


}