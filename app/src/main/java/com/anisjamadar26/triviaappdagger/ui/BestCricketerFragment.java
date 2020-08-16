package com.anisjamadar26.triviaappdagger.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.anisjamadar26.triviaappdagger.MyApplication;
import com.anisjamadar26.triviaappdagger.R;
import com.anisjamadar26.triviaappdagger.dagger.component.DaggerFragmentComponent;
import com.anisjamadar26.triviaappdagger.dagger.module.FragmentModule;

import static android.content.Context.MODE_PRIVATE;

/**
 * This screen contains best cricketer question, used RadioButtons so
 * user can select only one option from radio group
 *
 * Saved selected value in SharedPreferences and later used to store in database
 */
public class BestCricketerFragment extends Fragment {

    //getting views
    private RadioGroup radioGroup;
    private Button btnNext;
    private TextView tvError;

    //declaring variables for shared preferences

    //key for storing best cricketer name
    public static final String BEST_CRICK_KEY = "com.anisjamadar26.triviaapp.BEST_CRICKETER";
    private SharedPreferences mPreferences;

    //shared pref file name
    private String sharedPrefFile =
            "com.anisjamadar26.triviaapp.hellosharedprefs";
    private SharedPreferences.Editor preferencesEditor;

    private String radioButtonValue = null;

    //Getting nav controller
    private NavController navController;

    public BestCricketerFragment() {
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
        View view = inflater.inflate(R.layout.fragment_best_cricketer, container, false);
        //initializing views
        radioGroup = view.findViewById(R.id.radiogroup);
        btnNext = view.findViewById(R.id.button_next2);
        tvError = view.findViewById(R.id.textView_error);

        //initializing shared pref
        mPreferences = getActivity().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        preferencesEditor = mPreferences.edit();

        //Assigning nav controller
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton_first:
                        radioButtonValue = getResources().getString(R.string.sachin_tendulkar);
                        break;
                    case R.id.radioButton_second:
                        radioButtonValue = getResources().getString(R.string.virat_kohli);
                        break;
                    case R.id.radioButton_thirsd:
                        radioButtonValue = getResources().getString(R.string.adam_gilchirst);
                        break;
                    case R.id.radioButton_fourth:
                        radioButtonValue = getResources().getString(R.string.jacques_kallis);
                        break;
                    default:
                        // Do nothing.
                        break;
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButtonValue != null) {
                    goToNextPage();
                }
                else {
                    tvError.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "Please Select Answer", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private void goToNextPage() {
        preferencesEditor.putString(BEST_CRICK_KEY, radioButtonValue);
        preferencesEditor.apply();
        navController.navigate(R.id.action_bestCricketerFragment_to_flagQuesFragment);
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