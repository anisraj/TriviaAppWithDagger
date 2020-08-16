package com.anisjamadar26.triviaappdagger.dagger.component;

import com.anisjamadar26.triviaappdagger.dagger.FragmentScope;
import com.anisjamadar26.triviaappdagger.dagger.module.FragmentModule;
import com.anisjamadar26.triviaappdagger.ui.BestCricketerFragment;
import com.anisjamadar26.triviaappdagger.ui.FlagQuesFragment;
import com.anisjamadar26.triviaappdagger.ui.UserNameFragment;
import com.anisjamadar26.triviaappdagger.ui.history.HistoryFragment;
import com.anisjamadar26.triviaappdagger.ui.summary.SummaryFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = {ApplicationComponent.class}, modules = {FragmentModule.class})
public interface FragmentComponent {

    void inject(HistoryFragment historyFragment);

    void inject(SummaryFragment summaryFragment);

    void inject(BestCricketerFragment bestCricketerFragment);

    void inject(FlagQuesFragment flagQuesFragment);

    void inject(UserNameFragment userNameFragment);
}
