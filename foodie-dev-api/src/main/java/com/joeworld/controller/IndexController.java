package com.joeworld.controller;

import com.joeworld.common.JsonUtils;
import com.joeworld.common.PageInfo;
import com.joeworld.common.R;
import com.joeworld.common.RedisUtils;
import com.joeworld.enums.YesOrNo;
import com.joeworld.pojo.Carousel;
import com.joeworld.pojo.Category;
import com.joeworld.pojo.vo.CateGoryVo;
import com.joeworld.pojo.vo.NewCateVo;
import com.joeworld.service.CarouselService;
import com.joeworld.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("indexController")
@CrossOrigin //跨域
/* 类注解 */
@Api(value = "首页",tags = {"首页相关接口的"})
public class IndexController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 获取轮播图列表
     * @return
     */
    @ApiOperation(value = "获取轮播图列表")
    @GetMapping("/selectListAllCarousel")
    public R selectListAll( ) {
        List<Carousel> carousels = new ArrayList<>();
        String carouselsStr = redisUtils.get("carousels");
        if (StringUtils.isEmpty(carouselsStr)) {
            HashMap<String, Object> params = new HashMap<>();
            params.put("isShow", YesOrNo.man.type);
            carousels =  carouselService.selectListAll(params);
            redisUtils.set("carousels", JsonUtils.objectToJson(carousels));
        }else {
            carousels = JsonUtils.jsonToList(carouselsStr,Carousel.class);
        }
        // list转换成json
        return  R.ok(carousels);
    }
    /**
     * 查询一级分类
     *  主页刷新查询大分类 渲染到首页
     *  鼠标移动到大分类上加载子分类内容 如果已经存在则不需要加载（懒加载）
     * @return
     */
    @ApiOperation(value = "查询一级分类")
    @GetMapping("/selectOneCarousel")
    public R selectOneCarousel( ) {
        List<Category> categories = new ArrayList<>();
        String categoriesStr = redisUtils.get("categories");
        if (StringUtils.isEmpty(categoriesStr)) {
            HashMap<String, Object> params = new HashMap<>();
            params.put("type", YesOrNo.man.type);
            categories = categoryService.listAll(params);
            redisUtils.set("categories",JsonUtils.objectToJson(categories));
        }else {
            categories= JsonUtils.jsonToList(categoriesStr, Category.class);
        }

        return  R.ok(categories);
    }


    /**
     * 查询二级分类
     * @return
     */
    @ApiOperation(value = "查询二级分类")
    @GetMapping("/selectTwoCarousel/{fatherId}")
    public R selectTwoCarousel(@PathVariable Integer fatherId) {
        if (fatherId == null) {
            return R.error("请求参数为空");
        }
        List<CateGoryVo> categories = new ArrayList<>();
        String categoriesTwo = redisUtils.get("categoriesTwo"+fatherId);
        if (StringUtils.isEmpty(categoriesTwo)) {
            categories = categoryService.selectTwoCarousel(fatherId);
            redisUtils.set("categoriesTwo"+fatherId, JsonUtils.objectToJson(categories));
        }else {
            categories = JsonUtils.jsonToList(categoriesTwo,CateGoryVo.class);
        }
        return  R.ok(categories);
    }


    /**
     * 查询首页下的没一级分类下的六个最新的商品
     * @return
     */
    @ApiOperation(value = "查询最新的商品")
    @GetMapping("/getSixNewItemsLazy/{rootCatId}")
    public R getSixNewItemsLazy(@PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            return R.error("请求参数为空");
        }
        List<NewCateVo> categories = categoryService.getSixNewItemsLazy(rootCatId);
        return  R.ok(categories);
    }
}