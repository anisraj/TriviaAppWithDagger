package com.anisjamadar26.triviaappdagger.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anisjamadar26.triviaappdagger.MyApplication;
import com.anisjamadar26.triviaappdagger.R;
import com.anisjamadar26.triviaappdagger.dagger.component.DaggerFragmentComponent;
import com.anisjamadar26.triviaappdagger.dagger.module.FragmentModule;
import com.anisjamadar26.triviaappdagger.entity.GameEntity;

import java.util.List;

import javax.inject.Inject;

/**
 * This is fragment I used to show history of previous games,
 * in layout used recyclerview,
 * and for attaching data to recyclerview used adapter.
 */


public class HistoryFragment extends Fragment {
    private RecyclerView recyclerView;

    @Inject
    public HistoryViewModel historyViewModel;


    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getDependancies();
        super.onCreate(savedInstanceState);
        historyViewModel.getAllData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.recyclerView_history);
        final HistoryDataAdapter adapter = new HistoryDataAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        historyViewModel.gameLiveData.observe(getActivity(), new Observer<List<GameEntity>>() {
            @Override
            public void onChanged(List<GameEntity> gameEntities) {
                adapter.setGameEntities(gameEntities);
            }
        });
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void getDependancies() {
        DaggerFragmentComponent
                .builder()
                .applicationComponent(((MyApplication)getContext().getApplicationContext()).applicationComponent)
                .fragmentModule(new FragmentModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onDestroyView() {
        historyViewModel.onDestroy();
        super.onDestroyView();
    }
}