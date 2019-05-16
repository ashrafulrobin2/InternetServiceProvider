package com.eomsbd.isp.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.eomsbd.isp.R;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText email,pass;
    Button button;
  ProgressBar simpleProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);
        email=findViewById(R.id.emailEt);
        pass=findViewById(R.id.passwordEt);
        button=findViewById(R.id.button2);


    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (checkValidity()){

            }else {
                String rPassword = pass.getText().toString();
                String rEmail = email.getText().toString();
                if (rEmail.equals("admin") && rPassword.equals("123")){

                    Toasty.success(LoginActivity.this,"Successfully!",Toasty.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this,DashBoard.class));
                }else{
                    reetart();
                    Toasty.error(LoginActivity.this,"Invalid Username or Password!",Toasty.LENGTH_LONG).show();
                }

            }

        }
    });
    }

    public boolean checkValidity() {
        View focusView = null;
        boolean cancel = false;
        String rPassword = pass.getText().toString();
        String rEmail = email.getText().toString();
     if (TextUtils.isEmpty(rEmail)) {
            // focusView = pass;
            cancel = true;
            email.setError("Enter a valid UserName");
        }  else if (TextUtils.isEmpty(rPassword)) {
            // focusView = pass;
            cancel = true;
            pass.setError("Enter a valid password");

        }
        return cancel;
    }
    public void reetart(){
        startActivity(new Intent(this,LoginActivity.class));
        simpleProgressBar.setVisibility(View.VISIBLE);
        finish();
    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }
}
