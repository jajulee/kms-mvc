package org.victar.kms;

import org.testng.annotations.Test;
import org.victar.kms.models.Role;
import org.victar.kms.models.User;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

import org.testng.annotations.BeforeMethod;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class MongoDbUserTests {
	private final String dbName = "kms";
	
	private User defaultUser;
	private MongoClient mongoClient;
	private Gson gson;
	private MongoTemplate mongoTemplate;
	
	@Test(priority = 1)
	public void addUser(){
		
		try{
			mongoTemplate.save(this.defaultUser);
			Reporter.log("Add user: " + gson.toJson(this.defaultUser), true);
		}
		catch(Exception e){
			org.testng.Assert.fail("Failed to add user. " + e.getMessage());
		}
	}
	
	@Test(priority = 2)
	public void findUser(){
		User u = mongoTemplate.findOne(new Query(Criteria.where("username").is("testUser")), User.class);
		Reporter.log("Found user: " + gson.toJson(u), true);
		Assert.notNull(u);
	}
	
	@Test(priority = 10)
	public void deleteUser(){
		int affectedRows = delete();
		mongoTemplate.remove(new Query(Criteria.where("name").is("add user test role")), Role.class);
		Assert.isTrue(affectedRows == 1);
	}
	
	private int delete(){
		WriteResult result = mongoTemplate.remove(new Query(Criteria.where("username").is("testUser")), User.class);
		Reporter.log("Deleted user by name. Rows affected: " + result.getN(), true);
		return result.getN();
	}
	
	private User createUser(){
		
		User user = new User();
		user.setUsername("testUser");
		user.setFirstName("First Name");
		user.setMiddleName("Middle Name");
		user.setLastName("Last Name");
		user.setEmail("myEmail@domain.com");
		user.setPhone("555-555-1212");
		List<Role> roles = new ArrayList<Role>();
	
		Role r = mongoTemplate.findOne(new Query(Criteria.where("name").is("add user test role")), Role.class); 
		if(r == null){
			r = new Role();
			r.setName("add user test role");
			mongoTemplate.save(r);
		}		
		roles.add(r);
		user.setRoles(roles);
		return user;
	}
	
	@BeforeMethod
	public void beforeMethod() throws UnknownHostException {
		this.gson = new Gson();
		this.mongoClient = new MongoClient();
		mongoTemplate = new MongoTemplate(mongoClient, dbName);
		this.defaultUser = createUser();
	}

	@AfterMethod
	public void afterMethod() {
		mongoClient.close();
	}
}
