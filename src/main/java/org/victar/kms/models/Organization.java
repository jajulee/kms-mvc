package org.victar.kms.models;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="organizations")
public class Organization {
	@Id
	private ObjectId id;
	@Indexed
	private String name;
	private String code;
	
	@DBRef
	private List<User> users = new ArrayList<User>();

	public List<User> getRoles() {
		return this.users;
	}

	public void setRoles(List<User> users) {
		this.users = users;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
