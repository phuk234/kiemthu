package com.example.a18058511_truongtuanphuc_tk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        TextView updatename;
        TextView updateprice;
         updatename=findViewById(R.id.txtupdatename);
         updateprice=findViewById(R.id.txtupdateprice);
        Product x= (Product) getIntent().getSerializableExtra("product");

        updatename.setText(x.getName());
        updateprice.setText(x.getPrice()+"");
        OkHttpClient client = new OkHttpClient();
        Button btnadd=findViewById(R.id.btnupdateproduct);


        // Product product=new Product(50,name.getText().toString(),Double.parseDouble(price.getText().toString()));


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(updatename.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter your name product!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(updateprice.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter your price product!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Thread a= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        RequestBody formBody = new FormBody.Builder()
                                .addEncoded("name", updatename.getText().toString())
                                .addEncoded("price", updateprice.getText().toString())
                                .build();
                        //   RequestBody requestBody = new MultipartBody.Builder()
                        //         .setType(MultipartBody.FORM)
//
                        //                              .addFormDataPart("name", "'fruit'")
                        //                            .addFormDataPart("price", "'100000'")
                        //                          .build();
                        Request request = new Request.Builder()
                                .url("https://60b0bc521f26610017fff177.mockapi.io/Products/"+x.getId())
                                .put(formBody)
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