package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

/*    public void tellJoke(View view) {

//        JokeWizard jokeWizard = new JokeWizard();
//        String joke = jokeWizard.getJoke();
//
////        Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();
//
//        Intent intent = new Intent(mContext,DisplayActivity.class);
//        intent.putExtra(DisplayActivity.INTENT_KEY_JOKE,joke);
//        startActivity(intent);

        new EndpointsAsyncTask(mContext).execute("Manfred");

    }*/


}
