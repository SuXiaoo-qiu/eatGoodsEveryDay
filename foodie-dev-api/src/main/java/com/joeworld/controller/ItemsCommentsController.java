package com.joeworld.controller;

import com.github.pagehelper.PageHelper;
import com.joeworld.common.PageInfo;
import com.joeworld.common.R;
import com.joeworld.enums.YesOrNo;
import com.joeworld.pojo.ItemsComments;
import com.joeworld.pojo.OrderStatus;
import com.joeworld.pojo.Orders;
import com.joeworld.pojo.bo.center.OrderItmesCommentBo;
import com.joeworld.pojo.vo.CommentLeveCountsVo;
import com.joeworld.pojo.vo.SearchItemsVo;
import com.joeworld.service.ItemsCommentsService;
import com.joeworld.service.OrderStatusService;
import com.joeworld.service.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("itemsCommentsController")
@CrossOrigin //跨域
/* 类注解 */
@Api(tags = {"商品评价相关"})
public class ItemsCommentsController {

    @Autowired
    private ItemsCommentsService itemsCommentsService;
    @Autowired
    private OrdersService ordersService;
    /**
     * 分页查询
     * @param params
     * @return
     */
    @ApiOperation(value = "条件查询分页")
    @RequestMapping("/findPage")
    public PageInfo<ClassInfo> findPage(@RequestParam Map<String, Object> params){
        PageInfo<ClassInfo> pageInfo = itemsCommentsService.findPage(params);
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
     List<ItemsComments> ItemsComments =  itemsCommentsService.listAll(params);
        return  R.ok(ItemsComments);
    }

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @ApiOperation(value = "根据id查询")
    @RequestMapping("/getById")
    public ItemsComments getById(String id) {
        return itemsCommentsService.getById(id);
    }    
     
    /**
     * 新增，忽略null字段
     *
     * @param itemsComments 新增的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "新增")
    @RequestMapping("/insert")
    public R insert(@RequestBody ItemsComments itemsComments) {
      int insert = itemsCommentsService.insertIgnoreNull(itemsComments);
          if (insert > 0) {
              return R.ok();
          }
          return R.error();
      }    
      
    /**
     * 修改，忽略null字段
     *
     * @param itemsComments 修改的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "修改")
    @RequestMapping("/update")
    public R update(@RequestBody ItemsComments itemsComments) {
    int update = itemsCommentsService.updateIgnoreNull(itemsComments);
      if (update > 0) {
              return R.ok();
          }
          return R.error();
      }
    
    /**
     * 删除记录
     *
     * @param itemsComments 待删除的记录
     * @return 返回影响行数
     */
    @ApiOperation(value = "删除物理删除")
    @RequestMapping("/delete")
    public R delete(@RequestBody ItemsComments itemsComments) {
     	int delete = itemsCommentsService.delete(itemsComments);
          if (delete > 0) {
              return R.ok("删除成功");
          }
          return R.error("删除失败");
      }

    /**
     *  根据商品id查询商品评价等级
     * @param itemId
     * @return
     */
    @ApiOperation(value = "根据商品id查询商品评价等级")
    @GetMapping("/selectCommentCounts")
    public R selectCommentCounts(@RequestParam String itemId) {
       return R.ok(itemsCommentsService.selectCommentCounts(itemId));
    }

    /**
     * 根据商品id 查询商品评价（分页）
     * @param itemId
     * @return
     */
    @ApiOperation(value = "根据商品id 查询商品评价（分页）")
    @GetMapping("/selectItmeComments")
    public R selectItmeComments(@RequestParam String itemId,String level,Integer page,Integer pageSize ) {
        if (null == page){
            page = 0;
        }
        if (null == pageSize){
            pageSize = 10;
        }
        return R.ok(itemsCommentsService.selectItmeComments( itemId, level, page, pageSize));
    }

    /**
     * 搜索商品列表
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "搜索商品列表（分页）")
    @GetMapping("/searchItems")
    public R searchItems(@RequestParam String keywords,String sort,Integer page,Integer pageSize) {
        if (null == page){
            page = 0;
        }
        if (null == pageSize){
            pageSize = 20;
        }
        return R.ok(itemsCommentsService.searchItems( keywords, sort, page, pageSize));
    }

    /**
     *  搜索商品列表 根据三级分类id
     * @param catId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "搜索商品列表根据三级分类id（分页）")
    @GetMapping("/searchItemsByThirdCat")
    public R searchItemsByThirdCat(@RequestParam String catId,String sort,Integer page,Integer pageSize) {
        if (null == page){
            page = 0;
        }
        if (null == pageSize){
            pageSize = 20;
        }
        return R.ok(itemsCommentsService.searchItemsByThirdCat( catId, sort, page, pageSize));
    }


    /**
     * 根据商品规格ids 查询最新的商品数据
     * @param itemSpecIds
     * @return
     */
    @ApiOperation(value = " 根据商品规格ids 查询最新的商品数据")
    @GetMapping("/selectItemBySpecIds")
    public R selectItemBySpecIds(@RequestParam String itemSpecIds) {

        return R.ok(itemsCommentsService.selectItemBySpecIds(itemSpecIds));
    }

    /**
     * 保存商品评价
     * @param userId
     * @param orderId
     * @return
     */
    @ApiOperation(value = "保存商品评价")
    @PostMapping("/saveComments")
    public R saveComments(@RequestParam String userId,
                  @RequestParam String orderId,
                  @RequestBody List<OrderItmesCommentBo> commentList) {

        Orders orders = ordersService.selectMyOrderOne(userId, orderId);
        if (orders == null) {
            return R.error("订单存在");
        }
        if (null == commentList || commentList.size() == 0) {
            return R.error("评论内容不能为空");
        }
        itemsCommentsService.saveComments(userId,orderId,commentList);
        return R.ok();
    }

    /**
     * 评价管理（查询全部评价 分页）
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "评价管理（查询全部评价 分页）")
    @PostMapping("/selectComments")
    public R selectComments(@RequestParam String userId, @RequestParam  Integer page,@RequestParam Integer pageSize) {
        if (null == page){
            page = 0;
        }
        if (null == pageSize){
            pageSize = 10;
        }
        return R.ok(itemsCommentsService.selectComments(userId, page, pageSize));
    }


}