package com.spring.heroku.env;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EnvironmentService {

    private static Map<String,String> environmentMap = null;

    public static Map<String,String> getEnvironmentMap() throws Exception {
        if( environmentMap == null ) {
            environmentMap = new HashMap<String, String>();
            if( System.getenv().containsKey("DYNO") ) {
                // we are in heroku environment
                return System.getenv();
            } else if( System.getenv().containsKey("ENV_FILE") ) {
                // we are in heroku local
                return System.getenv();
            } else {
                File f = new File(".env");
                if(f.exists() && !f.isDirectory()) {
                    Properties properties = new Properties();
                    properties.load( new FileInputStream( f ) );
                    for(Object key : properties.keySet() ) {
                        environmentMap.put( (String) key, (String) properties.get(key) );
                    }
                }
            }
        }
        return environmentMap;
    }

    public static void main(String args[]) throws Exception {
        System.out.println(" getEnvironmentMap " + getEnvironmentMap() );
    }


}
