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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;
import com.portfolio.archit.displayjoke.DisplayActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private Context mContext;
    private InterstitialAd mInterstitialAd;
    private String jokeStr = "";
    private SimpleArcDialog mDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                navigateToJokeActivity(jokeStr);
            }
        });
        mDialog = new SimpleArcDialog(mContext);
        mDialog.setConfiguration(new ArcConfiguration(mContext));

        requestNewInterstitial();

    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    private void navigateToJokeActivity(String joke) {
        Intent intent = new Intent(getContext(), DisplayActivity.class);
        intent.putExtra(DisplayActivity.INTENT_KEY_JOKE, joke);
        startActivity(intent);
    }

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        Button btnTellJoke = (Button) root.findViewById(R.id.btnTellJoke);
        btnTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getJoke();
            }
        });

        return root;
    }


    private void getJoke() {
        if (Utils.isInternetAvailable(mContext)) {
            mDialog.show();
            EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
            endpointsAsyncTask.setEndPointCallback(new EndpointsAsyncTask.EndPointCallback() {
                @Override
                public void onReceiveCloudRes(String res) {
                    jokeStr = res;
                    mDialog.cancel();
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        navigateToJokeActivity(res);
                    }
                }
            });
            endpointsAsyncTask.execute("Archit");
        } else {
            Toast.makeText(mContext, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
    }
}
