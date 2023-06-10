package com.tanvir.tanvirmahmudkhan.myapplication;

import android.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpManager {

    public static String getData(String urls){
        try {
            URL url = new URL(urls);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            StringBuilder sb = new StringBuilder();
            BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;

            while ((line=bf.readLine()) != null){
                sb.append(line);
            }

            return sb.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getData(String urls,String userName, String password){
        try {
            byte[] loginBytes = (userName + ":" + password).getBytes();
            StringBuilder loginBuilder = new StringBuilder().append("Basic ").append(Base64.encode(loginBytes,Base64.DEFAULT));

            URL url = new URL(urls);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            con.addRequestProperty("Authorization",loginBuilder.toString());

            con.connect();

            StringBuilder sb = new StringBuilder();
            BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;

            while ((line=bf.readLine()) != null){
                sb.append(line);
            }

            return sb.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
