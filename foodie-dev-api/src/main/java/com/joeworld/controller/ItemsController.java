package com.joeworld.controller;

import com.joeworld.common.PageInfo;
import com.joeworld.common.R;
import com.joeworld.pojo.Items;
import com.joeworld.pojo.ItemsImg;
import com.joeworld.pojo.ItemsParam;
import com.joeworld.pojo.ItemsSpec;
import com.joeworld.pojo.vo.ItemInfoVo;
import com.joeworld.service.ItemsImgService;
import com.joeworld.service.ItemsParamService;
import com.joeworld.service.ItemsService;
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
@RequestMapping("itemsController")
@CrossOrigin //跨域
/* 类注解商品表 */
@Api(value = "商品相关",tags = {"商品相关的接口"})
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private ItemsImgService itemsImgService;

    @Autowired
    private ItemsParamService itemsParamService;

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
        PageInfo<ClassInfo> pageInfo = itemsService.findPage(params);
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
     List<Items> Items =  itemsService.listAll(params);
        return  R.ok(Items);
    }

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @ApiOperation(value = "根据id查询")
    @RequestMapping("/getById")
    public Items getById(String id) {
        return itemsService.getById(id);
    }    
     
    /**
     * 新增，忽略null字段
     *
     * @param items 新增的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "新增")
    @RequestMapping("/insert")
    public R insert(@RequestBody Items items) {
      int insert = itemsService.insertIgnoreNull(items);
          if (insert > 0) {
              return R.ok();
          }
          return R.error();
      }    
      
    /**
     * 修改，忽略null字段
     *
     * @param items 修改的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "修改")
    @RequestMapping("/update")
    public R update(@RequestBody Items items) {
    int update = itemsService.updateIgnoreNull(items);
      if (update > 0) {
              return R.ok();
          }
          return R.error();
      }
    
    /**
     * 删除记录
     *
     * @param items 待删除的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "删除物理删除")
    @RequestMapping("/delete")
    public R delete(@RequestBody Items items) {
     	int delete = itemsService.delete(items);
          if (delete > 0) {
              return R.ok("删除成功");
          }
          return R.error("删除失败");
      }


    /**
     * 根据"商品id 查询商品详情
     * @param itemId
     * @return
     */
    @ApiOperation(value = "根据商品id查询")
    @GetMapping("/getByitmeId/{itemId}")
    public R getByitmeId(@PathVariable("itemId") String itemId) {
        Items item = itemsService.getById(itemId);
        List<ItemsImg> itemsImgs = itemsImgService.getItemsId(itemId);
        ItemsParam itemsParam = itemsParamService.getItemsId(itemId);
        List<ItemsSpec> itemsSpecs = itemsSpecService.getItemsId(itemId);
        ItemInfoVo itemInfoVo = new ItemInfoVo();
        itemInfoVo.setItem(item);
        itemInfoVo.setItemImgList(itemsImgs);
        itemInfoVo.setItmesParam(itemsParam);
        itemInfoVo.setItemsSpecList(itemsSpecs);
        return R.ok(itemInfoVo);
    }

}