package com.example.manmohan.example_asnctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by manmohan on 14/11/15.
 */
public class AsyncTask_TT extends Activity {

        Button BT_start;
        TextView TV_msg;
        EditText ET_time;
        @Override

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_asyntask);

            BT_start=(Button)findViewById(R.id.bt_submit);
            TV_msg=(TextView)findViewById(R.id.tv_msg);
            ET_time=(EditText)findViewById(R.id.editText);

            BT_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    abc runner = new abc();
                    String sleepTime = ET_time.getText().toString();
                    runner.execute(sleepTime);
                }
            });
        }

        /*
         * Private class which runs the long operation. ( Sleeping for some time )
         */
        private class abc extends AsyncTask<String, String, String> {

            ProgressDialog P_Dialog;
            private String resp;
            int time,i=0,j=1;
            @Override
            protected String doInBackground(String... params) {

                try {
                    // Do your long operations here and return the result
                    time = Integer.parseInt(params[0]);
                    // Sleeping for given time period

                    i=time+1;
                    while(i>=1) {
                        Thread.sleep(1000);

                        runOnUiThread(new Runnable() {
                            public void run() {
                               // P_Dialog.setMessage("Resume in :: " + i + " seconds");
                                TV_msg.setText("Sleeping since "+i+" seconds");

                                // Calls onProgressUpdate()

                            }


                        });
                        i--;
                        publishProgress(""+j);
                        j++;
                    }

                    //P_Dialog.setMessage("second time sleeping");
                    //Thread.sleep(4000);
                    resp = "Slept for : " + time + " Seconds";
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
            protected void onPostExecute(final String result) {
                // execution of result of Long time consuming operation

                runOnUiThread(new Runnable() {
                    public void run() {
                        P_Dialog.dismiss();
                        TV_msg.setText(result);
                    }
                });

            }


            @Override
            protected void onPreExecute() {
                // Things to be done before execution of long running operation. For
                // example showing ProgessDialog

                P_Dialog = new ProgressDialog(AsyncTask_TT.this);
                P_Dialog.setCancelable(false);
                P_Dialog.show();
            }


            @Override
            protected void onProgressUpdate(String... text) {
                //P_Dialog.dismiss();
                //TV_msg.setText("Sleeping since "+text[0]+" seconds");
                P_Dialog.setMessage("Resume in :: " + text[0] + " seconds");
            }
        }
}
