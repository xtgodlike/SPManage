package com.qy.sp.manage.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SecondMenu implements Serializable {

	/**
	 * 二级菜单
	 */
	private static final long serialVersionUID = 1L;

	private TModule selfMenu; // 二级菜单自身对象
	private List<TModule> buttons = new ArrayList<TModule>(); // 二级菜单下的按钮

	public TModule getSelfMenu() {
		return selfMenu;
	}

	public void setSelfMenu(TModule selfMenu) {
		this.selfMenu = selfMenu;
	}

	public List<TModule> getButtons() {
		return buttons;
	}

	public void setButtons(List<TModule> buttons) {
		this.buttons = buttons;
	}

}
