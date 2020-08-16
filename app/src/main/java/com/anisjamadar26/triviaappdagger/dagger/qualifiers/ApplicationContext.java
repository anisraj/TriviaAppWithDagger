package com.anisjamadar26.triviaappdagger.dagger.qualifiers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.SOURCE)
public @interface ApplicationContext {}
