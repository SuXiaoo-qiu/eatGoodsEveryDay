package com.joeworld.pojo.vo;

import com.joeworld.pojo.Items;
import com.joeworld.pojo.ItemsImg;
import com.joeworld.pojo.ItemsParam;
import com.joeworld.pojo.ItemsSpec;

import java.util.Date;
import java.util.List;

/**
 * 商品详情vo
 */
public class ItemInfoVo {


    private Items item;
    private List<ItemsImg> itemImgList;
    private ItemsParam itmesParam ;
    private List<ItemsSpec> itemsSpecList;

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public List<ItemsImg> getItemImgList() {
        return itemImgList;
    }

    public void setItemImgList(List<ItemsImg> itemImgList) {
        this.itemImgList = itemImgList;
    }

    public ItemsParam getItmesParam() {
        return itmesParam;
    }

    public void setItmesParam(ItemsParam itmesParam) {
        this.itmesParam = itmesParam;
    }

    public List<ItemsSpec> getItemsSpecList() {
        return itemsSpecList;
    }

    public void setItemsSpecList(List<ItemsSpec> itemsSpecList) {
        this.itemsSpecList = itemsSpecList;
    }
}