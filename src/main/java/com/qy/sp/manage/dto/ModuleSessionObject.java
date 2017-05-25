package com.qy.sp.manage.dto;

import java.io.Serializable;
import java.util.List;

public class ModuleSessionObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private TModule parentModule; // 父模块(菜单)
	private List<TModule> childrenModules; // 子模块集合
	public TModule getParentModule() {
		return parentModule;
	}
	public void setParentModule(TModule parentModule) {
		this.parentModule = parentModule;
	}
	public List<TModule> getChildrenModules() {
		return childrenModules;
	}
	public void setChildrenModules(List<TModule> childrenModules) {
		this.childrenModules = childrenModules;
	}


}
