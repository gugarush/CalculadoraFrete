package com.gugarush.android.shippingbr;
import android.app.Application;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.gugarush.android.shippingbr.util.Constants;



public class GlobalApp extends Application {

    public static Settings mSettings;
    public static RequestQueue mRequestQueue;
    
    private static final int DEFAULT_GVP_TIMEOUT_MS = 30000;
    
    
    @Override
    public void onCreate() {
        Log.d("gofc", "onCreate GlobalApp");
        mRequestQueue = Volley.newRequestQueue(this);
        mSettings = new Settings(this);
    }
    
    private static DefaultRetryPolicy getPVRDefaultRetryPolicy() {
        return new DefaultRetryPolicy(
                // 30 seconds now. Was 2,5 seconds and i got too much timeouts
                DEFAULT_GVP_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    public static <T> void addToRequestQueue(final Request<T> req) {
        // set the default tag if tag is null
        if (req.getTag() == null) {
            req.setTag(Constants.DEFAULT_REQUEST_TAG);
        }

        // Timeout and Retry
        req.setRetryPolicy(
                getPVRDefaultRetryPolicy());

        mRequestQueue.add(req);
    }

    public static void cancelPendingRequests(final String tag) {
        if (mRequestQueue != null) {
            final String requestTag = tag != null ? tag
                    : Constants.DEFAULT_REQUEST_TAG;
            mRequestQueue.cancelAll(requestTag);
        }
    }



}
