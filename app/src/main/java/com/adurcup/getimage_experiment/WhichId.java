package com.adurcup.getimage_experiment;


import android.accounts.AuthenticatorException;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import com.squareup.picasso.Picasso;

public class WhichId extends AppCompatActivity {

    private EditText content_id;
    private Button bview;
    private ProgressBar progressBar;
    private String loginUrl, extension="";
    private ResponseItems responseItems;
    static final String TAG= "Content_id";
    private TextView text_test;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_which_id);

        content_id=(EditText) findViewById(R.id.edit_Cotentid);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

        imageView=(ImageView) findViewById(R.id.image);
        bview= (Button) findViewById(R.id.request_Button);
        bview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bview.getText()!="edit") {
                    extension = content_id.getText().toString();
                    progressBar.setVisibility(View.VISIBLE);
                    UpdateUrl();
                    Toast.makeText(getApplicationContext(), loginUrl, Toast.LENGTH_SHORT).show();
                    new AsyncHttpTask().execute(loginUrl);
                    //imageView.setVisibility(View.VISIBLE);
                }
                if(content_id.getVisibility()==View.GONE){
                    content_id.setVisibility(View.VISIBLE);
                    bview.setText("View");
                }
            }
        });
    }
    private String UpdateUrl(){
        loginUrl="http://api.adurcup.com/v2/products/";
        loginUrl=loginUrl+extension;
        return loginUrl;
    }

    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute(){
            setProgressBarIndeterminate(true);
        }
        @Override
        protected Integer doInBackground(String... params) {
            Integer result=0;
            HttpURLConnection urlConnection;

            try {
                URL url =new URL(params[0]);

                urlConnection =(HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("GET");

                int statusCode=urlConnection.getResponseCode();
                if (statusCode==200){
                    BufferedReader r= new BufferedReader((new InputStreamReader((urlConnection.getInputStream()))));
                    StringBuilder response =new StringBuilder();
                    String line;
                    while((line=r.readLine()) !=null ) {
                        response.append(line);
                    }
                    parseResult(response.toString());
                    result = 1;
                }
                else {
                    result = 0;
                }
            }
            catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }

            return result;
        }
        @Override
        protected void onPostExecute(Integer result) {
            progressBar.setVisibility(View.GONE);

            if (result==1){

                text_test=(TextView) findViewById(R.id.text);
                text_test.setText("id=" + responseItems.getId() + "\nname=" + responseItems.getName() + "\ncategory=" + responseItems.getCategory() + "\ndescription=" + responseItems.getDescription() + "\nmin_qun=" + responseItems.getMin_quantity() + "\nimage_src=" + responseItems.getImage_src());
                text_test.setVisibility(View.VISIBLE);
                bview.setVisibility(View.GONE);
                content_id.setVisibility(View.GONE);
                bview.setText("edit");
                bview.setVisibility(View.VISIBLE);
                Picasso.with(WhichId.this).load(responseItems.getImage_src())
                        .error(R.drawable.placeholder)
                        .placeholder(R.drawable.placeholder)
                        .into(imageView);

            }
            else{
                Toast.makeText(WhichId.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("product");
            JSONObject post = posts.optJSONObject(0);
            responseItems=new ResponseItems();


            responseItems.setId(post.optString("id"));
            responseItems.setName(post.optString("name"));
            responseItems.setCategory(post.optString("category"));
            responseItems.setDescription(post.optString("description"));
            responseItems.setMin_quantity(post.optString("min_quantity"));
            responseItems.setInitial_cost(post.optString("initial_cost"));
            responseItems.setPrice_per_unit(post.optString("price_per_unit"));
            responseItems.setCustomizable(post.optString("customizable"));
            responseItems.setColor(post.optString("color"));
            responseItems.setImage_src("http://www.adurcup.com/images/product/" + post.optString("image_src"));
            responseItems.setTypes(post.optString("types"));
            responseItems.setSizes(post.optString("sizes"));
            responseItems.setDelivery(post.optString("delivery"));
            responseItems.setUnit_description(post.optString("unit_description"));
            responseItems.setAdvertisement(post.optString("advertisement"));

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
