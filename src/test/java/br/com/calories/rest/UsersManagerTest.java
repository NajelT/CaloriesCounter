package br.com.calories.rest;

import java.util.Set;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;

import br.com.calories.model.Profile;
import br.com.calories.model.User;

public class UsersManagerTest extends JerseyTest {
	
    @Override
    protected ResourceConfig configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        return new JerseyConfig();
    }
    
    @Test
    public void test_addUser() {
    	WebTarget target = target();
		User responseMsg = target.path("/users/add")
        		.queryParam("name", "Michael")
        		.queryParam("email", "michael@email.com")
        		.queryParam("password", "pass")
        		.queryParam("profile", Profile.USER.name())
        		.request(MediaType.APPLICATION_JSON).post(null, User.class);
        Assert.assertNotNull("Didn´t add the user.", responseMsg);
        Assert.assertNotNull("Didn´t set an id.", responseMsg.getId());
        Assert.assertEquals("Michael", responseMsg.getName());
        Assert.assertEquals("michael@email.com", responseMsg.getEmail());
        Assert.assertEquals(null, responseMsg.getPassword());
        Assert.assertEquals(new Integer(2000), responseMsg.getCaloriesLimit());
        Assert.assertEquals(Profile.USER, responseMsg.getProfile());
    }
    
    @Test
    public void test_updateUser() {
    	WebTarget target = target();
    	User responseMsg = target.path("/users/update/1")
        		.queryParam("name", "Michael")
        		.queryParam("email", "michael@email.com")
        		.queryParam("password", "pass")
        		.queryParam("profile", Profile.USER_MANAGER.name())
        		.request(MediaType.APPLICATION_JSON).post(null, User.class);
        Assert.assertNotNull("Didn´t update the user.", responseMsg);
        Assert.assertEquals("Wrong id.", new Integer(1), responseMsg.getId());
        Assert.assertEquals("Michael", responseMsg.getName());
        Assert.assertEquals("michael@email.com", responseMsg.getEmail());
        Assert.assertNull(responseMsg.getPassword());
        Assert.assertEquals(Profile.USER_MANAGER, responseMsg.getProfile());
    }
    
    @Test
    public void test_updateNonExistentUser() {
    	WebTarget target = target();
    	User responseMsg = target.path("/users/update/999")
        		.queryParam("name", "Michael")
        		.queryParam("email", "michael@email.com")
        		.queryParam("password", "pass")
        		.queryParam("profile", Profile.USER_MANAGER.name())
        		.request(MediaType.APPLICATION_JSON).post(null, User.class);
        Assert.assertNull("Updated a non-existent user.", responseMsg);
    }
    
    @Test
    public void test_removeUser() {
    	WebTarget target = target();
    	boolean responseMsg = target.path("/users/remove/3")
        		.request(MediaType.APPLICATION_JSON).delete(Boolean.class);
        Assert.assertTrue("Didn´t remove the user.", responseMsg);
    }
    
    @Test
    public void test_removeNonExistentUser() {
    	WebTarget target = target();
    	boolean responseMsg = target.path("/users/remove/9999")
        		.request(MediaType.APPLICATION_JSON).delete(Boolean.class);
        Assert.assertFalse("Removed a non-existend user.", responseMsg);
    }
    
    @Test
    public void test_getAll() {
    	WebTarget target = target();
    	Set<?> responseMsg = target.path("/users/all")
        		.request(MediaType.APPLICATION_JSON).get(Set.class);
        Assert.assertNotNull("Didn´t find all users.", responseMsg);
    }


}
