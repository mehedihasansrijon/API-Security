package com.example.apiandsecurity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;
    Button button;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        requestQueue = Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                stringRequest();
                /* objectRequest();
                 arrayRequest(); */
            }
        });
    }

    private void stringRequest() {
        String stringRequestURL = getString(R.string.base_url) + "/stringRequest.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, stringRequestURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textView.setText(response);
                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.toString());
                progressBar.setVisibility(View.GONE);
            }
        }) {
            @NonNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> maps = new HashMap<String, String>();
                maps.put("name", "John");
                maps.put("pass", "552233");
                return maps;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void objectRequest() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pass", "578456");
            jsonObject.put("name", "Elon");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        String objectRequestURL = getString(R.string.base_url) + "/objectRequest.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, objectRequestURL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String msg;
                try {
                    String status = response.getString("status");
                    String name = response.getString("data");
                    msg = "Status : " + status + "\nName : " + name;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                textView.setText(msg);
                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.toString());
                progressBar.setVisibility(View.GONE);
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void arrayRequest() {
        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pass", "1144777");
            jsonObject.put("name", "Thomas");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        jsonArray.put(jsonObject);

        String arrayRequestURL = getString(R.string.base_url) + "/arrayRequest.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, arrayRequestURL, jsonArray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String msg;
                try {
                    JSONObject jsonArray1 = response.getJSONObject(0);
                    String status = jsonArray1.getString("status");
                    String data = jsonArray1.getString("data");
                    msg = "Status : " + status + "\nData : " + data;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                textView.setText(msg);
                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.toString());
                progressBar.setVisibility(View.GONE);
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}