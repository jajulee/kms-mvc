package org.victar.kms;

import org.testng.annotations.Test;
import org.victar.kms.models.Role;
import org.victar.kms.models.User;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

import org.testng.annotations.BeforeMethod;

import java.net.UnknownHostException;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class MongoDbRoleTests {
	private final String dbName = "kms";
	private MongoTemplate mongoTemplate;
	private Role defaultRole;
	private MongoClient mongoClient;
	private Gson gson;

	@Test(priority = 1)
	public void addRole() {
		try {
			mongoTemplate.save(defaultRole);
			Reporter.log("Add role: " + gson.toJson(this.defaultRole), true);
		} catch (Exception e) {
			org.testng.Assert.fail("Failed to add role. " + e.getMessage());
		}
	}

	@Test(priority = 2)
	public void findOne() {
		Role r = mongoTemplate.findOne(new Query(Criteria.where("name").is(defaultRole.getName())), Role.class);
		Assert.notNull(r);
	}

	@Test(priority = 3)
	public void saveRole() {
		String originalName = this.defaultRole.getName();
		Role r = mongoTemplate.findOne(new Query(Criteria.where("name").is(defaultRole.getName())), Role.class);
		r.setName("changed name by 'SaveRole' unit test");
		try{
			mongoTemplate.save(r);
			Reporter.log("Saved default role as : " + gson.toJson(r), true);
		}
		catch(Exception e){
			org.testng.Assert.fail("Failed to save role. " + e.getMessage());
		}
		// change the name back so delete method can be executed
		r.setName(originalName);
		try{
			mongoTemplate.save(r);
			Reporter.log("Saved default role as : " + gson.toJson(r), true);
		}
		catch(Exception e){
			org.testng.Assert.fail("Failed to save role. " + e.getMessage());
		}

	}

	@Test(priority = 4)
	public void deleteRole() {
		int rowsAffected = delete(this.defaultRole);
		Reporter.log("Number of roles deleted: " + rowsAffected + ", removed role: " + gson.toJson(this.defaultRole),
				true);
		// if rowsAffected == 0, record was not found; if rowsAffected > 1, fail
		// test
		Assert.isTrue(rowsAffected == 0 || rowsAffected == 1);
	}

	private int delete(Role r) {
		WriteResult result = mongoTemplate.remove(new Query(Criteria.where("name").is(r.getName())), Role.class);
		return result.getN();
	}

	@BeforeMethod
	public void beforeMethod() throws UnknownHostException {
		this.defaultRole = new Role();
		this.defaultRole.setName("test role");
		gson = new Gson();
		this.mongoClient = new MongoClient();
		mongoTemplate = new MongoTemplate(mongoClient, dbName);
	}

	@AfterMethod
	public void afterMethod() {
		mongoClient.close();
	}

}
