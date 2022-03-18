package com.my.basic.model

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Retention(value= RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD, ElementType.FIELD)
annotation class TestAnnotation()
