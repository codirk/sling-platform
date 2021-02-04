package de.messetat.sling.core.components.services;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.jcr.api.SlingRepository;

@Component(service = ConfigServiceImpl.class, immediate = true)
@Designate(ocd = ConfigServiceImpl.Config.class)
public class ConfigServiceImpl  {

    @ObjectClassDefinition(name = "A simple cleanup task", description = "Simple demo for cron-job like task with properties")
    public static @interface Config {

        @AttributeDefinition(name = "A parameter", description = "Can be configured in /system/console/configMgr")
        String myParameter() default "";
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigServiceImpl.class);
    @Reference
    private SlingRepository repository;

    private String myParameter;

    public String getMyParameter() {
        return myParameter;
    }

    @Activate
    protected void activate(final Config config) {
        this.myParameter = (String.valueOf(config.myParameter()) != null) ? String.valueOf(config.myParameter())
                : null;
        LOGGER.info("configure: cleanupPath='{}''", this.myParameter);
    }


}
