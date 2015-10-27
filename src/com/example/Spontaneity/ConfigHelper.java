package com.example.Spontaneity;

import com.parse.ConfigCallback;
import com.parse.ParseConfig;
import com.parse.ParseException;


/**
 * Created by seanbroomfield on 10/19/15.
 */
public class ConfigHelper {
    private ParseConfig config;
    private long configLastFetchedTime;

    public void fetchConfigIfNeeded() {
        final long configRefreshInterval = 60 * 60; //1 hour

        if(config == null || System.currentTimeMillis() - configLastFetchedTime  > configRefreshInterval) {
            config = ParseConfig.getCurrentConfig();
            ParseConfig.getInBackground(new ConfigCallback() {
                @Override
                public void done(ParseConfig parseConfig, ParseException e) {
                    if(e == null) {
                        //successfully retrieved
                        config = parseConfig;
                        configLastFetchedTime = System.currentTimeMillis();
                    } else {
                        //Failed
                        configLastFetchedTime = 0;
                    }
                }
            });
        }
    }
}
