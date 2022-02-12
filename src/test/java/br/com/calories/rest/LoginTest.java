package br.com.calories.rest;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;

import br.com.calories.model.Profile;
import br.com.calories.model.User;

public class LoginTest extends JerseyTest {
	
    @Override
    protected ResourceConfig configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        return new JerseyConfig();
    }

    @Test
    public void test_loginUserSuccess() {
        WebTarget target = target();
        User responseMsg = target.path("/login")
        		.queryParam("email", "cindy@email.com")
        		.queryParam("password", "senha")
        		.request(MediaType.APPLICATION_JSON).get(User.class);
        Assert.assertNotNull("Didn´t find any user.", responseMsg);
        Assert.assertEquals("Wrong user.", "Cindy Soares", responseMsg.getName());
        Assert.assertEquals("Wrong profile.", Profile.USER, responseMsg.getProfile());
        Assert.assertNull("Shouldn´t serialize password.", responseMsg.getPassword());
    }
    
    @Test
    public void test_loginUserWhenPasswordIsWrong() {
        WebTarget target = target();
        User responseMsg = target.path("/login")
        		.queryParam("email", "cindy@email.com")
        		.queryParam("password", "xxxx")
        		.request(MediaType.APPLICATION_JSON).get(User.class);
        Assert.assertNull("Shouldn´t return any user.", responseMsg);
    }

    @Test
    public void test_loginUserWhenUserDoesntExists() {
        WebTarget target = target();
        User responseMsg = target.path("/login")
        		.queryParam("email", "any@email.com")
        		.request(MediaType.APPLICATION_JSON).get(User.class);
        Assert.assertNull("Shouldn´t find any user.", responseMsg);
    }

}
