package com.qy.sp.manage.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainMenu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<RootMenu> rootMenus = new ArrayList<RootMenu>();

	public List<RootMenu> getRootMenus() {
		return rootMenus;
	}

	public void setRootMenus(List<RootMenu> rootMenus) {
		this.rootMenus = rootMenus;
	}
	
	
	public MainMenu(List<TModule> TModules){
		initMenu(TModules);
	}
	
	/**
	 * 初始化菜单
	 * @param TModules
	 */
	private void initMenu(List<TModule> TModules){
		for (TModule TModule : TModules) {
			if(TModule.getModuleLevel()==1){ //一级菜单
				RootMenu rootMenu = new RootMenu();
				rootMenu.setSelfMenu(TModule);
				//System.out.println("一级菜单："+TModule.getTModuleName());
				//获取一级菜单下的所有二级菜单
				for (TModule TModule2 : TModules) {
					if(TModule.getModuleId().equals(TModule2.getParentMId())){
						//System.out.println("二级菜单："+TModule2.getTModuleName());
						SecondMenu sMenu = new SecondMenu();
						sMenu.setSelfMenu(TModule2);
						for (TModule TModule3 : TModules) {
							if(TModule2.getModuleId().equals(TModule3.getParentMId())){
								sMenu.getButtons().add(TModule3);
								//System.out.println("三级菜单："+TModule3.getTModuleName());
							}
						}
						rootMenu.getChildMenu().add(sMenu);
					}
				}
				rootMenus.add(rootMenu);
			}
		}
	}
	
}
