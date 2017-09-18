package com.example.gamer.unit3assign1;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by Gamer on 9/18/2017.
 */

public class SourceCodeLoader extends AsyncTask<String,String,String> {
    private EditText urlInput;
    private TextView scOutput;
    Context cntxt;

    public SourceCodeLoader(Context cntxt, TextView scOutput, EditText urlInput) {
        this.urlInput = urlInput;
        this.scOutput = scOutput;
        this.cntxt= cntxt;
    }

    @Override
    protected String doInBackground(String... params) {

        String queryString = params[0];
        publishProgress("Getting the source code please wait...");
        Ion.with(cntxt)
                .load(queryString)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        scOutput.setText(result);
                    }
                });
        return "Enter URL";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            //  scOutput.setText(s);
            urlInput.setHint(s);
            urlInput.setText("");

        } catch (Exception e){

            scOutput.setText("Failed");
            urlInput.setText("");
            e.printStackTrace();
        }
    }

    @Override
    protected void onProgressUpdate(String... text) {
        scOutput.setText(text[0]);
    }
}
