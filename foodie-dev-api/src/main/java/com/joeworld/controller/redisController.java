package com.joeworld.controller;

import com.joeworld.common.PageInfo;
import com.joeworld.common.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("redis")
@CrossOrigin //跨域
/* 类注解 */
@Api(value = "订单状态",tags = {"redis测试"})
public class redisController {
    //注入redis
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 以下针对String 类型
     *  redis 常用五大类型
     *  1. string
     *  2. hash
     *  3. set
     *  4。zset
     *  5。list
     * @param key
     * @param value
     * @return
     */
    @ApiOperation(value = "新增")
    @RequestMapping("/setRedis")
    public Object setRedis(String key ,String value){
        redisUtils.set(key, value);
        return  "ok";

    }
    @ApiOperation(value = "查询")
    @RequestMapping("/getRedis")
    public Object getRedis(String key){
        return redisUtils.get(key);


    }
    @ApiOperation(value = "删除")
    @RequestMapping("/delRedis")
    public Object delRedis(String key){
        redisUtils.del(key);
        return  "ok";

    }

}
