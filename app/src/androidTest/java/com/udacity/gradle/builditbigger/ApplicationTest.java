package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends AndroidTestCase {

    public void testJokeTask() {

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();

        String joke = null;
        try {
            joke = endpointsAsyncTask.execute("Archit").get(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(joke);


//        String res = EndpointsAsyncTask.getJokeFromCloudSync("Archit");
//        Assert.assertEquals(res, "Hi, Archit");


    }

}