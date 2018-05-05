package com.dravid.sgpacalci2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SGPA extends ActionBarActivity {

    EditText s1, s2, s3, s4, s5, s6, s7, s8, c1, c2, c3, c4, c5, c6, c7, c8;
    String f1, f2, f3, f4, f5, f6, f7, f8, b1, b2, b3, b4, b5, b6, b7, b8;
    Button cgpa,per;
    String arr[];
    DatabaseHelper helper = new DatabaseHelper(this);
    Contact c = new Contact();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgpa);
        arr = getIntent().getStringArrayExtra("details");

        c.setSem(arr[0]);
        c.setSub(arr[1]);
        c.setCredits(arr[2]);
        c.setUSN(arr[3]);
    }

    public void onCGPA(View view) {
        if (view.getId() == R.id.cgpa || view.getId()==R.id.per) {


            int semester = Integer.parseInt(arr[1]);

            // int semester = getIntent().getIntExtra("sem", 2);
            cgpa = (Button) findViewById(R.id.cgpa);
            per = (Button)findViewById(R.id.per);
            float result,percent;
            String r="10",p;
            float g1, g2, g3, g4, g5, g6, g7, g8, h1, h2, h3, h4, h5, h6, h7, h8;
            s1 = (EditText) findViewById(R.id.s1);
            s2 = (EditText) findViewById(R.id.s2);
            s3 = (EditText) findViewById(R.id.s3);
            s4 = (EditText) findViewById(R.id.s4);
            s5 = (EditText) findViewById(R.id.s5);
            s6 = (EditText) findViewById(R.id.s6);
            s7 = (EditText) findViewById(R.id.s7);
            s8 = (EditText) findViewById(R.id.s8);
            c1 = (EditText) findViewById(R.id.c1);
            c2 = (EditText) findViewById(R.id.c2);
            c3 = (EditText) findViewById(R.id.c3);
            c4 = (EditText) findViewById(R.id.c4);
            c5 = (EditText) findViewById(R.id.c5);
            c6 = (EditText) findViewById(R.id.c6);
            c7 = (EditText) findViewById(R.id.c7);
            c8 = (EditText) findViewById(R.id.c8);
            f1 = s1.getText().toString();
            f2 = s2.getText().toString();
            f3 = s3.getText().toString();
            f4 = s4.getText().toString();
            f5 = s5.getText().toString();
            f6 = s6.getText().toString();
            f7 = s7.getText().toString();
            f8 = s8.getText().toString();
            b1 = c1.getText().toString();
            b2 = c2.getText().toString();
            b3 = c3.getText().toString();
            b4 = c4.getText().toString();
            b5 = c5.getText().toString();
            b6 = c6.getText().toString();
            b7 = c7.getText().toString();
            b8 = c8.getText().toString();
            switch (semester) {
                case 1:

                    if (f1.equals("") || b1.equals("") || !f2.equals("") || !b2.equals("") || !f3.equals("") || !b3.equals("") ||
                            !f4.equals("") || !b4.equals("")
                            || !f5.equals("") || !b5.equals("") || !f6.equals("") || !b6.equals("") || !f7.equals("") || !b7.equals("") ||
                            !f8.equals("") || !b8.equals(""))
                        toast("1");
                    else {

                        g1 = Float.parseFloat(f1);
                        r = f1;
                        h1 = Float.parseFloat(b1);
                        int total = (int) h1;
                        String tot = String.valueOf(total);
                        if (!tot.equals(arr[2])) {
                            Toast t = Toast.makeText(this, "Total credits must be " + arr[2], Toast.LENGTH_SHORT);
                            t.show();
                        } else {
                            percent = (g1 - 0.75f) * 10f;
                            percent = Math.round(percent * 100.0f) / 100.0f;
                            p = Float.toString(percent);
                            per.setText(p);
                            cgpa.setText(f1);
                            c.setSGPA(r);
                            long i = helper.insertSGPA(c);
                            if (i != 0) {
                                Toast t = Toast.makeText(this, "SGPA is recorded and stored", Toast.LENGTH_SHORT);
                                t.show();
                            } else {
                                Toast t = Toast.makeText(this, "Don't click me again and again", Toast.LENGTH_SHORT);
                                t.show();
                            }
                        }
                    }

                    break;
                case 2:
                    if (f1.equals("") || b1.equals("") || f2.equals("") || b2.equals("") || !f3.equals("") || !b3.equals("") ||
                            !f4.equals("") || !b4.equals("")
                            || !f5.equals("") || !b5.equals("") || !f6.equals("") || !b6.equals("") || !f7.equals("") || !b7.equals("") ||
                            !f8.equals("") || !b8.equals(""))
                        toast("2");
                    else {

                        g1 = Float.parseFloat(f1);
                        g2 = Float.parseFloat(f2);
                        h1 = Float.parseFloat(b1);
                        h2 = Float.parseFloat(b2);
                        int total = (int)(h1+h2);
                        String tot = String.valueOf(total);
                        if (!tot.equals(arr[2])) {
                            Toast t = Toast.makeText(this,"Total credits must be " + arr[2],Toast.LENGTH_SHORT);
                            t.show();
                        } else {
                            result = ((g1 * h1) + (g2 * h2)) / (h1 + h2);

                            //result = Math.round(result*100.0f)/100.0f;
                            percent = (result - 0.75f) * 10f;
                            // percent = Math.round(percent*100.0f)/100.0f;
                            r = Float.toString(result);
                            p = Float.toString(percent);
                            per.setText(p);
                            cgpa.setText(r);
                            c.setSGPA(r);
                            long i = helper.insertSGPA(c);
                            if (i != 0) {
                                Toast t = Toast.makeText(this, "SGPA is recorded and stored", Toast.LENGTH_SHORT);
                                t.show();
                            } else {
                                Toast t = Toast.makeText(this, "Don't click me again and again", Toast.LENGTH_SHORT);
                                t.show();
                            }
                        }
                    }
                    break;

                case 3:
                    if (f1.equals("") || b1.equals("") || f2.equals("") || b2.equals("") || f3.equals("") || b3.equals("") ||
                            !f4.equals("") || !b4.equals("")
                            || !f5.equals("") || !b5.equals("") || !f6.equals("") || !b6.equals("") || !f7.equals("") || !b7.equals("") ||
                            !f8.equals("") || !b8.equals(""))
                        toast("3");
                    else {

                        g1 = Float.parseFloat(f1);
                        g2 = Float.parseFloat(f2);
                        g3 = Float.parseFloat(f3);
                        h1 = Float.parseFloat(b1);
                        h2 = Float.parseFloat(b2);
                        h3 = Float.parseFloat(b3);
                        int total = (int) (h1 + h2 + h3);
                        String tot = String.valueOf(total);
                        if (!tot.equals(arr[2])) {
                            Toast t = Toast.makeText(this, "Total credits must be " + arr[2], Toast.LENGTH_SHORT);
                            t.show();

                        } else {
                            result = ((g1 * h1) + (g2 * h2) + (g3 * h3)) / (h1 + h2 + h3);

                            //  result = Math.round(result*100.0f)/100.0f;
                            percent = (result - 0.75f) * 10f;
                            // percent = Math.round(percent*100.0f)/100.0f;
                            r = Float.toString(result);
                            p = Float.toString(percent);
                            per.setText(p);
                            cgpa.setText(r);
                            c.setSGPA(r);
                            long i = helper.insertSGPA(c);
                            if (i != 0) {
                                Toast t = Toast.makeText(this, "SGPA is recorded and stored", Toast.LENGTH_SHORT);
                                t.show();
                            } else {
                                Toast t = Toast.makeText(this, "Don't click me again and again", Toast.LENGTH_SHORT);
                                t.show();
                            }
                        }
                    }
                    break;

                case 4:
                    if (f1.equals("") || b1.equals("") || f2.equals("") || b2.equals("") || f3.equals("") || b3.equals("") ||
                            f4.equals("") || b4.equals("")
                            || !f5.equals("") || !b5.equals("") || !f6.equals("") || !b6.equals("") || !f7.equals("") || !b7.equals("") ||
                            !f8.equals("") || !b8.equals(""))
                        toast("4");
                    else {

                        g1 = Float.parseFloat(f1);
                        g2 = Float.parseFloat(f2);
                        g3 = Float.parseFloat(f3);
                        g4 = Float.parseFloat(f4);
                        h1 = Float.parseFloat(b1);
                        h2 = Float.parseFloat(b2);
                        h3 = Float.parseFloat(b3);
                        h4 = Float.parseFloat(b4);
                        int total = (int)(h1 + h2 + h3 + h4);
                        String tot = String.valueOf(total);
                        if (!tot.equals(arr[2])) {
                            Toast t =Toast.makeText(this,"Total credits must be "+arr[2],Toast.LENGTH_SHORT);
                            t.show();

                        } else {
                            result = ((g1 * h1) + (g2 * h2) + (g3 * h3) + (g4 * h4)) / (h1 + h2 + h3 + h4);

                            //  result = Math.round(result*100.0f)/100.0f;
                            percent = (result - 0.75f) * 10f;
                            //   percent = Math.round(percent*100.0f)/100.0f;
                            r = Float.toString(result);
                            p = Float.toString(percent);
                            per.setText(p);
                            cgpa.setText(r);
                            c.setSGPA(r);
                            long i = helper.insertSGPA(c);
                            if (i != 0) {
                                Toast t = Toast.makeText(this, "SGPA is recorded and stored", Toast.LENGTH_SHORT);
                                t.show();
                            } else {
                                Toast t = Toast.makeText(this, "Don't click me again and again", Toast.LENGTH_SHORT);
                                t.show();
                            }
                        }
                    }
                    break;

                case 5:
                    if (f1.equals("") || b1.equals("") || f2.equals("") || b2.equals("") || f3.equals("") || b3.equals("") ||
                            f4.equals("") || b4.equals("")
                            || f5.equals("") || b5.equals("") || !f6.equals("") || !b6.equals("") || !f7.equals("") || !b7.equals("") ||
                            !f8.equals("") || !b8.equals(""))
                        toast("5");
                    else {

                        g1 = Float.parseFloat(f1);
                        g2 = Float.parseFloat(f2);
                        g3 = Float.parseFloat(f3);
                        g4 = Float.parseFloat(f4);
                        g5 = Float.parseFloat(f5);
                        h1 = Float.parseFloat(b1);
                        h2 = Float.parseFloat(b2);
                        h3 = Float.parseFloat(b3);
                        h4 = Float.parseFloat(b4);
                        h5 = Float.parseFloat(b5);
                        int total = (int) (h1 + h2 + h3 + h4 + h5);
                        String tot = String.valueOf(total);
                        if (!tot.equals(arr[2])) {
                            Toast t = Toast.makeText(this, "Total credits must be " + arr[2], Toast.LENGTH_SHORT);
                            t.show();

                        } else {
                            result = ((g1 * h1) + (g2 * h2) + (g3 * h3) + (g4 * h4) + (g5 * h5)) / (h1 + h2 + h3 + h4 + h5);

                            //  result = Math.round(result*100.0f)/100.0f;
                            percent = (result - 0.75f) * 10f;
                            // percent = Math.round(percent*100.0f)/100.0f;
                            r = Float.toString(result);
                            p = Float.toString(percent);
                            per.setText(p);
                            cgpa.setText(r);
                            c.setSGPA(r);
                            long i = helper.insertSGPA(c);
                            if (i != 0) {
                                Toast t = Toast.makeText(this, "SGPA is recorded and stored", Toast.LENGTH_SHORT);
                                t.show();
                            } else {
                                Toast t = Toast.makeText(this, "Don't click me again and again", Toast.LENGTH_SHORT);
                                t.show();
                            }
                        }
                    }
                    break;


                case 6:
                    if (f1.equals("") || b1.equals("") || f2.equals("") || b2.equals("") || f3.equals("") || b3.equals("") ||
                            f4.equals("") || b4.equals("")
                            || f5.equals("") || b5.equals("") || f6.equals("") || b6.equals("") || !f7.equals("") || !b7.equals("") ||
                            !f8.equals("") || !b8.equals(""))
                        toast("6");
                    else {

                        g1 = Float.parseFloat(f1);
                        g2 = Float.parseFloat(f2);
                        g3 = Float.parseFloat(f3);
                        g4 = Float.parseFloat(f4);
                        g5 = Float.parseFloat(f5);
                        g6 = Float.parseFloat(f6);
                        h1 = Float.parseFloat(b1);
                        h2 = Float.parseFloat(b2);
                        h3 = Float.parseFloat(b3);
                        h4 = Float.parseFloat(b4);
                        h5 = Float.parseFloat(b5);
                        h6 = Float.parseFloat(b6);
                        int total = (int)(h1 + h2 + h3 + h4 + h5 + h6);
                        String tot = String.valueOf(total);
                        if (!tot.equals(arr[2])) {
                            Toast t =Toast.makeText(this,"Total credits must be "+arr[2],Toast.LENGTH_SHORT);
                            t.show();

                        } else {
                            result = ((g1 * h1) + (g2 * h2) + (g3 * h3) + (g4 * h4) + (g5 * h5) + (g6 * h6)) / (h1 + h2 + h3 + h4 + h5 + h6);

                            result = Math.round(result * 100.0f) / 100.0f;
                            percent = (result - 0.75f) * 10f;
                            percent = Math.round(percent * 100.0f) / 100.0f;
                            r = Float.toString(result);
                            p = Float.toString(percent);
                            per.setText(p);
                            cgpa.setText(r);
                            c.setSGPA(r);
                            long i = helper.insertSGPA(c);
                            if (i != 0) {
                                Toast t = Toast.makeText(this, "SGPA is recorded and stored", Toast.LENGTH_SHORT);
                                t.show();
                            } else {
                                Toast t = Toast.makeText(this, "Don't click me again and again", Toast.LENGTH_SHORT);
                                t.show();
                            }
                        }
                    }
                    break;

                case 7:
                    if (f1.equals("") || b1.equals("") || f2.equals("") || b2.equals("") || f3.equals("") || b3.equals("") ||
                            f4.equals("") || b4.equals("")
                            || f5.equals("") || b5.equals("") || f6.equals("") || b6.equals("") || f7.equals("") || b7.equals("") ||
                            !f8.equals("") || !b8.equals(""))
                        toast("7");
                    else {

                        g1 = Float.parseFloat(f1);
                        g2 = Float.parseFloat(f2);
                        g3 = Float.parseFloat(f3);
                        g4 = Float.parseFloat(f4);
                        g5 = Float.parseFloat(f5);
                        g6 = Float.parseFloat(f6);
                        g7 = Float.parseFloat(f7);
                        h1 = Float.parseFloat(b1);
                        h2 = Float.parseFloat(b2);
                        h3 = Float.parseFloat(b3);
                        h4 = Float.parseFloat(b4);
                        h5 = Float.parseFloat(b5);
                        h6 = Float.parseFloat(b6);
                        h7 = Float.parseFloat(b7);
                        int total = (int) (h1 + h2 + h3 + h4 + h5 + h6 + h7);
                        String tot = String.valueOf(total);
                        if (!tot.equals(arr[2])) {
                            Toast t =Toast.makeText(this,"Total credits must be "+arr[2],Toast.LENGTH_SHORT);
                            t.show();
                        } else {
                            result = ((g1 * h1) + (g2 * h2) + (g3 * h3) + (g4 * h4) + (g5 * h5) + (g6 * h6) + (g7 * h7)) / (h1 + h2 + h3 + h4 + h5 + h6 + h7);
                            result = Math.round(result * 100.0f) / 100.0f;
                            percent = (result - 0.75f) * 10f;
                            percent = Math.round(percent * 100.0f) / 100.0f;
                            r = Float.toString(result);
                            p = Float.toString(percent);
                            per.setText(p);
                            cgpa.setText(r);
                            c.setSGPA(r);
                            long i = helper.insertSGPA(c);
                            if (i != 0) {
                                Toast t = Toast.makeText(this, "SGPA is recorded and stored", Toast.LENGTH_SHORT);
                                t.show();
                            } else {
                                Toast t = Toast.makeText(this, "Don't click me again and again", Toast.LENGTH_SHORT);
                                t.show();
                            }
                        }
                    }
                    break;

                case 8:
                    if (f1.equals("") || b1.equals("") || f2.equals("") || b2.equals("") || f3.equals("") || b3.equals("") ||
                            f4.equals("") || b4.equals("")
                            || f5.equals("") || b5.equals("") || f6.equals("") || b6.equals("") || f7.equals("") || b7.equals("") ||
                            f8.equals("") || b8.equals(""))
                        toast("8");
                    else {


                        g1 = Float.parseFloat(f1);
                        g2 = Float.parseFloat(f2);
                        g3 = Float.parseFloat(f3);
                        g4 = Float.parseFloat(f4);
                        g5 = Float.parseFloat(f5);
                        g6 = Float.parseFloat(f6);
                        g7 = Float.parseFloat(f7);
                        g8 = Float.parseFloat(f8);
                        h1 = Float.parseFloat(b1);
                        h2 = Float.parseFloat(b2);
                        h3 = Float.parseFloat(b3);
                        h4 = Float.parseFloat(b4);
                        h5 = Float.parseFloat(b5);
                        h6 = Float.parseFloat(b6);
                        h7 = Float.parseFloat(b7);
                        h8 = Float.parseFloat(b8);
                        int total = (int) (h1 + h2 + h3 + h4 + h5 + h6 + h7 + h8);
                        String tot = String.valueOf(total);
                        if (!tot.equals(arr[2])) {
                            Toast t =Toast.makeText(this,"Total credits must be "+arr[2],Toast.LENGTH_SHORT);
                            t.show();

                        } else {
                            result = ((g1 * h1) + (g2 * h2) + (g3 * h3) + (g4 * h4) + (g5 * h5) + (g6 * h6) + (g7 * h7) + (g8 * h8)) / (h1 + h2 + h3 + h4 + h5 + h6 + h7 + h8);
                            result = Math.round(result * 100.0f) / 100.0f;
                            percent = (result - 0.75f) * 10f;
                            percent = Math.round(percent * 100.0f) / 100.0f;
                            r = Float.toString(result);
                            p = Float.toString(percent);
                            per.setText(p);
                            cgpa.setText(r);
                            c.setSGPA(r);
                            long i = helper.insertSGPA(c);
                            if (i != 0) {
                                Toast t = Toast.makeText(this, " SGPA is recorded and stored", Toast.LENGTH_SHORT);
                                t.show();
                            } else {
                                Toast t = Toast.makeText(this, "Don't click me again and again", Toast.LENGTH_SHORT);
                                t.show();
                            }
                        }
                    }
                    break;
            }


        }
    }


    public void toast(String sem) {
        Toast t = Toast.makeText(this, "You must Enter details of exactly " + sem + " subjects", Toast.LENGTH_SHORT);
        t.show();
    }

}
