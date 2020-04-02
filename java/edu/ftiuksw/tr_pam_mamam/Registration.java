package edu.ftiuksw.tr_pam_mamam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    EditText mFullname,mEmail,mPassword,mPhone;
    Button mRegisterbtn;
    TextView mLoginbtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        mFullname = findViewById(R.id.Full_Name_Insert_reg);
        mEmail = findViewById(R.id.Email_Insert_reg);
        mPassword = findViewById(R.id.Passowrd_Insert_reg);
        mRegisterbtn = findViewById(R.id.Btn_Register);
        mLoginbtn = findViewById(R.id.ready_login);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar_Reg);

        if (fAuth.getCurrentUser() != null) {
            Toast.makeText(Registration.this, "You Already Login!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            progressBar.setVisibility(View.VISIBLE);
            finish();
        }
        mRegisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String fullname = mFullname.getText().toString().trim();

                if (TextUtils.isEmpty(fullname)){
                    mFullname.setError("Fullname Required");
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email Required");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    mPassword.setError("Password Required");
                    return;
                }

                if (password.length()<6){
                    mPassword.setError("Password must be More 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            Toast.makeText(Registration.this, "User Created!",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), Login.class));
                            progressBar.setVisibility(View.INVISIBLE);
                        } else{
                            Toast.makeText(Registration.this,"Error! "+task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });

        mLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
    @Override
    public void onBackPressed() {

    }
}
