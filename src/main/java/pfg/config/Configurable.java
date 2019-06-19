package pfg.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation denoting fields whose values should be loaded from the config
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Configurable {
    /**
     * Config key to use. If left empty, the library will use the field name
     * @return
     */
    String value() default "";
}
