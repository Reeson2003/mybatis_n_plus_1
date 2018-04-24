package com.test.beans;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Pavel Gavrilov
 */
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface SOUT {
}
