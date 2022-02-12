package br.com.calories.rest;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;

public class SettingsTest extends JerseyTest {
	
    @Override
    protected ResourceConfig configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        return new JerseyConfig();
    }
    
    @Test
    public void test_settingCaloriesLimitToAnExistentUser() {
        WebTarget target = target();
        boolean responseMsg = target.path("/settings/1/2000")
        		.request(MediaType.APPLICATION_JSON).post(null, Boolean.class);
        Assert.assertTrue("Didn´t updated the calories limit.", responseMsg);
    }

    @Test
    public void test_settingCaloriesLimitToNonExistentgUser() {
        WebTarget target = target();
        boolean responseMsg = target.path("/settings/999/2000")
        		.request(MediaType.APPLICATION_JSON).post(null, Boolean.class);
        Assert.assertFalse("Updated the calories limit of a non-existing user.", responseMsg);
    }

}
