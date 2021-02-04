package de.messetat.sling.core.components.services;

import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = ConfigServiceFactoryImpl.class, immediate = true)
@Designate(ocd = ConfigServiceFactoryImpl.Config.class, factory = true)
public class ConfigServiceFactoryImpl {

    @ObjectClassDefinition(name = "A simple cleanup task", description = "Simple demo for cron-job like task with properties")
    public static @interface Config {

        @AttributeDefinition(name = "A parameter", description = "Can be configured in /system/console/configMgr")
        String myParameter() default "";
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigServiceFactoryImpl.class);
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
