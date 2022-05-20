package com.joeworld.controller;

import com.joeworld.common.*;
import com.joeworld.pojo.Items;
import com.joeworld.pojo.ItemsImg;
import com.joeworld.pojo.ItemsParam;
import com.joeworld.pojo.ItemsSpec;
import com.joeworld.pojo.bo.ShopcartBo;
import com.joeworld.pojo.vo.ItemInfoVo;
import com.joeworld.service.ItemsImgService;
import com.joeworld.service.ItemsParamService;
import com.joeworld.service.ItemsService;
import com.joeworld.service.ItemsSpecService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("shopcatController")
@CrossOrigin //跨域
/* 类注解购物车 */
@Api(value = "购物车",tags = {"购物车相关的接口"})
public class ShopcatController  extends BaseConller {
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 根据"商品id 查询商品详情
     * @param
     * @return
     */
    @ApiOperation(value = "根据商品id查询",notes = "根据商品id查询",httpMethod = "POST")
    @PostMapping("/add")
    public R getByitmeId(@RequestParam String userId, @RequestBody ShopcartBo shopcartBo, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(userId)) {
            return R.error("未登录");
        }
        // 2022/4/24  前端在登录的情况下，添加商品会到购物车，会同事在后端同步购物车到redis缓存
        String shopcartJson = redisUtils.get(FOODIE_SHOPCART+userId);
        List<ShopcartBo> shopcartList = null;
        boolean isHaving =false;
        if (StringUtils.isNotBlank(shopcartJson)){
            // 如果redis 中没有购物车了
            shopcartList = JsonUtils.jsonToList(shopcartJson,ShopcartBo.class);
            // 判断购物车是是否已经存在商品
            for (ShopcartBo sc : shopcartList) {
                //如果规格id 是相等的 就代表这个商品已经存在购物车里面了
                if (sc.getSpecId().equals(shopcartBo.getSpecId())) {
                    // 把现有的BuyCounts 和新的进行相加
                    sc.setBuyCounts(sc.getBuyCounts()+shopcartBo.getBuyCounts());
                    isHaving =true;
                }
            }
            if (!isHaving){
                shopcartList.add(shopcartBo);
            }
        }else {
            // 购物车中没有数据
            shopcartList= new ArrayList<>();
            // 把购物车扔到redis中
            shopcartList.add(shopcartBo);
        }
        // 把购物车中的数据放入redis （覆盖现有的redis）
        redisUtils.set(FOODIE_SHOPCART+userId,JsonUtils.objectToJson(shopcartList));


        return R.ok();
    }
    /**
     * 根据"商品id 查询商品详情
     * @param
     * @return
     */
    @ApiOperation(value = "从购物车中删除商品",notes = "从购物车中删除商品",httpMethod = "POST")
    @PostMapping("/del")
    public R del(@RequestParam String userId, @RequestParam String itemSpecId, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(userId)||StringUtils.isBlank(itemSpecId)) {
            return R.error("请求参数不能未空");
        }
        // 2022/4/25  前端在登录的情况下 删除购物车中的商品 （redis）

        String shopcartStr = redisUtils.get(FOODIE_SHOPCART + userId);
        if (StringUtils.isNotBlank(shopcartStr)) {
            List<ShopcartBo> shopcartBoList = JsonUtils.jsonToList(shopcartStr, ShopcartBo.class);
            for (ShopcartBo shopcartBo : shopcartBoList) {

                if (shopcartBo.getSpecId().equals(itemSpecId)) {
                    shopcartBoList.remove(shopcartBo);
                    break;
                }
            }
            redisUtils.set(FOODIE_SHOPCART + userId, JsonUtils.objectToJson(shopcartBoList));
        }
        return R.ok();
    }
}