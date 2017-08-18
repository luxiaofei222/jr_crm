package com.jingren.jing.common.tree.bean;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class HighTree implements Serializable{

	private String id;//id 
	private String text;//内容
	private String state="closed";
	private boolean checked=false;//是否被选中
	private HighTree item;//节点
	private List<HighTree> children;//子节点
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public HighTree getItem() {
		return item;
	}
	public void setItem(HighTree item) {
		this.item = item;
	}
	public List<HighTree> getChildren() {
		return children;
	}
	public void setChildren(List<HighTree> children) {
		this.children = children;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
