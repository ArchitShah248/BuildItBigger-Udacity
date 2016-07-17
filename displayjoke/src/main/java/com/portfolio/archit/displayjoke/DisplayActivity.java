package com.portfolio.archit.displayjoke;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {


    public final static String INTENT_KEY_JOKE = "intent_key_joke";

    private Context mContext;
    private TextView tvJoke;
    private String jokeStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        tvJoke = (TextView) findViewById(R.id.tvJoke);

        Intent intent = getIntent();
        if(intent.hasExtra(INTENT_KEY_JOKE)){
            jokeStr = intent.getStringExtra(INTENT_KEY_JOKE);
        }

        tvJoke.setText(jokeStr);

    }
}
