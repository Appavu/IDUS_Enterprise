package com.example.rohasoft.idus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rohasoft.idus.others.GetUserCallBack;
import com.example.rohasoft.idus.others.ServerRequest;
import com.example.rohasoft.idus.others.User;
import com.example.rohasoft.idus.others.UserLocalStore;

public class LoginActivity extends AppCompatActivity {

    EditText editText_UserId,editText_Password;
    Button button_login;
    UserLocalStore userLocalstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_UserId= (EditText) findViewById(R.id.editText_UserId);
        editText_Password= (EditText) findViewById(R.id.editText_Password);

        button_login= (Button) findViewById(R.id.button_login);


        userLocalstore=new UserLocalStore(this);

        logIn();




    }

    private void logIn() {

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=editText_UserId.getText().toString();
                String pass=editText_Password.getText().toString();
                User user=new User(username,pass);
                authenticate(user);
            }
        });
    }

    private void authenticate(User user){
        ServerRequest serverRequest=new ServerRequest(this);
        serverRequest.fetchUserDataInBackground(user, new GetUserCallBack() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null){
                    showErrorMessage();
                }else {
                    logUserIn(returnedUser );
                }
            }
        });
    }

    private void showErrorMessage(){

        android.support.v7.app.AlertDialog.Builder builder=new android.support.v7.app.AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("Incorrect user details :(");
        builder.setPositiveButton("ok",null);
        builder.show();

    }

    private void logUserIn(User returnedUser ){

        userLocalstore.storeUserData(returnedUser);
        userLocalstore.setUserLoggedIn(true);

        startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }

}
