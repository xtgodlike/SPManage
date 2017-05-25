package com.qy.sp.manage.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RootMenu implements Serializable {

	/**
	 * 菜单根节点(一级菜单)
	 */
	private static final long serialVersionUID = 1L;

	private TModule selfMenu; // 一级菜单自身对象
	private List<SecondMenu> childMenu = new ArrayList<SecondMenu>(); // 二级菜单
	
	public RootMenu(){
		
	}

	public TModule getSelfMenu() {
		return selfMenu;
	}

	public void setSelfMenu(TModule selfMenu) {
		this.selfMenu = selfMenu;
	}

	public List<SecondMenu> getChildMenu() {
		return childMenu;
	}

	public void setChildMenu(List<SecondMenu> childMenu) {
		this.childMenu = childMenu;
	}

	
}
