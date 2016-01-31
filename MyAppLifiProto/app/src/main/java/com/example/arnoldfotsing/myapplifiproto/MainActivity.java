package com.example.arnoldfotsing.myapplifiproto;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends ActionBarActivity {
        //Commentaire claude
        //commentaire encore claude
        //Commentaire hassan
        //commentaire encore Hqqssqn
    int a = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configurePosition();
    }

    public void configurePosition () {

        ImageButton imageButtonPositon = (ImageButton) findViewById(R.id.imageButton2);

        imageButtonPositon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i=20, j=20, k=20, l=20;
                ImageButton imageButtonPositon = (ImageButton) findViewById(R.id.imageButton2);
                for (int h=20; h<1000  ;h+=5){

                    imageButtonPositon.setPadding(i+h,j+h,k+h,l+h);
                    imageButtonPositon.setBackgroundColor(0x78FFAB11);

                    for (int p=0; p<1000000 ;p++){
                        //for (int q=0; q<1000000 ;q++){
                        //}
                    }
                }
            }
        });

        /*
        imageButtonPositon.setBackgroundColor(10);
        System.out.print("color 10");
        imageButtonPositon.setBackgroundColor(100);
        System.out.print("color 100");
        imageButtonPositon.setBackgroundColor(255);
        System.out.print("color 255");

        int i=20, j=20, k=20, l=20;
        for (int h=20; h<2000  ;h+=5){
            imageButtonPositon.setPadding(i+h,j+h,k+h,l+h);
            for (int p=0; p<1000000 ;p++){
                //for (int q=0; q<1000000 ;q++){
                //}
            }
        }*/



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
