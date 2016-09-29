package com.example.manmohan.example_asnctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTask_Act  extends Activity {

    private Button BT, BT_Move;
    private EditText ET_Time;
    private TextView Final_Result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        ET_Time = (EditText) findViewById(R.id.et_time);
        BT = (Button) findViewById(R.id.bt_do_it);
        BT_Move = (Button) findViewById(R.id.bt_move);

        Final_Result = (TextView) findViewById(R.id.tv_result);

        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abc runner = new abc();
                String sleepTime = ET_Time.getText().toString();
                runner.execute(sleepTime);
            }
        });

        BT_Move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I_Move = new Intent(AsyncTask_Act.this, AsyncTask_TT.class);
                startActivity(I_Move);
            }
        });
    }


    /*
     * Private class which runs the long operation. ( Sleeping for some time )
     */
    private class abc extends AsyncTask<String, String, String> {

        ProgressDialog P_Dialog;
        private String resp;

        @Override
        protected String doInBackground(String... params) {

            publishProgress("Sleeping....."); // Calls onProgressUpdate()
            try {
                // Do your long operations here and return the result
                int time = Integer.parseInt(params[0]);
                // Sleeping for given time period
                Thread.sleep(time);
                resp = "Slept for : " + time + " milliseconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            P_Dialog.dismiss();
            Final_Result.setText(result);
        }


        @Override
        protected void onPreExecute() {
            // Things to be done before execution of long running operation. For
            // example showing ProgessDialog

            P_Dialog = new ProgressDialog(AsyncTask_Act.this);
            P_Dialog.setMessage("Sleeping Time....");
            P_Dialog.setCancelable(false);
            P_Dialog.show();
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //P_Dialog.dismiss();
            Final_Result.setText(text[0]);
            Toast.makeText(getApplicationContext(), "Hello "+text[0], Toast.LENGTH_SHORT).show();
            // Things to be done while execution of long running operation is in
            // progress. For example updating ProgessDialog
        }
    }
}