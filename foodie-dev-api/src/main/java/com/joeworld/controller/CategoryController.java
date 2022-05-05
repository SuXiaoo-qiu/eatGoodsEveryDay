package com.joeworld.controller;

import com.joeworld.common.PageInfo;
import com.joeworld.common.R;
import com.joeworld.pojo.Category;
import com.joeworld.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("categoryController")
@CrossOrigin //跨域
/* 类注解 */
@Api(value = "")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    /**
     * 分页查询
     * @param params
     * @return
     */
    @ApiOperation(value = "条件查询分页")
    @RequestMapping("/findPage")
    public PageInfo<ClassInfo> findPage(@RequestParam Map<String, Object> params){
        PageInfo<ClassInfo> pageInfo = categoryService.findPage(params);
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
     List<Category> Category =  categoryService.listAll(params);
        return  R.ok(Category);
    }

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @ApiOperation(value = "根据id查询")
    @RequestMapping("/getById")
    public Category getById(Integer id) {
        return categoryService.getById(id);
    }    
     
    /**
     * 新增，忽略null字段
     *
     * @param category 新增的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "新增")
    @RequestMapping("/insert")
    public R insert(@RequestBody Category category) {
      int insert = categoryService.insertIgnoreNull(category);
          if (insert > 0) {
              return R.ok();
          }
          return R.error();
      }    
      
    /**
     * 修改，忽略null字段
     *
     * @param category 修改的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "修改")
    @RequestMapping("/update")
    public R update(@RequestBody Category category) {
    int update = categoryService.updateIgnoreNull(category);
      if (update > 0) {
              return R.ok();
          }
          return R.error();
      }
    
    /**
     * 删除记录
     *
     * @param category 待删除的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "删除物理删除")
    @RequestMapping("/delete")
    public R delete(@RequestBody Category category) {
     	int delete = categoryService.delete(category);
          if (delete > 0) {
              return R.ok("删除成功");
          }
          return R.error("删除失败");
      }
}