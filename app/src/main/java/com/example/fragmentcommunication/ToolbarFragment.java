package com.example.fragmentcommunication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class ToolbarFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {


    private static int seekvalue = 10;

    private static EditText editText;

    ToolbarListener activityCallback;

    public interface ToolbarListener{

        void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState);

        public void onButtonClick(int fontSize, String text);

    }

    public ToolbarFragment() {
        // Required empty public constructor
    }


    /*public static ToolbarFragment newInstance(String param1, String param2) {
        ToolbarFragment fragment = new ToolbarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_toolbar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.editText1);

        final SeekBar seekbar = view.findViewById(R.id.seekBar1);

        seekbar.setOnSeekBarChangeListener(this);

        final Button button = view.findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                buttonClicked(view);

            }

        });

    }

    private void buttonClicked(View view) {

        activityCallback.onButtonClick(seekvalue, editText.getText().toString());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        seekvalue = progress;

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {

            activityCallback = (ToolbarListener)context;

        } catch (ClassCastException e){

            throw new ClassCastException(context.toString() + " must implement ToolbarListener");

        }
    }
}