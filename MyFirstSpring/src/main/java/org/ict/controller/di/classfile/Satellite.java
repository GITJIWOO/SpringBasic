package org.ict.controller.di.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Satellite {
	@Autowired
	private Broadcast broadcast;
	
	public Satellite(Broadcast broadcast) {
		this.broadcast = broadcast;
	}
	
	public void Broadcasting() {
		System.out.print("À§¼º ");
		broadcast.broadcast();
	}
}
