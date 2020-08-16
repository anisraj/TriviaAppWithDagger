package com.anisjamadar26.triviaappdagger.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.anisjamadar26.triviaappdagger.MyApplication;
import com.anisjamadar26.triviaappdagger.R;
import com.anisjamadar26.triviaappdagger.dagger.component.DaggerFragmentComponent;
import com.anisjamadar26.triviaappdagger.dagger.module.FragmentModule;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class FlagQuesFragment extends Fragment {

    /**
     * This screen provides questions of flag colors,
     * used checkboxes, so user can select multiple answers
     * After selecting choices, added it to string, added in SharedPreferences and
     * later used for storing in database
     */
    //getting view
    private CheckBox cbWhite;
    private CheckBox cbYellow;
    private CheckBox cbOrange;
    private CheckBox cbGreen;
    private Button btnSelectAll;
    private Button btnNext;

    private ArrayList<String> selectedFlagColors = new ArrayList<>();

    //declaring variables for shared preferences
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.anisjamadar26.triviaapp.hellosharedprefs";
    private SharedPreferences.Editor preferencesEditor;
    public static final String FLAG_COLOR_KEY = "com.anisjamadar26.triviaapp.FLAG_COLORS";

    private StringBuilder stringBuilder = null;

    //Getting nav controller
    private NavController navController;

    public FlagQuesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getDependancies();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_flag_ques, container, false);

        //getting views
        btnNext = view.findViewById(R.id.fqbutton_next);
        btnSelectAll = view.findViewById(R.id.button_selectAll);
        cbWhite = view.findViewById(R.id.checkBox_first);
        cbYellow = view.findViewById(R.id.checkBox_second);
        cbOrange = view.findViewById(R.id.checkBox_thirsd);
        cbGreen = view.findViewById(R.id.checkBox_fourth);

        //initializing shared pref
        mPreferences = getActivity().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        preferencesEditor = mPreferences.edit();

        stringBuilder = new StringBuilder();

        //Assigning nav controller
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        btnSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbWhite.setChecked(true);
                cbGreen.setChecked(true);
                cbOrange.setChecked(true);
                cbYellow.setChecked(true);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFlagColors();
                if (stringBuilder.length() == 0) {
                    Toast.makeText(getContext(), "Select at least one option", Toast.LENGTH_SHORT).show();
                }
                else {
                    goToNextPage();
                }
            }
        });


        return view;
    }

    private void checkFlagColors() {
        if (cbWhite.isChecked()) {
            selectedFlagColors.add("White");
        }
        if (cbYellow.isChecked()) {
            selectedFlagColors.add("Yellow");
        }
        if (cbOrange.isChecked()) {
            selectedFlagColors.add("Orange");
        }
        if (cbGreen.isChecked()) {
            selectedFlagColors.add("Green");
        }

        /**
         * This is where I add logic for adding comma(,) if more answers selected
         */
        for (int i = 0; i < selectedFlagColors.size(); i++) {

            stringBuilder.append(selectedFlagColors.get(i));

            if (!(i == selectedFlagColors.size() - 1)) {

                stringBuilder.append(",");
            }

        }
    }

    private void goToNextPage() {
        preferencesEditor.putString(FLAG_COLOR_KEY, stringBuilder.toString());
        preferencesEditor.apply();
        navController.navigate(R.id.action_flagQuesFragment_to_summaryFragment);
    }

    private void getDependancies() {
        DaggerFragmentComponent
                .builder()
                .applicationComponent(((MyApplication)getContext().getApplicationContext()).applicationComponent)
                .fragmentModule(new FragmentModule(this))
                .build()
                .inject(this);
    }
}