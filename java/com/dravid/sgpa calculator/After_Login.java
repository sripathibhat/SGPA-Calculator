package com.dravid.sgpacalci2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class After_Login extends ActionBarActivity {

    Intent intent;
    TextView name;
    String usn;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after__login);
        usn = getIntent().getStringExtra("USN");
        String nam = helper.getName(usn);
        name = (TextView)findViewById(R.id.name);
        name.setText(nam);
    }

    public void onSGPA(View v)
    {
        if(v.getId()==R.id.view)
        {
            String seme = helper.getSems(usn);
            String gpa = helper.getGPAs(usn);
            if((seme.equals("\n"))||(gpa.equals("\n")))
            {
                Toast t = Toast.makeText(this,"SGPA is not calculated for usn number "+usn,Toast.LENGTH_SHORT);
                t.show();
            }
            else {
                intent = new Intent(After_Login.this, dummy.class);
                intent.putExtra("USN",usn);
                intent.putExtra("sem", seme);
                intent.putExtra("gpa", gpa);
                startActivity(intent);
            }
        }
        else if(v.getId()== R.id.newgpa)
        {
            intent = new Intent(After_Login.this,Welcome.class);
            intent.putExtra("USN",usn);
            startActivity(intent);
        }
    }



}
