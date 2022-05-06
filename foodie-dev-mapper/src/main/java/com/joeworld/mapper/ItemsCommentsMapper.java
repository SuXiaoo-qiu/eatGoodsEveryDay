package com.joeworld.mapper;

import com.joeworld.pojo.ItemsComments;
import com.joeworld.pojo.OrderStatus;
import com.joeworld.pojo.vo.CommentLeveCountsVo;
import com.joeworld.pojo.vo.ShopcartVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cglib.core.ClassInfo;

import java.util.List;
import java.util.Map;
@Mapper
public interface ItemsCommentsMapper {
	
    /**
	 * 分页查询所有记录
	 *
	 * @return 返回集合，没有返回空List
	 */
	List<ClassInfo> findPage(Map<String,Object> map);


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
	public CommentLeveCountsVo selectCommentCounts(Map map);

	/**
	 * 商品评价
	 * @param map
	 * @return
	 */
	public List<ClassInfo>  selectItmeComments(@Param("paramsMap")Map map);

	/**
	 * 搜索商品列表
	 * @param map
	 * @return
	 */
	public List<ClassInfo>  searchItems(@Param("paramsMap")Map map);

	/**
	 * 根据三级分类id搜索商品
	 * @param map
	 * @return
	 */
	public List<ClassInfo>  searchItemsByThirdCat(@Param("paramsMap")Map map);


	/**
	 * 根据三级分类id搜索商品
	 * @param map
	 * @return
	 */
	public List<ShopcartVo>  selectItemBySpecIds(@Param("paramsList")List specIdsList);


	void saveComments(Map<String,Object> map);


	public List<ClassInfo>  selectComments(@Param("paramsMap")Map map);

	int getMyOrderCommentsCount(@Param("paramsMap")Map map);

	List<ClassInfo>  getOrderTrend(@Param("paramsMap")Map map);


}