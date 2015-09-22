package de.zalando.baigan.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation to mark a class as Baigan configuration. All the public
 * methods in this class will be treated as config methods, and the
 * configuration key will be derived according to convention.
 * <classname>.<method>. Camel cased words will be separated with a .(dot).
 * Example ExpressDelivery.featureEnabled translates to key
 * express.delivery.feature.enabled
 * 
 * @author mchand
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BaiganConfig {
}
