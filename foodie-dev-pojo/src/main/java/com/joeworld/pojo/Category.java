package com.joeworld.pojo;


/**
 * 商品分类
 */
public class Category {


	/** 主键;分类id主键 */
	private Integer id;
	/** 分类名称;分类名称 */
	private String name;
	/** 分类类型;分类得类型，
1:一级大分类
2:二级分类
3:三级小分类 */
	private Integer type;
	/** 父id;父id 上一级依赖的id，1级分类则为0，二级三级分别依赖上一级 */
	private Integer fatherId;
	/** 图标;logo */
	private String logo;
	/** 口号 */
	private String slogan;
	/** 分类图 */
	private String catImage;
	/** 背景颜色 */
	private String bgColor;

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getType() {
		return this.type;
	}
	
	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}
	
	public Integer getFatherId() {
		return this.fatherId;
	}
	
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public String getLogo() {
		return this.logo;
	}
	
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
	
	public String getSlogan() {
		return this.slogan;
	}
	
	public void setCatImage(String catImage) {
		this.catImage = catImage;
	}
	
	public String getCatImage() {
		return this.catImage;
	}
	
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}
	
	public String getBgColor() {
		return this.bgColor;
	}
	

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        Category that = (Category) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Category{" +
				"id=" + id +
						",name='" + name + "'" + 
						",type='" + type + "'" + 
						",fatherId='" + fatherId + "'" + 
						",logo='" + logo + "'" + 
						",slogan='" + slogan + "'" + 
						",catImage='" + catImage + "'" + 
						",bgColor='" + bgColor + "'" + 
		                '}';
    }
	
}