package com.dravid.sgpacalci2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends ActionBarActivity {

    Intent intent;
    EditText u,p;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLogin(View view)
    {
        if(view.getId()==R.id.submit)
        {

            u = (EditText)findViewById(R.id.usn);
            p = (EditText)findViewById(R.id.pass);
            String usn = u.getText().toString();
            String pass = p.getText().toString();
            String pwd = helper.searchPass(usn);

            if((usn.equals("")) | (pass.equals("")))
            {
                toast("Please fill the details");
            }

            else if(pwd.equals("not found"))
            {
                toast("USN not registered");
            }

            else if(pwd.equals(pass))
            {
                intent = new Intent(Login.this,After_Login.class);
                intent.putExtra("USN",usn);
                startActivity(intent);
            }
            else
            {
                toast("USN and password do not match");
            }
        }

    }


    private void toast(String s) {
        Toast t = Toast.makeText(Login.this,s,Toast.LENGTH_SHORT);
        t.show();
    }




}
