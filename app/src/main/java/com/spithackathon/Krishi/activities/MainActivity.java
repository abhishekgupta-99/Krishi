package com.spithackathon.Krishi.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import com.spithackathon.Krishi.R;
import com.spithackathon.Krishi.adapters.MainAdapter;
import com.spithackathon.Krishi.modals.MainListItem;
import com.spithackathon.Krishi.utilities.TextToSpeech;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<MainListItem> list;
    private RecyclerView recyclerView;
    private MainAdapter adapter;
     MediaPlayer mp;

    //final MediaPlayer mp= MediaPlayer.create(this, R.raw.home);

//    MediaPlayer mp = null;
//    String kVoiceRssServer = "http://api.voicerss.org";
//    String kVoiceRSSAppKey = "097398c17f554f11a641ddbbb435a6c3";

    private Integer[] imageUrls={R.drawable.ic_vegetable,R.drawable.ic_pesticide,R.drawable.ic_parliament, R.drawable.ic_robot};
    private Integer[] hindiTexts={R.string.crop_production_card_title_hi,R.string.treatment_card_title_hi,R.string.policy_card_title_hi, R.string.chatbot_hi};
    private Integer[] englishTexts={R.string.crop_production_card_title_en,R.string.treatment_card_title_en,R.string.policy_card_title_en, R.string.chatbot_en};
    private String[] backgroundColors={"#35e372","#a4f075","#ff9f80", "#ff9f80"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         mp= MediaPlayer.create(this, R.raw.home);



        Intent[] links={
                new Intent(MainActivity.this, CropProductionActivity.class),
                new Intent(MainActivity.this, SelectProblem.class),
                new Intent(MainActivity.this, Select_Policy.class),
                new Intent(MainActivity.this, ChatbotActivity.class)
        };

        list = new ArrayList<>();
        for(int i=0;i<imageUrls.length;i++){
            MainListItem item=new MainListItem();

            item.setImageUrl(imageUrls[i]);
            item.setHindiText(hindiTexts[i]);
            item.setEnglishText(englishTexts[i]);
            item.setBackgroundColor(backgroundColors[i]);
            item.setIntent(links[i]);
            item.setBackgroundColor(backgroundColors[i]);

            list.add(item);
        }

        adapter = new MainAdapter(this,list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

       // initializeMediaPlayer();
        try{
            //you can change the path, here path is external directory(e.g. sdcard) /Music/maine.mp3
            //mp.setDataSource(R.raw.home);

           // mp.prepare();
            mp.start();
        }catch(Exception e){e.printStackTrace();}

        TextToSpeech ts= new TextToSpeech();
       // ts.initializeMediaPlayer(" खेती की उन्नत विधियां के लिए पहला कार्ड चुने | प्रमुख समस्याएं एवं निवारण के लिए दूसरा कार्ड चुने | सरकार की योजनाएं के लिए तीसरा कार्ड चुने | कृषि चैटबॉट के लिए चौथा कार्ड चुने");

        Log.v("version", Build.VERSION.SDK_INT + "");

        findViewById(R.id.progress).setVisibility(View.GONE);


    }

    @Override
    protected void onStop() {
        super.onStop();
        mp.release();
    }
}
