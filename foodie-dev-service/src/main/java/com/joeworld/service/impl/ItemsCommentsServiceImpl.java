package com.joeworld.service.impl;

import com.github.pagehelper.PageHelper;
import com.joeworld.common.PageInfo;
import com.joeworld.enums.CommentLevel;
import com.joeworld.mapper.ItemsCommentsMapper;
import com.joeworld.pojo.ItemsComments;
import com.joeworld.pojo.vo.CommentLeveCountsVo;
import com.joeworld.pojo.vo.ItmeCommentVo;
import com.joeworld.pojo.vo.SearchItemsVo;
import com.joeworld.pojo.vo.ShopcartVo;
import com.joeworld.service.ItemsCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ItemsCommentsServiceImpl implements ItemsCommentsService {

    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;
    
    /**
    * 分页查询所有记录
    * @param map
    * @return
    */
    @Override
    public PageInfo<ClassInfo> findPage(Map<String, Object> map) {
        PageHelper.startPage(Integer.valueOf(map.get("pageCode").toString()), Integer.valueOf(map.get("pageSize").toString()));
        List<ClassInfo> page = itemsCommentsMapper.findPage(map);
        return new PageInfo<>(page);
    }
    

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    @Override
    public List<ItemsComments> listAll(Map<String,Object> map) {
    	return itemsCommentsMapper.listAll(map);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @Override
    public ItemsComments getById(String id) {
    	return itemsCommentsMapper.getById(id);
    }
	
    /**
     * 新增，插入所有字段
     *
     * @param itemsComments 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insert(ItemsComments itemsComments) {
    	return itemsCommentsMapper.insert(itemsComments);
    }
	
    /**
     * 新增，忽略null字段
     *
     * @param itemsComments 新增的记录
     * @return 返回影响行数
     */
    @Override
    public int insertIgnoreNull(ItemsComments itemsComments) {
    	return itemsCommentsMapper.insertIgnoreNull(itemsComments);
    }
	
    /**
     * 修改，修改所有字段
     *
     * @param itemsComments 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int update(ItemsComments itemsComments) {
    	return itemsCommentsMapper.update(itemsComments);
    }
	
    /**
     * 修改，忽略null字段
     *
     * @param itemsComments 修改的记录
     * @return 返回影响行数
     */
    @Override
    public int updateIgnoreNull(ItemsComments itemsComments) {
    	return itemsCommentsMapper.updateIgnoreNull(itemsComments);
    }
	
    /**
     * 删除记录
     *
     * @param itemsComments 待删除的记录
     * @return 返回影响行数
     */
    @Override
    public int delete(ItemsComments itemsComments) {
    	return itemsCommentsMapper.delete(itemsComments);
    }

    /**
     * 根据商品id查询商品评价等级
     * @param itemId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentLeveCountsVo selectCommentCounts(String itemId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("itemId",itemId);
        map.put("commentLevel", CommentLevel.GOOD.type);
        CommentLeveCountsVo goodCounts = itemsCommentsMapper.selectCommentCounts(map);
        map.put("commentLevel", CommentLevel.NORMAL.type);

        CommentLeveCountsVo normalCounts = itemsCommentsMapper.selectCommentCounts(map);
        map.put("commentLevel", CommentLevel.BAD.type);
        CommentLeveCountsVo badCounts = itemsCommentsMapper.selectCommentCounts(map);

        CommentLeveCountsVo commentLeveCountsVo = new CommentLeveCountsVo();
        commentLeveCountsVo.setGoodCounts(goodCounts.getPublicCounts());
        commentLeveCountsVo.setNormalCounts(normalCounts.getPublicCounts());
        commentLeveCountsVo.setBadCounts(badCounts.getPublicCounts());
        Integer totalCounts =commentLeveCountsVo.getGoodCounts() + commentLeveCountsVo.getNormalCounts() + commentLeveCountsVo.getBadCounts();
        commentLeveCountsVo.setTotalCounts(totalCounts);
        return commentLeveCountsVo;
    }

    @Override
    public PageInfo<ClassInfo> selectItmeComments( String itemId,String commentLevel,Integer page,Integer pageSize){
        PageHelper.startPage(page,pageSize);
        HashMap<String, Object> map = new HashMap<>();
        map.put("itemId",itemId);
        map.put("commentLevel",commentLevel);
        List<ClassInfo> lsit = itemsCommentsMapper.selectItmeComments(map);
        return new PageInfo<>(lsit);
    }

    @Override
    public PageInfo<ClassInfo> searchItems(String keywords, String sort, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        HashMap<String, Object> map = new HashMap<>();
        map.put("keywords",keywords);
        map.put("sort",sort);
        List<ClassInfo> list = itemsCommentsMapper.searchItems(map);
        return new PageInfo<>(list);
    }
    @Override
    public PageInfo<ClassInfo> searchItemsByThirdCat(String catId, String sort, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        HashMap<String, Object> map = new HashMap<>();
        map.put("catId",catId);
        map.put("sort",sort);
        List<ClassInfo> list = itemsCommentsMapper.searchItemsByThirdCat(map);
        return new PageInfo<>(list);
    }
@Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ShopcartVo> selectItemBySpecIds(String specIds) {
        String[] ids =  specIds.split(",");
        ArrayList<String> speclasList = new ArrayList<>();
        Collections.addAll(speclasList,ids); //对象拷贝
        return itemsCommentsMapper.selectItemBySpecIds(speclasList);
    }
    //food-1002-spec-1
}