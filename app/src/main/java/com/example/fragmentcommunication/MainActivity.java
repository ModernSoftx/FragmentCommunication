package com.example.fragmentcommunication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements ToolbarFragment.ToolbarListener{

    private static TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){

        textView = view.findViewById(R.id.textView2);

    }


    @Override
    public void onButtonClick(int fontSize, String text) {
        TextFragment textFragment = (TextFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);

        textFragment.changeTextProperties(fontSize, text);

    }


}