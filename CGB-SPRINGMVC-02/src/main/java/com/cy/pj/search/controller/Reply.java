package com.cy.pj.search.controller;
/**
 * 答复对象
 * @author soft01
 *
 */
public class Reply {
	private String content;
	private Integer id;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Reply [content=" + content + ", id=" + id + "]";
	}
	
}
