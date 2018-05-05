package com.dravid.sgpacalci2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Register extends ActionBarActivity {

    public static final int CONNECTION_TIMEOUT = 100000;
    public static final int READ_TIMEOUT = 150000;
    private static final String REGISTER_URL = "http://192.168.95.2/android_connect/sgpa_reg.php";

    DatabaseHelper helper = new DatabaseHelper(this);
    int flag = 1;
    Intent intent;
    EditText n, u, p1, p2, e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onRegister(View view) {
        if (view.getId() == R.id.reg) {
            n = (EditText) findViewById(R.id.name);
            u = (EditText) findViewById(R.id.usn);
            p1 = (EditText) findViewById(R.id.pass1);
            p2 = (EditText) findViewById(R.id.pass2);
            e = (EditText) findViewById(R.id.email);

            final String name = n.getText().toString();
            final String usn = u.getText().toString();
            final String pass1 = p1.getText().toString();
            final String pass2 = p2.getText().toString();
            final String email = e.getText().toString();

            if ((name.equals("")) | (usn.equals("")) | (pass1.equals("")) | (pass2.equals("")) | (email.equals(""))) {
                toast("Please fill all the details");
            } else if (pass1.length() < 4) {
                toast("Password must be at least 4 characters long");
            } else if (!pass1.equals(pass2)) {
                toast("Passwords don't match");
            } else {
                Contact c = new Contact();
                c.setName(name);
                c.setEmail(email);
                c.setPass(pass1);
                c.setUSN(usn);
                int i = helper.insertContact(c);
                if (i != 0) {
                    register(name,usn,pass1,email);

                    toast("Sign Up Successful! Login now");

                    intent = new Intent(Register.this, Login.class);
                    startActivity(intent);


                } else {
                    toast("USN already registered! ");

                }

            }

        }

    }


    private void toast(String s) {
        Toast t = Toast.makeText(Register.this, s, Toast.LENGTH_SHORT);
        t.show();
    }

    private void register(String name, String usn, String pass1, String email) {
        String urlSuffix = "?name=" + name + "&usn=" + usn + "&password=" + pass1 + "&email=" + email;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //Toast.makeText(MainActivity.this,"PreExecute",Toast.LENGTH_LONG).show();
                loading = ProgressDialog.show(Register.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(MainActivity.this,"PostExecute"+s,Toast.LENGTH_LONG).show();
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                //Toast.makeText(MainActivity.this,REGISTER_URL+s,Toast.LENGTH_LONG).show();
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(REGISTER_URL + s);

                    //Toast.makeText(Register.this,"URL"+s,Toast.LENGTH_LONG).show();
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    //Toast.makeText(MainActivity.this,"Connection Established",Toast.LENGTH_LONG).show();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));  // Exception here

                    String result;

                    result = bufferedReader.readLine();

                    return result;
                } catch (Exception e) {
                    //Toast.makeText(MainActivity.this,"Exception caught",Toast.LENGTH_LONG).show();
                    return null;
                }
            }
        }
        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);

    }
}

 /*
   private class AsyncLogin extends AsyncTask<String,String, String> {
        ProgressDialog pdLoading = new ProgressDialog(Register.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("http://192.168.95.2/android_connect/sgpa_reg.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("name", params[0])
                        .appendQueryParameter("usn", params[1])
                        .appendQueryParameter("pass1",params[2])
                        .appendQueryParameter("email",params[3]);
                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception1";
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuffer result = new StringBuffer();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();
            if (result.equalsIgnoreCase("please fill all values")) {
                /* Here launching another activity when login successful. If you persist login state
                use sharedPreferences of Android. and logout button to clear sharedPreferences.


             // Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
               // startActivity(intent);
                //ainActivity.this.finish();

                //Toast.makeText(Register.this, "Please Fill All The Fields", Toast.LENGTH_LONG).show();
            }


            else if(result.equalsIgnoreCase("successfully registered")){
                Toast.makeText(Register.this, "Successful Registration", Toast.LENGTH_LONG).show();
            }
            else if (result.equalsIgnoreCase("unsuccessful")) {

                Toast.makeText(Register.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();


            }
            else if (result.equalsIgnoreCase("oops! Please try again!")) {

                Toast.makeText(Register.this, "OOPs! Something went wrong before insertion", Toast.LENGTH_LONG).show();


            }

            else if (result.equalsIgnoreCase("exception1")) {

                Toast.makeText(Register.this, "Exception1", Toast.LENGTH_LONG).show();


            }


            else
                Toast.makeText(Register.this, "OOPs! Something went wrong.", Toast.LENGTH_LONG).show();
        }

    }*/









