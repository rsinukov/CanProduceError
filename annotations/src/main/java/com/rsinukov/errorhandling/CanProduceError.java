package com.rsinukov.errorhandling;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Annotation to indicate methods that can produce errors. ConProduceError plugin uses
 * this annotation to show an icon in the IDE gutter.
 */
@Documented
@Target(METHOD)
@Retention(SOURCE)
public @interface CanProduceError {
}
