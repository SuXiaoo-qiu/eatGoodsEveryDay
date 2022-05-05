package com.joeworld.mapper;

import com.joeworld.pojo.ItemsImg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cglib.core.ClassInfo;

import java.util.List;
import java.util.Map;
@Mapper
public interface ItemsImgMapper {
	
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
	List<ItemsImg> listAll(Map<String,Object> map);


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	ItemsImg getById(String id);
	
	/**
     * 新增，插入所有字段
     *
     * @param itemsImg 新增的记录
     * @return 返回影响行数
     */
	int insert(ItemsImg itemsImg);
	
	/**
     * 新增，忽略null字段
     *
     * @param itemsImg 新增的记录
     * @return 返回影响行数
     */
	int insertIgnoreNull(ItemsImg itemsImg);
	
	/**
     * 修改，修改所有字段
     *
     * @param itemsImg 修改的记录
     * @return 返回影响行数
     */
	int update(ItemsImg itemsImg);
	
	/**
     * 修改，忽略null字段
     *
     * @param itemsImg 修改的记录
     * @return 返回影响行数
     */
	int updateIgnoreNull(ItemsImg itemsImg);
	
	/**
     * 删除记录
     *
     * @param itemsImg 待删除的记录
     * @return 返回影响行数
     */
	int delete(ItemsImg itemsImg);

	/**
	 * 根据商品外键id来查询商品图片
	 * @param itemId
	 * @return
	 */
	List<ItemsImg> getItemsId(String itemId);

	/**
	 * 和是否主图来拆线呢商品图片
	 * @param itemId
	 * @param isMain
	 * @return
	 */
	 String getByIdAndIsMain(String itemId, Integer isMain);
}