package com.joeworld.controller;


import com.joeworld.common.*;
import com.joeworld.pojo.Users;
import com.joeworld.pojo.bo.ShopcartBo;
import com.joeworld.pojo.vo.UserVo;
import com.joeworld.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("usersController")
@CrossOrigin //跨域
/* 类注解 */
@Api(value = "用户",tags = {"用于登录注册展示的相关用户接口"})
public class UsersController  extends BaseConller{
    final static Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersService usersService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @ApiOperation(value = "条件查询分页")
    @RequestMapping("/findPage")
    public PageInfo<ClassInfo> findPage(@RequestParam Map<String, Object> params){
        PageInfo<ClassInfo> pageInfo = usersService.findPage(params);
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
     List<Users> Users =  usersService.listAll(params);
     logger.info("查询参数"+Users.toString());
        return  R.ok(Users);
    }

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @ApiOperation(value = "根据id查询")
    @RequestMapping("/getById")
    public Users getById(String id) {
        return usersService.getById(id);
    }    
     
    /**
     * 新增，忽略null字段
     *
     * @param users 新增的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "新增")
    @RequestMapping("/insert")
    public R insert(@RequestBody Users users) {
      int insert = usersService.insertIgnoreNull(users);
          if (insert > 0) {
              return R.ok();
          }
          return R.error();
      }    
      
    /**
     * 修改，忽略null字段
     *
     * @param users 修改的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "修改")
    @RequestMapping("/update")
    public R update(@RequestBody Users users) {
    int update = usersService.updateIgnoreNull(users);
      if (update > 0) {
              return R.ok();
          }
          return R.error();
      }
    
    /**
     * 删除记录
     *
     * @param users 待删除的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "删除物理删除")
    @RequestMapping("/delete")
    public R delete(@RequestBody Users users) {
     	int delete = usersService.delete(users);
          if (delete > 0) {
              return R.ok("删除成功");
          }
          return R.error("删除失败");
      }

    /**、
     * 根据用户名进行查询用户是否存在
     * @param username
     * @return
     */
    @ApiOperation(value = "根据用户名进行查询用户是否存在")
    @RequestMapping("/selectUserNameIsExist")
    public R selectUserNameIsExist(@RequestParam String username) {
        if (StringUtils.isEmpty(username)) {
            return R.error("用户名不能为空");
        }
        // 如果存在就提示错误
        if (usersService.selectUserNameIsExist(username)){
            return R.error("用户名存在");
        }
        return R.ok();
    }


    /**
     * 创建用户
     * @param userVo
     * @return
     */
    @ApiOperation(value = "创建用户")
    @PostMapping("/createUser")
    public R createUser(@RequestBody UserVo userVo,HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(userVo.getUsername())||
                StringUtils.isEmpty(userVo.getPassword())||
                StringUtils.isEmpty(userVo.getConfirmPassword())) {
            return R.error("用户名或密码不能为空");
        }
        if (usersService.selectUserNameIsExist(userVo.getUsername())){
            return R.error("用户名存在");
        }
        if (userVo.getPassword().length() < 6){
            return R.error("密码长度不能小于六位");
        }
        if (!userVo.getPassword().equals(userVo.getConfirmPassword())){
            return R.error("两次密码输入不一致");
        }
        Users user = usersService.createUser(userVo);
        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(user),true);
        this.synchShopcartData(user.getId(),request,response);

        return R.ok(user);
    }


    /**
     * 登录
     * @param user
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "登录")
    @PostMapping("/userLogin")
    public R userLogin(@RequestBody Users user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (StringUtils.isBlank(user.getUsername())||
                StringUtils.isEmpty(user.getPassword())) {
            return R.error("用户名或密码不能为空");
        }
        Users usersRest = usersService.userLogin(user.getUsername(), MD5Utils.getMD5Str(user.getPassword()));
        if (null == usersRest){
            return R.error("用户名或密码错误");
        }
        /**
         * isEncode 是否加密 true是  false否
         */
        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(usersRest),true);
        // 同步购物车数据
        this.synchShopcartData(usersRest.getId(),request,response);
        return R.ok(usersRest);
    }

    /**
     * 注册登录后同步cookie 和redis中的购物车数据
     *
     * 1. redis 中如果没有数据  如果cooki中的购车为空 不做任何处理 如果cookie中的购物车不为空那就吧cookie的数据放入redis中
     * 2。redis 中如果有数据 如果cookie中的购物车不为空 那么就把redis中的数据放入cookie中 覆盖本地的cookie 如果cookie不为空（redis和cookie都不为空） 那就以cookie为主 redis没有的就从cookie添加到redis中
     * 3. 同步到redis中了以后 覆盖本地cookie 购物车的数据 保证本地购车数据是最新的
     */
    private void  synchShopcartData(String userId,HttpServletRequest request, HttpServletResponse response){
        // 获取redis 购物车中数据
        String shopcartStrRedis = redisUtils.get(FOODIE_SHOPCART + userId);
        // 获取cookie 购物车中数据
        String shopcartStrCookie = CookieUtils.getCookieValue(request, FOODIE_SHOPCART, true);
        if (StringUtils.isBlank(shopcartStrRedis)){
            if (StringUtils.isNotBlank(shopcartStrCookie)){
                redisUtils.set(FOODIE_SHOPCART+userId,shopcartStrCookie);
            }
        }else {
            // redis不为空 cookie也不为空 就合并 （如果同一商品cookie覆盖redis）
            if (StringUtils.isNotBlank(shopcartStrCookie)) {
                /**
                 * 1. 已经存在的 把cookie 中对应的数量 覆盖redis （根据具体需求） 也可以全部覆盖
                 * 2. 把这个数据标记成待删除 统一放入一个list中
                 * 3. 从cookie中清理所有待删除的数据
                 * 4。 合并redis嗯哼cookie中的数据
                 * 5. 更新到redis和cookie中
                 */
                List<ShopcartBo> shopcartBosRedis= JsonUtils.jsonToList(shopcartStrRedis, ShopcartBo.class);
                List<ShopcartBo> shopcartBosCookie = JsonUtils.jsonToList(shopcartStrCookie, ShopcartBo.class);
                // 定义一个待删除的list
                ArrayList<ShopcartBo> shopcartBosDelete = new ArrayList<>();

                for (ShopcartBo shopcartBoRedis : shopcartBosRedis) {
                    String specIdRedis = shopcartBoRedis.getSpecId();
                    for (ShopcartBo shopcartBoCookie : shopcartBosCookie) {
                        String specIdCookie = shopcartBoCookie.getSpecId();

                        if (specIdRedis.equals(specIdCookie)){
                            //如果redis 和 cookie 中的数据都不为空 那就把redis 中的数据进行覆盖 （把coookie的数量覆盖到redis中）
                            shopcartBoRedis.setBuyCounts(shopcartBoCookie.getBuyCounts());
                            // 放入待删除list 等下合并完删除
                            shopcartBosDelete.add(shopcartBoCookie);
                        }

                    }

                }
                //删除cookie已经合并的数据
                shopcartBosCookie.removeAll(shopcartBosDelete);
                // 把cookie中剩下数据进行覆盖到redis 这个就是最新的数据
                shopcartBosRedis.addAll(shopcartBosCookie);
                // 跟新到cookie 和redis
                redisUtils.set(FOODIE_SHOPCART+userId,JsonUtils.objectToJson(shopcartBosRedis));
                CookieUtils.setCookie(request, response,FOODIE_SHOPCART,JsonUtils.objectToJson(shopcartBosRedis),true);

            }else {
                // redis 不为空  cookie为空 直接覆盖cookie
                CookieUtils.setCookie(request, response,FOODIE_SHOPCART,shopcartStrRedis,true);

            }
        }


    }


    /**
     * 测试session
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "测试session")
    @GetMapping("/getSession")
    public Object getSession(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        session.setAttribute("userInfo","new user");
        session.setMaxInactiveInterval(3600);
        session.getAttribute("userInfo");
     //   session.removeAttribute("userInfo");
        return R.ok();

    }


    /**
     * 退出登录
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public R logout(@RequestParam String userId,HttpServletRequest request,HttpServletResponse response) throws Exception {
        CookieUtils.deleteCookie(request,response, "user");

        // TODO: 2022/4/5  用户推出登录，清空购物车 
        // 2022/4/5  分布式会话中需要清除用户数据
        CookieUtils.deleteCookie(request,response, FOODIE_SHOPCART);

        return R.ok();
    }


}