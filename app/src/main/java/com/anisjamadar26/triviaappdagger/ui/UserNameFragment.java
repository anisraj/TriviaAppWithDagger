package com.anisjamadar26.triviaappdagger.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.anisjamadar26.triviaappdagger.MyApplication;
import com.anisjamadar26.triviaappdagger.R;
import com.anisjamadar26.triviaappdagger.dagger.component.DaggerFragmentComponent;
import com.anisjamadar26.triviaappdagger.dagger.module.FragmentModule;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import static android.content.Context.MODE_PRIVATE;

/**
 * In this activity I am getting user name, I validated that if without entering anything user clicks
 * start quiz, then it does not stated
 *
 * then saved user name value in SharedPreferences, later saved in database
 */
public class UserNameFragment extends Fragment {

    public static final String NAME_KEY = "com.anisjamadar26.triviaapp.NAME_KEY";

    //Getting views
    private TextInputLayout tilName;
    private TextInputEditText tieName;
    private Button btnNext;

    //Getting nav controller
    private NavController navController;

    public static final String USER_NAME_KEY = "com.anisjamadar26.triviaapp.USERNAME";

    //declaring variables for shared preferences
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.anisjamadar26.triviaapp.hellosharedprefs";
    private SharedPreferences.Editor preferencesEditor;

    public UserNameFragment() {
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
        View view = inflater.inflate(R.layout.fragment_user_name, container, false);
        //Assigning views
        tilName = view.findViewById(R.id.textInputLayout_name);
        tieName = view.findViewById(R.id.textInputEdittext_name);
        btnNext = view.findViewById(R.id.button_next);

        //Assigning nav controller
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        //initializing shared pref
        mPreferences = getActivity().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        preferencesEditor = mPreferences.edit();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tieName.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getContext(), "Enter name value", Toast.LENGTH_SHORT).show();
                }
                else {
                    goToFirstQuestion(name);
                }
            }
        });

        return view;
    }

    private void goToFirstQuestion(String name) {
        preferencesEditor.putString(USER_NAME_KEY, name);
        preferencesEditor.apply();
        navController.navigate(R.id.action_getUserNameFragment_to_bestCricketerFragment);
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