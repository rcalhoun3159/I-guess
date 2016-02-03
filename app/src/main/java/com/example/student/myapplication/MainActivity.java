package com.example.student.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
ImageButton but;
    TextView tv = null;
    int i = 0;
    int a = 0;
    FragmentManager fm;
    MainActivityFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but = (ImageButton) findViewById(R.id.button);
        fm = getSupportFragmentManager();
        fragment = (MainActivityFragment)fm.findFragmentById(R.id.fragment);
        tv = fragment.tv;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void clickMethod(View view) {

        GetterSetter.playerScore = 0;
        GetterSetter.dealerScore = 0;
        GetterSetter.hit++;
        GetterSetter.buttonpressed = 1;

        //tv.setText("Card Number: " + GetterSetter.hit + " suit: " + fragment.card[GetterSetter.hit].suit + " rank: " + fragment.card[GetterSetter.hit].rank);
        //tv.setText("Card Number: " + a + " suit: " + fragment.card[a].suit + " rank: " + fragment.card[a].rank);
        //GetterSetter.currentCard = a;
        //a++;
    }

    public void clickMethod2 (View view) {

        GetterSetter.playerScore = 0;
        GetterSetter.dealerScore = 0;
        GetterSetter.dealerhit = GetterSetter.hit;
        GetterSetter.buttonpressed = 1;
    }

    public void clickMethod3 (View view) {
        //Redeal

        GetterSetter.playerScore = 0;
        GetterSetter.dealerScore = 0;
        GetterSetter.hit = 3;
        GetterSetter.dealerhit = 1;
        GetterSetter.buttonpressed = 1;
        fragment.shuffleDeck(GetterSetter.card);
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
