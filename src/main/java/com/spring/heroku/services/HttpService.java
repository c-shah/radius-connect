package com.spring.heroku.services;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpService {

    public static String postRequest(String url, Map<String, String> data) throws Exception {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        if( data.size() > 0 ) {
            List<NameValuePair> params = new ArrayList<NameValuePair>(data.size());
            for(String key : data.keySet() ) {
                params.add(new BasicNameValuePair(key, data.get(key) ));
            }
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        }
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        String theString = null;
        if (entity != null) {
            InputStream inputStream = entity.getContent();
            try {
                theString = IOUtils.toString(inputStream, "UTF-8");
            } finally {
                inputStream.close();
            }
        }
        return theString;
    }

}
