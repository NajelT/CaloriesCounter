package br.com.calories.rest;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.TracingConfig;

public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {		
        packages(getClass().getPackage().getName());
        register(new JacksonFeature());
        register(LoggingFilter.class);
        property(ServerProperties.TRACING, TracingConfig.ON_DEMAND.name());
	}
	
}
