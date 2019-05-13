package com.eomsbd.isp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eomsbd.isp.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private ProgressDialog progressDialog;
    TextView user1, first, last, email, phone, address, national,staticipcpe,expiration,profileName;
    ApiInterface apiInterface;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user1 = findViewById(R.id.userName);
        first = findViewById(R.id.firstName);
        last = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.mobile);
        address = findViewById(R.id.address);
        national = findViewById(R.id.nationalId);
        staticipcpe=findViewById(R.id.staticipcpe);
        expiration=findViewById(R.id.expiration);
        profileName=findViewById(R.id.profileName);
        editText = findViewById(R.id.text);
        button = findViewById(R.id.btn);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int value = Integer.parseInt(editText.getText().toString());
                if (value!=0){
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Loading..");
                    progressDialog.show();
                    generateDataList(value);
                }else {
                    Toast.makeText(MainActivity.this,
                            "Null Value"
                            , Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    public void generateDataList(int id) {
        apiInterface = Retrofit.getRetrofitInstance().create(ApiInterface.class);

            Call<User> userCall = apiInterface.getProfile(id);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    User user = response.body();

                    progressDialog.dismiss();

                    if (response.isSuccessful() && user != null) {
                        email.setText(user.getEmail());
                        address.setText(user.getAddress());
                        phone.setText(user.getMobile());
                        last.setText(user.getLastname());
                        first.setText(user.getFirstname());
                        user1.setText(user.getUsername());
                        national.setText(user.getNationalid());
                        staticipcpe.setText(user.getStaticipcpe());
                        expiration.setText(user.getExpiration());
                        profileName.setText(user.getProfileName());

                    } else {
                        Toast.makeText(MainActivity.this,
                                String.format("Response is %s", String.valueOf(response.code()))
                                , Toast.LENGTH_LONG).show();
                    }


                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });

    }

}



