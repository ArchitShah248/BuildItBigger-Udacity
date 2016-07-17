package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;
import com.portfolio.archit.displayjoke.DisplayActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private Context mContext;
    private SimpleArcDialog mDialog;

    public MainActivityFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();

        mDialog = new SimpleArcDialog(mContext);
        mDialog.setConfiguration(new ArcConfiguration(mContext));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button btnTellJoke = (Button) root.findViewById(R.id.btnTellJoke);
        btnTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getJoke();
            }
        });
        return root;
    }

    private void navigateToJokeActivity(String joke) {
        Intent intent = new Intent(getContext(), DisplayActivity.class);
        intent.putExtra(DisplayActivity.INTENT_KEY_JOKE, joke);
        startActivity(intent);
    }

    private void getJoke() {

        if (Utils.isInternetAvailable(mContext)) {
            mDialog.show();
            EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
            endpointsAsyncTask.setEndPointCallback(new EndpointsAsyncTask.EndPointCallback() {
                @Override
                public void onReceiveCloudRes(String res) {
                    mDialog.cancel();
                    navigateToJokeActivity(res);
                }
            });
            endpointsAsyncTask.execute("Archit");
        } else {
            Toast.makeText(mContext, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
    }
}
