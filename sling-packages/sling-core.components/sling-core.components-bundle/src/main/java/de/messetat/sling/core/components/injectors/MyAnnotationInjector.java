package de.messetat.sling.core.components.injectors;

import de.messetat.sling.core.components.injectors.annotations.MyAnnotation;
import org.apache.sling.api.adapter.Adaptable;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.spi.AcceptsNullName;
import org.apache.sling.models.spi.DisposalCallbackRegistry;
import org.apache.sling.models.spi.Injector;
import org.apache.sling.models.spi.injectorspecific.AbstractInjectAnnotationProcessor2;
import org.apache.sling.models.spi.injectorspecific.InjectAnnotationProcessor2;
import org.apache.sling.models.spi.injectorspecific.StaticInjectAnnotationProcessorFactory;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;


@Component(property = Constants.SERVICE_RANKING + ":Integer=1000",
        service = {Injector.class, StaticInjectAnnotationProcessorFactory.class,
                AcceptsNullName.class})
public class MyAnnotationInjector implements Injector, AcceptsNullName,
        StaticInjectAnnotationProcessorFactory {

    @Override
    public String getName() {
        return "parent-page";
    }

    @Override
    public Object getValue(Object adaptableObj, String name, Type declaredType,
                           AnnotatedElement element, DisposalCallbackRegistry callbackRegistry) {

        MyAnnotation annotation = element.getAnnotation(MyAnnotation.class);

        if (annotation == null) {
            return null;
        }

        if (adaptableObj instanceof Adaptable) {

            Adaptable adaptable = (Adaptable) adaptableObj;

            Resource resource;
            if (adaptable instanceof Resource) {
                resource = (Resource) adaptable;
            } else {
                resource = adaptable.adaptTo(Resource.class);
            }

            if (resource != null) {
                ResourceResolver resourceResolver = resource.getResourceResolver();
                return new Object();
            }

        }

        return null;
    }

    @Override
    public InjectAnnotationProcessor2 createAnnotationProcessor(AnnotatedElement annotatedElement) {
        MyAnnotation annotation = annotatedElement.getAnnotation(MyAnnotation.class);
        if (annotation != null) {
            return new ParentPageAnnotationProcessor(annotation);
        }
        return null;
    }

    private static class ParentPageAnnotationProcessor extends AbstractInjectAnnotationProcessor2 {
        private final MyAnnotation annotation;

        public ParentPageAnnotationProcessor(MyAnnotation annotation) {
            this.annotation = annotation;
        }

        public String getName() {
            if (annotation.name().isEmpty()) {
                return null;
            }
            return annotation.name();
        }
    }
}
