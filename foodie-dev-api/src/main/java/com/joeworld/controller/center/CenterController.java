package com.joeworld.controller.center;

import com.joeworld.common.R;
import com.joeworld.pojo.Users;
import com.joeworld.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("center")
@CrossOrigin //跨域
/* 类注解 */
@Api(value = "用户中心",tags = {"center-用户中心相关的接口"})
public class CenterController {

    @Autowired
    private UsersService usersService;



    @ApiOperation(value = "获取用户信息")
    @GetMapping("findUserByIdInfo")
    public R findUserByIdInfo(@RequestParam String userId){
        Users userbyId = usersService.getById(userId);
        userbyId.setPassword("");
        return R.ok(userbyId);

    }

}
