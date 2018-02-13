package com.satyajit.nevalabassignment.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.satyajit.nevalabassignment.Models.CustomListAdapter;
import com.satyajit.nevalabassignment.Models.Person;
import com.satyajit.nevalabassignment.R;
import com.satyajit.nevalabassignment.Util.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Satyajit on 10/02/18
 */



public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ShimmerFrameLayout shimmerView;

    // given URL
    String URL = "https://test-api.nevaventures.com";
    ArrayList<Person> arrayList;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
        initialization();
        requestFetch();

    }
    private void initialization(){
        arrayList = new ArrayList<> ();
        lv =findViewById(R.id.listview);
        shimmerView=findViewById ( R.id.shimmer_view_container );

    }

    private void requestFetch(){
        // volley request obj
        JsonObjectRequest request = new JsonObjectRequest( Request.Method.GET,
                URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    JSONArray personsArray=response.getJSONArray ("data");
                    Log.d(TAG, personsArray.toString());
                    for (int i=0;i<personsArray.length ();i++)
                    {
                        try{
                        JSONObject jo = personsArray.getJSONObject(i);

                        arrayList.add(new Person (


                                jo.optString ("name","No name"),
                                jo.optString ("skills","No skills"),
                                jo.optString ("image","https://goo.gl/r3y1aq")));
                    }
                    catch (Exception e){
                        throw e;
                    }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                CustomListAdapter adapter = new CustomListAdapter(
                        getApplicationContext(), R.layout.persons_list, arrayList
                );
                shimmerView.stopShimmerAnimation();
                shimmerView.setVisibility ( View.INVISIBLE);
                lv.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        // adding req
        AppController.getInstance().addToRequestQueue(request);
    }

    @Override
    protected void onResume() {
        super.onResume ( );
        shimmerView.startShimmerAnimation ();
    }

    @Override
    protected void onStart() {
        super.onStart ( );
        shimmerView.startShimmerAnimation ();
    }

    @Override
    protected void onPause() {
        super.onPause ( );
    }
}