package de.messetat.sling.core.components.injectors.annotations;

import org.apache.sling.models.annotations.Source;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.spi.injectorspecific.InjectAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@InjectAnnotation
@Source("parent-page")
public @interface MyAnnotation {
    String resourceType() default "";

    int depth() default 0;

    String name() default "";

    InjectionStrategy injectionStrategy() default InjectionStrategy.DEFAULT;
}
