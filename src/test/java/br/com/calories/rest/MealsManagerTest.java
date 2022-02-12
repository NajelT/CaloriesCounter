package br.com.calories.rest;

import java.util.Date;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;

import br.com.calories.model.Meal;

public class MealsManagerTest  extends JerseyTest {
	
    @Override
    protected ResourceConfig configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        return new JerseyConfig();
    }
    
    @Test
    public void test_addMeal() {
        WebTarget target = target();
        Date date = new Date();
		Meal responseMsg = target.path("/meals/add/1")
        		.queryParam("date", date.getTime())
        		.queryParam("description", "snack")
        		.queryParam("calories", new Integer(200))
        		.request(MediaType.APPLICATION_JSON).post(null, Meal.class);
        Assert.assertNotNull("Didn´t add the meal.", responseMsg);
        Assert.assertNotNull("Didn´t set an id.", responseMsg.getId());
        Assert.assertEquals("snack", responseMsg.getDescription());
        Assert.assertEquals(new Integer(200), responseMsg.getCalories());
        Assert.assertEquals(date, responseMsg.getDate());
    }
    
    @Test
    public void test_addMealToANonExistentUser() {
        WebTarget target = target();
        Meal responseMsg = target.path("/meals/add/999")
        		.request(MediaType.APPLICATION_JSON).post(null, Meal.class);
        Assert.assertNull("Added the meal to a non-existent .", responseMsg);
    }

    @Test
    public void test_removeMeal() {
        WebTarget target = target();
		Boolean responseMsg = target.path("/meals/remove/1/1")
        		.request(MediaType.APPLICATION_JSON).delete(Boolean.class);
        Assert.assertTrue("Didn´t remove the meal.", responseMsg);
    }

    @Test
    public void test_removeNonExistentMeal() {
        WebTarget target = target();
		Boolean responseMsg = target.path("/meals/remove/1/9999")
        		.request(MediaType.APPLICATION_JSON).delete(Boolean.class);
        Assert.assertFalse("Removed a non-existent meal.", responseMsg);
    }

    @Test
    public void test_updateMeal() {
        WebTarget target = target();
        Date date = new Date();
		Meal responseMsg = target.path("/meals/update/1/1")
        		.queryParam("date", date.getTime())
        		.queryParam("description", "any thing")
        		.queryParam("calories", new Integer(2))
        		.request(MediaType.APPLICATION_JSON).post(null, Meal.class);
        Assert.assertNotNull("Didn´t update the meal.", responseMsg);
        Assert.assertEquals(new Integer(1), responseMsg.getId());
        Assert.assertEquals("any thing", responseMsg.getDescription());
        Assert.assertEquals(new Integer(2), responseMsg.getCalories());
        Assert.assertEquals(date, responseMsg.getDate());
    }
    
    @Test
    public void test_updateMealToANonExistentUser() {
        WebTarget target = target();
        Meal responseMsg = target.path("/meals/update/1/999")
        		.request(MediaType.APPLICATION_JSON).post(null, Meal.class);
        Assert.assertNull("Updated a non-existent meal.", responseMsg);
    }
}
