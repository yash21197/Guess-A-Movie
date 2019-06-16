package com.example.yashu.guess_the_movie;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Movie extends AppCompatActivity {

    private TextView t1,t2,t3,t4;
    private EditText e1;
    private Button b1;
    private boolean turn=true;

    private char lastchar;

    private static TreeStructure ts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);


        t1 = (TextView)findViewById(R.id.text1);
        t2 = (TextView)findViewById(R.id.text2);
        t3 = (TextView)findViewById(R.id.text3);
        t4 = (TextView)findViewById(R.id.text4);

        e1 = (EditText)findViewById(R.id.etext5);

        b1 = (Button)findViewById(R.id.btn1);
        AssetManager assetManager=getAssets();
        try
        {
            InputStream is=assetManager.open("movies.txt");
            ts = new TreeStructure(is);
        }
        catch (IOException e) {
            e.printStackTrace();
        }



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(turn==true)
                {
                    check(e1.getText().toString().toLowerCase());}

            }
        });
        t2.setText("USER TURN");

        Random ran = new Random();
        lastchar = (char)(ran.nextInt(26)+97);

        t3.setText(""+lastchar);


    }

    public void check(String str){

        if(!str.equals("")){
        if(lastchar==str.charAt(0)){
        boolean ch = ts.search(str);
        if(ch==false){
            Toast.makeText(this, "User Loose." , Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Movie.this, MainActivity.class);
            Movie.this.startActivity(intent);
            finish();
        }else if(ch==true){
            lastchar = str.charAt(str.length()-1);
            t3.setText(""+lastchar);
          turn=false;
            e1.setText("");
            computer();
        }
        }
        else{
            e1.setText("");
            Toast.makeText(this, "Invalid Input" , Toast.LENGTH_SHORT).show();
        }}
        else
        { Toast.makeText(this, "Please Enter Movie Name" , Toast.LENGTH_SHORT).show();}
    }

    private void computer(){
        if(turn==false){
            //Random ran = new Random();
           // int choise = ran.nextInt(26)+97;
            String makestr =lastchar+"";
            String ch = ts.computersearch(makestr.toString().trim());
            if(ch.equals("")){
                Toast.makeText(this, "User Win." , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Movie.this, MainActivity.class);
                Movie.this.startActivity(intent);
                finish();
            }else{
                lastchar = ch.charAt(ch.length()-1);
                t3.setText(lastchar+"");
                Toast.makeText(this, ch , Toast.LENGTH_SHORT).show();

                turn=true;
            }
        }
    }

}
