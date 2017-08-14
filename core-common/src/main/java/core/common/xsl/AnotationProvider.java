package core.common.xsl;

import java.lang.annotation.Annotation;

public class AnotationProvider {
	public static <T extends Annotation> T getClassAnotation(Class<?> objClass, Class<T> annotationClass) {
		if (objClass.isAnnotationPresent(annotationClass)) {
			return objClass.getAnnotation(annotationClass);
		}
		
		return null;
	}
}
