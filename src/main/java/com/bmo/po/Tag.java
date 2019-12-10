package com.bmo.po;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Bmo  2019-12-10  
 *
 */
@Entity
@Table(name = "t_tag")
public class Tag {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	
	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@ManyToMany(mappedBy = "tags")
	private List<Post> posts = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + "]";
	}
	
	
}
