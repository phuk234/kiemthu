package com.example.a18058511_truongtuanphuc_tk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity2 extends AppCompatActivity {
    private RecyclerView recyclerview;
    private ProductAdapter adapter;
    private ArrayList<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button refresh=findViewById(R.id.btnrefresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte=new Intent(v.getContext(),MainActivity2.class);

                v.getContext().startActivity(inte);
            }
        });

        Button btnadd=findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte=new Intent(v.getContext(),MainActivity3.class);

                v.getContext().startActivity(inte);
            }
        });

        recyclerview=findViewById(R.id.listproduct);
        products = new ArrayList<Product>();
       // products.add(new Product(1,"Cake",10000));
        //products.add(new Product(2,"Milk",70000));


        OkHttpClient client = new OkHttpClient();

        // Khởi tạo Moshi adapter để biến đổi json sang model java (ở đây là User)
        Moshi moshi = new Moshi.Builder().build();
        Type productsType = Types.newParameterizedType(List.class,Product.class);
        final JsonAdapter<List<Product>> jsonAdapter = moshi.adapter(productsType);

        // Tạo request lên server.
        Request request = new Request.Builder()
                .url("https://60b0bc521f26610017fff177.mockapi.io/Products")
                .build();

        // Thực thi request.
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                // Lấy thông tin JSON trả về. Bạn có thể log lại biến json này để xem nó như thế nào.
                String json = response.body().string();
                final List<Product> users = jsonAdapter.fromJson(json);

                // Cho hiển thị lên RecyclerView.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new ProductAdapter(users,MainActivity2.this);
                        recyclerview.setAdapter(adapter);
                        recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity2.this,LinearLayoutManager.VERTICAL,false));
                    }
                });
            }
        });




    }
}