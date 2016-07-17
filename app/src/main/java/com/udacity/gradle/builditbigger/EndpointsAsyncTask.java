package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.example.archit.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by Archit Shah on 7/14/2016.
 */
public class EndpointsAsyncTask extends AsyncTask<String, Void, String> {

    private static MyApi myApiService = null;

    public interface EndPointCallback{
        void onReceiveCloudRes(String res);
    }

    private EndPointCallback  endPointCallback;

    public EndPointCallback getEndPointCallback() {
        return endPointCallback;
    }

    public void setEndPointCallback(EndPointCallback endPointCallback) {
        this.endPointCallback = endPointCallback;
    }

    public EndpointsAsyncTask() {
    }

    @Override
    protected String doInBackground(String... params) {
        String name = params[0];
        return getJokeFromCloudSync(name);
    }

    @Override
    protected void onPostExecute(String result) {
        if(endPointCallback != null){
            endPointCallback.onReceiveCloudRes(result);
        }
    }

    public static String getJokeFromCloudSync(String name){
        String res = null;

        if(myApiService == null) {

            /*// Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver*/

            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://psychic-karma-137216.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        try {
            res =  myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }

        return res;
    }

}
