package com.example.a18058511_truongtuanphuc_tk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private Button btnlogin;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        inputEmail=findViewById(R.id.txtemail);
        inputPassword=findViewById(R.id.txtpassword);
        btnlogin=findViewById(R.id.btnlogin);





        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte=new Intent(v.getContext(),MainActivity2.class);
                //if(getIntent().getSerializableExtra("plants")==null)
                  //  inte.putExtra("plants",new ArrayList<Plant>());
                //else
                  //  inte.putExtra("plants",getIntent().getSerializableExtra("plants"));
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter your email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter your password!", Toast.LENGTH_SHORT).show();
                    return;
                }



               // progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Login success!",
                                            Toast.LENGTH_SHORT).show();

                                    Intent inte=new Intent(v.getContext(),MainActivity2.class);
                                 //   if(getIntent().getSerializableExtra("plants")==null)
                                   //     inte.putExtra("plants",new ArrayList<Plant>());
                                   // else
                                     //   inte.putExtra("plants",getIntent().getSerializableExtra("plants"));
                                    v.getContext().startActivity(inte);

                                }



                                if (!task.isSuccessful()) {
                                   Toast.makeText(MainActivity.this, "Email or password invalid!",
                                          Toast.LENGTH_SHORT).show();
                                }
                            }
                        });




            }
        });

    }
}