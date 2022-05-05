package com.joeworld.controller;

import com.joeworld.common.PageInfo;
import com.joeworld.common.R;
import com.joeworld.pojo.ItemsImg;
import com.joeworld.pojo.ItemsSpec;
import com.joeworld.service.ItemsSpecService;
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
@RequestMapping("itemsSpecController")
@CrossOrigin //跨域
/* 类注解 */
@Api(tags = {"商品规格相关接口"})
public class ItemsSpecController {

    @Autowired
    private ItemsSpecService itemsSpecService;
    
    /**
     * 分页查询
     * @param params
     * @return
     */
    @ApiOperation(value = "条件查询分页")
    @RequestMapping("/findPage")
    public PageInfo<ClassInfo> findPage(@RequestParam Map<String, Object> params){
        PageInfo<ClassInfo> pageInfo = itemsSpecService.findPage(params);
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
     List<ItemsSpec> ItemsSpec =  itemsSpecService.listAll(params);
        return  R.ok(ItemsSpec);
    }

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @ApiOperation(value = "根据id查询")
    @RequestMapping("/getById")
    public ItemsSpec getById(String id) {
        return itemsSpecService.getById(id);
    }    
     
    /**
     * 新增，忽略null字段
     *
     * @param itemsSpec 新增的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "新增")
    @RequestMapping("/insert")
    public R insert(@RequestBody ItemsSpec itemsSpec) {
      int insert = itemsSpecService.insertIgnoreNull(itemsSpec);
          if (insert > 0) {
              return R.ok();
          }
          return R.error();
      }    
      
    /**
     * 修改，忽略null字段
     *
     * @param itemsSpec 修改的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "修改")
    @RequestMapping("/update")
    public R update(@RequestBody ItemsSpec itemsSpec) {
    int update = itemsSpecService.updateIgnoreNull(itemsSpec);
      if (update > 0) {
              return R.ok();
          }
          return R.error();
      }
    
    /**
     * 删除记录
     *
     * @param itemsSpec 待删除的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "删除物理删除")
    @RequestMapping("/delete")
    public R delete(@RequestBody ItemsSpec itemsSpec) {
     	int delete = itemsSpecService.delete(itemsSpec);
          if (delete > 0) {
              return R.ok("删除成功");
          }
          return R.error("删除失败");
      }


    /**
     * 根据商品外键id来查询啥商品规格
     * @param itemId
     * @return
     */
    @ApiOperation(value = "根据商品外键id来查询啥商品规格")
    @GetMapping("getImet/{itemId}")
    public List<ItemsSpec> getItemsId(@RequestParam String itemId) {
        return itemsSpecService.getItemsId(itemId);
    }

}