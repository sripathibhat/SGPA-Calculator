package com.dravid.sgpacalci2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Welcome extends ActionBarActivity {

    EditText semester,subjects,credits;
    TextView name;
    Intent intent;
    String usn;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        usn = getIntent().getStringExtra("USN");
        name = (TextView)findViewById(R.id.name);
        String n = helper.getName(usn);//ERROR HERE
        name.setText(n);
    }

    public void onGPA(View view)
    {
        semester = (EditText)findViewById(R.id.semester);
        subjects = (EditText)findViewById(R.id.sub);
        credits = (EditText)findViewById(R.id.credits);
        final String sem = semester.getText().toString();
        final String sub = subjects.getText().toString();
        final String credit = credits.getText().toString();

        if((sem.equals("")) | (sub.equals("")) | (credit.equals("")))
        {
            toast("Please fill all the details");
        }
        else if((Integer.parseInt(sem)>8) |(Integer.parseInt(sem)<=0))
        {
            toast("Invalid semester,it must be between 1 and 8");
        }
        else if((Integer.parseInt(sub)>8) |(Integer.parseInt(sub)<=0))
        {
            toast("Invalid number of subjects,it must be between 1 and 8");
        }


        else
        {
            if(view.getId()==R.id.sgpa) {
                // int s = Integer.parseInt(sem);
                int i= helper.checkSem(sem,usn);

                //toast("error");

                if(i==1){
                    new AlertDialog.Builder(this).setTitle("Confirm").setMessage("The SGPA of sem "+sem+" is already calculated! Are you sure you want to calculate again?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String arr[] = {sem,sub,credit,usn};
                            intent = new Intent(Welcome.this, SGPA.class);
                            intent.putExtra("details",arr);
                            // intent.putExtra("sem", Integer.parseInt(sub));
                            startActivity(intent);
                        }
                    }).setNegativeButton("No", null).show();
                    //toast("The SGPA of sem "+sem+" is already calculated!" );
                }
                else {
                    String arr[] = {sem,sub,credit,usn};
                    intent = new Intent(Welcome.this, SGPA.class);
                    intent.putExtra("details",arr);
                    // intent.putExtra("sem", Integer.parseInt(sub));
                    startActivity(intent);
                }
            }
            else if(view.getId()==R.id.cgpa){
                float cgpa = helper.getCGPA(usn);
                if(cgpa ==0){
                    toast("CGPA is not applicable");
                }
                else
                    toast("CGPA is " + cgpa);
            }
            else if(view.getId() == R.id.bsgpa){
                String sgpa = helper.getSGPA(sem,usn);
                if(sgpa.equals("")){
                    toast("SGPA is not calculated");
                }
                else{
                    toast("SGPA of sem " + sem +" is "+sgpa);
                }





            }

        }
    }

    private void toast(String s) {
        Toast t = Toast.makeText(Welcome.this,s,Toast.LENGTH_SHORT);
        t.show();
    }

}
