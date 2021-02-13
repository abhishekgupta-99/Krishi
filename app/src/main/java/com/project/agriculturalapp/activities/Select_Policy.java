package com.project.agriculturalapp.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import com.project.agriculturalapp.R;
import com.project.agriculturalapp.adapters.PolicyAdapter;
import com.project.agriculturalapp.utilities.TextToSpeech;

import java.util.ArrayList;


public class Select_Policy extends AppCompatActivity {

    private PolicyAdapter adapter;
    private  ArrayList list;
    private RecyclerView recyclerView;

    private String[] links;
    private String[] allcolors;
    String tts="";
    String[] hindi_color= {"नीला","पीला","नारंगी","लाल","सफेद","काला","गुलाबी","हरा","बैंगनी"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_policy);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        links=getResources().getStringArray(R.array.policies_link);
        allcolors=getResources().getStringArray(R.array.Colors);

        list = new ArrayList<>();

        list=new ArrayList();

        String[] array=getResources().getStringArray(R.array.policies);

        for(int i=0;i<array.length;i++){
            list.add(array[i]);
            tts=tts+array[i]+" के लिए " +hindi_color[i]+" कार्ड चुने | ";
        }

        Log.d("policies",tts);

        adapter = new PolicyAdapter(this,list,links, allcolors);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        TextToSpeech ts= new TextToSpeech();
        ts.initializeMediaPlayer(tts);


        findViewById(R.id.progress).setVisibility(View.GONE);
    }
}