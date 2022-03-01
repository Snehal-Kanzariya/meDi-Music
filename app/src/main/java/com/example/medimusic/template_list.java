package com.example.medimusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class template_list extends AppCompatActivity {

    ImageView close_template;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_list);

        close_template = findViewById(R.id.close_template);

        close_template.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("dddd","Test arrow");
                Toast.makeText(getApplicationContext(),"close",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),template_list.class);
                startActivity(i);
            }
        });


    }
}