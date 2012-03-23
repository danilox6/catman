package it.unisannio.memento;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface Delegate {
	Class<?> to();
	String method() default "";
}
