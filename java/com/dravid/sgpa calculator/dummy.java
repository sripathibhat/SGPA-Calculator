package com.dravid.sgpacalci2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;


public class dummy extends ActionBarActivity {
  DatabaseHelper helper = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
        TextView u = (TextView)findViewById(R.id.title);
        TextView sem = (TextView)findViewById(R.id.sem);
        TextView gpa = (TextView)findViewById(R.id.gpa);
        TextView cgpa = (TextView)findViewById(R.id.cgpa);

        String usn = getIntent().getStringExtra("USN");
        String semester = getIntent().getStringExtra("sem");
        String gpas = getIntent().getStringExtra("gpa");
        float cg = helper.getCGPA(usn);
        String avg = String.valueOf(cg);
        cgpa.setText("CGPA : "+avg);
        u.setText("USN : "+usn);
        sem.setText(semester);
        gpa.setText(gpas);
    }

}
