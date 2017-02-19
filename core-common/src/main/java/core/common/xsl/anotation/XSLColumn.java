package core.common.xsl.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import core.common.convert.Converter;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface XSLColumn {
	int index() default 0;
	Class<? extends Converter<?>> converter();
}
