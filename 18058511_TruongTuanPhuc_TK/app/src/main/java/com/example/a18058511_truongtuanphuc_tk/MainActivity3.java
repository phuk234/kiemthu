package com.example.a18058511_truongtuanphuc_tk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.moshi.Json;

import java.io.IOException;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
       // MediaType JSON
           //     = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        Button btnadd;
        EditText name;
        EditText price;

       name=findViewById(R.id.txtaddname);
        price=findViewById(R.id.txtaddprice);

       // Product product=new Product(50,name.getText().toString(),Double.parseDouble(price.getText().toString()));

        btnadd=findViewById(R.id.btnaddnew);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter your name product!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(price.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter your price product!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Thread a= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        RequestBody formBody = new FormBody.Builder()
                                .addEncoded("name", name.getText().toString())
                                .addEncoded("price", price.getText().toString())
                                .build();
                     //   RequestBody requestBody = new MultipartBody.Builder()
                       //         .setType(MultipartBody.FORM)
//
  //                              .addFormDataPart("name", "'fruit'")
    //                            .addFormDataPart("price", "'100000'")
      //                          .build();
                        Request request = new Request.Builder()
                                .url("https://60b0bc521f26610017fff177.mockapi.io/Products")
                                .post(formBody)
                                .build();
                        try {
                            client.newCall(request).execute();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                a.start();


                Intent inte=new Intent(v.getContext(),MainActivity2.class);

                v.getContext().startActivity(inte);
                        }



        });

    }
}