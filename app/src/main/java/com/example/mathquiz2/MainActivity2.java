package com.example.mathquiz2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    EditText emailId, password;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mFirebaseAuth=FirebaseAuth.getInstance();
        emailId=findViewById(R.id.emailId);
        password=findViewById(R.id.password);
        btnSignUp=findViewById(R.id.btnSignUp);
        tvSignIn=findViewById(R.id.tvSignIn);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailId.getText().toString();
                String pwd=password.getText().toString();
                if (email.isEmpty()){
                    emailId.setError("Please enter your email");
                    emailId.requestFocus();
                }
               else if (pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
               else if (email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(MainActivity2.this,"Fields are emptyy",Toast.LENGTH_SHORT).show();
                }
               else if (!(email.isEmpty() && pwd.isEmpty())){
                   mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity2.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (!task.isSuccessful()){
                               Toast.makeText(MainActivity2.this,"Sign Up UNSUCCESSFULY,Try again",Toast.LENGTH_SHORT).show();
                           }
                           else{
                               startActivity(new Intent(MainActivity2.this,HomeActivity.class));
                           }

                       }
                   });
                }
               else{
                    Toast.makeText(MainActivity2.this,"Error Ocurred",Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity2.this,LoginActivity.class);
                startActivity(in);
            }
        });

    }
}