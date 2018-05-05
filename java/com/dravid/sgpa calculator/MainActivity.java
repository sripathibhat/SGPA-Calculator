package com.dravid.sgpacalci2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public void onSubmit(View view)
    {
        if(view.getId()==R.id.login)
        {
            Intent intent = new Intent(MainActivity.this,Login.class);
            startActivity(intent);

        }
        else if(view.getId()==R.id.register)
        {
            Intent intent = new Intent(MainActivity.this,Register.class);
            startActivity(intent);

        }
    }

    public void onBackPressed(){


        new AlertDialog.Builder(this).setTitle("Alert").setMessage("Are you sure you want to exit?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).setNegativeButton("No", null).show();
    }




}
