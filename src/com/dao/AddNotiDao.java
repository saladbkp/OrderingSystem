/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.model.Notifications;
import com.tool.TextFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khwon
 */
public class AddNotiDao {
    ArrayList<Notifications> notiarray = new ArrayList<Notifications>();
	
	public AddNotiDao() {
		TextFunction txtfunc = new TextFunction("src/data/notifications.txt");
		notiarray = txtfunc.readfile(Notifications.class);
	}

	// view
        public List<Notifications> findDataByCus(String id) {
		List<Notifications> findarray = this.notiarray.stream().filter(x->x.getTargetId().equals(id)).toList();
		return findarray;
	}
}
