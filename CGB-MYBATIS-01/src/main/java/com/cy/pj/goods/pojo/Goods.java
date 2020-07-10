package com.cy.pj.goods.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 基于此商品对象封装数据(商品信息) 对象靠属性实现存储数据
 * @author soft01
 *POJO对象(简单的Java对象)
 */
public class Goods implements Serializable{
	private static final long serialVersionUID = 690138036951052829L;
	/**商品ID*/
	private Long id;
	/**商品名称*/
	private String name;
	/**商品备注*/
	private String remark;
	/**创建时间*/
	private Date createdTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", remark=" + remark + ", createdTime=" + createdTime + "]";
	}
	
}
