package com.example.vijaya.androidhardware;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import androidx.appcompat.app.AppCompatActivity;

public class StorageActivity extends AppCompatActivity {
    EditText txt_content;
    EditText contenttoDisplay;
    String FILENAME = "MyAppStorage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        txt_content = (EditText) findViewById(R.id.id_txt_mycontent);
        contenttoDisplay = (EditText) findViewById(R.id.id_txt_display);
    }

    public void saveTofile(View v) throws IOException {
        // ICP Task4: Write the code to save the text
        if(TextUtils.isEmpty(txt_content.getText())){
            Toast.makeText(this, "Please provide some text content", Toast.LENGTH_SHORT).show();
        }
        else{
            writeToFile(txt_content.getText().toString(),getApplicationContext());
            txt_content.setText("");
        }
    }

    public void retrieveFromFile(View v) throws IOException {
        // ICP Task4: Write the code to display the above saved text

        try {

            // create inputstream and read the content of the file,,,
            FileInputStream fin = openFileInput(FILENAME);
            int c;
            String temp="";
            while( (c = fin.read()) != -1){
                temp = temp + Character.toString((char)c);
            }

            // change the visibility of textview to visible
            contenttoDisplay.setVisibility(View.VISIBLE);

            // displaying the content the user has entered...
            contenttoDisplay.setText(temp);

        }
        catch(Exception e){
        }
    }


    private void writeToFile(String data,Context context) {
        data += " ";
        try {
            // create output stream and write to file..

            FileOutputStream fOut = openFileOutput(FILENAME,context.MODE_APPEND);
            fOut.write(data.getBytes());
            fOut.close();
            Toast.makeText(getBaseContext(),"Data Stored Successfully! ",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
