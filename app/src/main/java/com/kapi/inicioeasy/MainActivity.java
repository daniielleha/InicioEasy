package com.kapi.inicioeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ParseUser parseUser =ParseUser.getCurrentUser();
    private static final String TAG = MainActivity.class.getSimpleName();
    private MaterialButton tst;
    private EditText correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correo = (EditText) findViewById(R.id.etEmail);
        tst = (MaterialButton) findViewById(R.id.btnIniciar);
        tst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser currentUser = ParseUser.getCurrentUser();
                 if (correo != null) {/*(currentUser != null){
                    String userId = currentUser.getObjectId();
                    String username = currentUser.getUsername();
                    String objectID = currentUser.getObjectId();
                } else {
                    Toast.makeText(getApplicationContext(), "the user does not exist!",Toast.LENGTH_SHORT).show();
                }*/
                HashMap<String, Object> params = new HashMap<String, Object>();
                /*try {
                    params.put("correo", correo.getText().toString());
                    //Log.d(TAG, "onClick: "+ String.valueOf(parseUser));
                } catch (Exception e){
                    e.printStackTrace();
                }*/
                     params.put("email", correo);
                     ParseCloud.callFunctionInBackground("getUserNameByEmail", params, new FunctionCallback() {

                    @Override
                    public void done(Object object, ParseException e) {
                        Log.d(TAG, String.valueOf(object));
                    }

                    @Override
                    public void done(Object o, Throwable throwable) {
                        Log.d("---->testKapital+", String.valueOf(o));
                        HashMap result = (HashMap) o;
                        int code = Integer.parseInt(String.valueOf(result.get("code")));
                        if (code == 200) {
                            HashMap response = (HashMap) result.get("response");
                            Log.d(TAG, "done: " + response.toString());
                            /*for (int i = 0; i < response.size(); i++) {
                                HashMap responsetmp = (HashMap) response.get(i);
                                Log.d(TAG, "done: " + response.get(i));
                                if (responsetmp.get("idPlan").equals("rw5aWxNrWw")) {
                                    planes = (ArrayList) responsetmp.get("plans");
                                }
                            }*/
                        } else if (code == 400) {
                            Toast.makeText(getApplicationContext(), (CharSequence) result.get("msg"), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}