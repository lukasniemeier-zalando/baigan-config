package de.zalando.baigan.proxy;

import com.google.common.base.CaseFormat;
import com.google.common.base.Strings;

/**
 * The class to contain utility methods used in proxying configuration beans.
 * 
 * @author mchand
 *
 */
public class ProxyUtils {
    private static final String NAMESPACE_SEPARATOR = ".";

    public static String dottify(final String text) {

        if (Strings.isNullOrEmpty(text)) {
            return NAMESPACE_SEPARATOR;
        }

        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, text)
                .replace("_", NAMESPACE_SEPARATOR);

    }
}
