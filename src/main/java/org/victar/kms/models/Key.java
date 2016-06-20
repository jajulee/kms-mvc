package org.victar.kms.models;

import org.springframework.data.annotation.Id;

public class Key {
	@Id
	private String id;
	
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
