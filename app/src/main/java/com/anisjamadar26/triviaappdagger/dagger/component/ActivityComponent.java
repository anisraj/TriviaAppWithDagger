package com.anisjamadar26.triviaappdagger.dagger.component;

import com.anisjamadar26.triviaappdagger.MainActivity;
import com.anisjamadar26.triviaappdagger.TriviaActivity;
import com.anisjamadar26.triviaappdagger.dagger.ActivityScope;
import com.anisjamadar26.triviaappdagger.dagger.module.ActivityModule;

import dagger.Component;

@ActivityScope
@Component(dependencies = {ApplicationComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(TriviaActivity triviaActivity);

    void inject(MainActivity mainActivity);
}
