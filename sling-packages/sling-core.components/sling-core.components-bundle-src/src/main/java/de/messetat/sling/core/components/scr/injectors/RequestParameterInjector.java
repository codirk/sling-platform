package de.messetat.sling.core.components.scr.injectors;


import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.models.spi.DisposalCallbackRegistry;
import org.apache.sling.models.spi.Injector;
import org.osgi.framework.Constants;

import javax.servlet.ServletRequest;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;

@Component
@Service
@Property(name = Constants.SERVICE_RANKING, intValue = Integer.MIN_VALUE)
public class RequestParameterInjector implements Injector {
    @Override
    public String getName() {
        return "request-parameter";
    }

    @Override
    public Object getValue(final Object adaptable,
                           final String fieldName,
                           final Type type,
                           final AnnotatedElement annotatedElement,
                           final DisposalCallbackRegistry disposalCallbackRegistry) {

        if (adaptable instanceof ServletRequest) {
            final ServletRequest request = (ServletRequest) adaptable;
            if (type instanceof Class<?>) {
                Class<?> fieldClass = (Class<?>) type;
                return getValue(request, fieldClass, fieldName);
            }
        }
        return null;
    }

    private Object getValue(final ServletRequest request, final Class<?> fieldClass, final String fieldName) {
        /*
        if (StringUtils.isBlank(request.getParameter(fieldName))) {
            return null;
        }
         */
        if (fieldClass.equals(Integer.class)) {
            try {
                String parameterValue = request.getParameter(fieldName);
                return Integer.parseInt(parameterValue);
            } catch (NumberFormatException ex) {
                return null;
            }
        } else if (fieldClass.equals(Boolean.class)) {
            String parameterValue = request.getParameter(fieldName);
            return Boolean.parseBoolean(parameterValue);
        } else if (fieldClass.equals(String.class)) {
            return request.getParameter(fieldName);
        } else {
            return request.getParameterValues(fieldName);
        }

    }
}