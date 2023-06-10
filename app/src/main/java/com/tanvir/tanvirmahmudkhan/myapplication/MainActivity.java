package com.tanvir.tanvirmahmudkhan.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tanvir.tanvirmahmudkhan.model.Flower;
import com.tanvir.tanvirmahmudkhan.parser.FlowerJSONParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //TextView tv1;
    List<Flower> flowers;
    ListView listView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        progressBar = findViewById(R.id.progressBar);
        //flowers = new ArrayList<>();

        String url = "http://services.hanselandpetal.com/feeds/flowers.json";

        if(isOnline()){
            MyTask myTask = new MyTask();
            myTask.execute(url);
        }
        else {
            Toast.makeText(MainActivity.this,"No internet connection...",Toast.LENGTH_LONG).show();
        }
    }

    private boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    private class MyTask extends AsyncTask<String,String,List<Flower>>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<Flower> flowers) {

            progressBar.setVisibility(View.INVISIBLE);

            if(flowers != null){
                FlowerAdapter flowerAdapter = new FlowerAdapter(MainActivity.this,android.R.layout.simple_list_item_1,flowers);
                listView.setAdapter(flowerAdapter);
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Flower> doInBackground(String... ulrs) {

            HttpManager httpManager = new HttpManager();

            String jsonContent = httpManager.getData(ulrs[0]);
            flowers= FlowerJSONParser.parseFeed(jsonContent);


            String imageURL = "https://images.unsplash.com/photo-1526047932273-341f2a7631f9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=80";
            try {
                InputStream inputStream = (InputStream) new URL(imageURL).getContent();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                for(int i=0; i<flowers.size(); i++){
                    flowers.get(i).setBitmap(bitmap);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return flowers;
        }
    }
}
