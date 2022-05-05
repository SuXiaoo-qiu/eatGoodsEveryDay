package com.joeworld.controller;

import com.joeworld.common.PageInfo;
import com.joeworld.common.R;
import com.joeworld.pojo.UserAddress;
import com.joeworld.pojo.bo.AddressBo;
import com.joeworld.service.UserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("userAddressController")
@CrossOrigin //跨域
/* 类注解 */
@Api(value = "地址相关",tags = {"地址相关的接口"})
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;
    
    /**
     * 分页查询
     * @param params
     * @return
     */
    @ApiOperation(value = "条件查询分页")
    @RequestMapping("/findPage")
    public PageInfo<ClassInfo> findPage(@RequestParam Map<String, Object> params){
        PageInfo<ClassInfo> pageInfo = userAddressService.findPage(params);
        if (pageInfo.getList().size() > 0){
            pageInfo.setCode(200);
            pageInfo.setMessage("成功");
        }else {
            pageInfo.setPageSize(0);
            pageInfo.setCode(500);
            pageInfo.setMessage("暂无数据");
        }

        return pageInfo;
        
    }
    
    /**
    *查询所有记录
    * @param params
    * @return 返回集合，没有返回空List
    */
    @ApiOperation(value = "条件查询全部数据")
    @RequestMapping("/list")
    public R listAll(@RequestParam Map<String, Object> params) {
     List<UserAddress> UserAddress =  userAddressService.listAll(params);
        return  R.ok(UserAddress);
    }

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @ApiOperation(value = "根据id查询")
    @RequestMapping("/getById")
    public UserAddress getById(String id) {
        return userAddressService.getById(id);
    }    
     
    /**
     * 新增，忽略null字段
     * @param addressBo 新增的记录
     * @return返回影响行数
     */
    @ApiOperation(value = "新增")
    @RequestMapping("/insert")
    public R insert(@RequestBody AddressBo addressBo) {
      int insert = userAddressService.insertIgnoreNull(addressBo);
          if (insert > 0) {
              return R.ok();
          }
          return R.error();
      }    
      
    /**
     * 修改，忽略null字段
     *
     * @param userAddress 修改的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "修改")
    @RequestMapping("/update")
    public R update(@RequestBody UserAddress userAddress) {
    int update = userAddressService.updateIgnoreNull(userAddress);
      if (update > 0) {
              return R.ok();
          }
          return R.error();
      }

    /**
     *  删除物理删除
     * @param userId
     * @param addressId
     * @return
     */
    @ApiOperation(value = "删除物理删除")
    @RequestMapping("/delete")
    public R delete(  @RequestParam String userId,
                      @RequestParam String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(addressId);
        userAddress.setUserId(userId);

        int delete = userAddressService.delete(userAddress);
          if (delete > 0) {
              return R.ok("删除成功");
          }
          return R.error("删除失败");
      }

    /**
     * 根据用户id查询收货地址列表
     * @param userId
     * @return
     */
    @ApiOperation(value = "根据用户id查询收货地址列表")
    @PostMapping("/selectListAddress")
    public R selectListAddress(@RequestParam String userId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        List<UserAddress> userAddresses = userAddressService.listAll(map);
        return R.ok(userAddresses);
    }

    /**
     * 设置为默认地址
     * @param userId
     * @param addressId
     * @return
     */
    @ApiOperation(value = "设置为默认地址")
    @RequestMapping("/setDefault")
    public R setDefault(  @RequestParam String userId,
                      @RequestParam String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(addressId);
        userAddress.setUserId(userId);

        int delete = userAddressService.setDefault(userAddress);
        if (delete > 0) {
            return R.ok("设置成功");
        }
        return R.error("设置失败");
    }
}