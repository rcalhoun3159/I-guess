package com.example.student.myapplication;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
//Button but;
    View rootView;
    Card[] card;
    int z = 0;
    Handler mHandler;
    TextView tv;
    TextView tv1;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_main, container,
                false);

        rootView.setBackgroundColor(Color.BLACK);
        tv = (TextView) rootView.findViewById(R.id.textView);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OldLondon.tff");
        tv.setTypeface(tf);
        tv.setTextSize(48);
        tv.setTextColor(Color.RED);
        tv1 = (TextView) rootView.findViewById(R.id.textView1);
        tv1.setTypeface(tf);
        tv1.setTextSize(48);
        tv1.setTextColor(Color.RED);


        card = new Card[52];

        for (int s = 0; s < 4; s++){
            for (int v = 0; v < 13; v++){
                card[z] = new Card(s,v);
                z++;
            }
        }

        card = shuffleDeck(card);
        GetterSetter.card = card;

        mHandler = new Handler();
        mHandler.post(mUpdate);

        return rootView;
    }

    private Runnable mUpdate = new Runnable(){

        public void run() {
            tv1.setText("" + GetterSetter.playerScore + " ");
            tv.setText("" + GetterSetter.dealerScore + " ");

            if (GetterSetter.buttonpressed == 0){
                if (GetterSetter.dealerhit > 1){
                    if (GetterSetter.dealerScore < 1) {
                        GetterSetter.playerScore = 0;
                        GetterSetter.dealerScore = 0;
                        GetterSetter.dealerhit++;
                        GetterSetter.buttonpressed = 1;
                    }
                }
            }

            mHandler.postDelayed(this, 1);
        }
    };

    public Card[] shuffleDeck(Card[] deck){
        Random var = new Random();
        Card tempCard = new Card(0,0);
        for (int sC=0;sC<52;sC++){
            int rC = var.nextInt(52);
            tempCard = deck[rC];
            deck[rC] = deck[sC];
            deck[sC] = tempCard;
        }
    return deck;
    }

}