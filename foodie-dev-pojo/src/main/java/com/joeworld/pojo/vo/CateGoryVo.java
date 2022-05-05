package com.joeworld.pojo.vo;

import java.util.List;

/**
 * 二级分类vo
 */
public class CateGoryVo {
    private Integer id;
    private Integer type;
    private Integer fatherId;
    private String name;
    private List<SubCateGoryVo> subCateGoryVoList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubCateGoryVo> getSubCateGoryVoList() {
        return subCateGoryVoList;
    }

    public void setSubCateGoryVoList(List<SubCateGoryVo> subCateGoryVoList) {
        this.subCateGoryVoList = subCateGoryVoList;
    }
}
