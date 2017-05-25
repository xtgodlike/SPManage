package com.qy.sp.manage.dto;

import java.io.Serializable;
import java.util.List;

public class PurviewSessionObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ModuleSessionObject> menu;// 菜单
	private List<TModule> limits; // 权限列表 
	private List<TModule> buttons; // 按钮 预留字段
	
	/**
	 * 判断是否包含改请求
	 * @param url
	 * @return
	 */
	public boolean isContainUrl(String url){
		
		//判断改请求是否是按钮
		/*for (TModule TModule : buttons) {
			if(TModule.getTModuleLink().indexOf(url)!=-1){
				return true;
			}
		}*/
		
		//请求是为菜单
		for (ModuleSessionObject ModuleSessionObject : menu) {
			TModule parentTModule = ModuleSessionObject.getParentModule();
			if(parentTModule.getModuleLink().indexOf(url)!=-1){
				return true;
			}
			
			List<TModule> childrenTModules = ModuleSessionObject.getChildrenModules();
			for (TModule TModule : childrenTModules) {
				if(TModule.getModuleLink().indexOf(url)!=-1){
					return true;
				}
			}
		}
		
		//是否为权限
		for (TModule TModule : limits) {
			if(TModule.getModuleLink().indexOf(url)!=-1){
				return true;
			}
		}
		return false;
	}

	public List<ModuleSessionObject> getMenu() {
		return menu;
	}

	public void setMenu(List<ModuleSessionObject> menu) {
		this.menu = menu;
	}

	public List<TModule> getButtons() {
		return buttons;
	}

	public void setButtons(List<TModule> buttons) {
		this.buttons = buttons;
	}

	public List<TModule> getLimits() {
		return limits;
	}

	public void setLimits(List<TModule> limits) {
		this.limits = limits;
	}
}
