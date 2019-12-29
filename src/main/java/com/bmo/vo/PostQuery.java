package com.bmo.vo;

public class PostQuery {

	private String title;
	private Long typeId;
	private boolean recommend;
	
	public PostQuery() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public boolean isRecommend() {
		return recommend;
	}

	public void setRecommend(boolean recommend) {
		this.recommend = recommend;
	}
	
	
}
