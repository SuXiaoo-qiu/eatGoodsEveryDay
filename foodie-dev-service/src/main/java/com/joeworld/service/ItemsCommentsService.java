package com.joeworld.service;


import com.joeworld.common.PageInfo;
import com.joeworld.pojo.ItemsComments;
import com.joeworld.pojo.vo.CommentLeveCountsVo;
import com.joeworld.pojo.vo.ItmeCommentVo;
import com.joeworld.pojo.vo.SearchItemsVo;
import com.joeworld.pojo.vo.ShopcartVo;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsService {

    /**
    *   分页查询所有记录
    * @param map
    * @return
    */
    PageInfo<ClassInfo> findPage(Map<String,Object> map);
   
	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<ItemsComments> listAll(Map<String,Object> map);

	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	ItemsComments getById(String id);
	
	/**
     * 新增，插入所有字段
     *
     * @param itemsComments 新增的记录
     * @return 返回影响行数
     */
	int insert(ItemsComments itemsComments);
	
	/**
     * 新增，忽略null字段
     *
     * @param itemsComments 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(ItemsComments itemsComments);
	
	/**
     * 修改，修改所有字段
     *
     * @param itemsComments 修改的记录
     * @return 返回影响行数
     */
	int update(ItemsComments itemsComments);
	
	/**
     * 修改，忽略null字段
     *
     * @param itemsComments 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(ItemsComments itemsComments);
	
	/**
     * 删除记录
     *
     * @param itemsComments 待删除的记录
     * @return 返回影响行数
     */
	int delete(ItemsComments itemsComments);

	/**
	 * 根据商品id查询商品评价等级
	 * @param itemId
	 * @return
	 */
	public CommentLeveCountsVo selectCommentCounts(String itemId);

	/**
	 * 根据商品id 查询商品评价（分页）
	 * @param map
	 * @return
	 */
	public PageInfo<ClassInfo> selectItmeComments( String itemId,String commentLevel,Integer page,Integer pageSize);

	/**
	 * 搜索商品列表
	 * @param keywords
	 * @param sort
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public PageInfo<ClassInfo> searchItems( String keywords,String sort,Integer page,Integer pageSize);

	public PageInfo<ClassInfo> searchItemsByThirdCat( String catId,String sort,Integer page,Integer pageSize);

	public List<ShopcartVo> selectItemBySpecIds(String specIds);
}