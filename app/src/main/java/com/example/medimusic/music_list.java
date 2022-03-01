package com.example.medimusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class music_list extends AppCompatActivity {

    ImageView down_arrow;
    CircleImageView civ1, civ2, civ3;

    enum PLACEHOLDER_STATE {
        SELECTED_PLAY,
        SELECTED_STOP,
        UNSELECTED_PLAY,
        UNSELECTED_STOP
    }

    //declare circular imageview
    int placeholder1, placeholder2, placeholder3;

    Map<Integer, Enum> map = new HashMap<>();

    private SoundPool soundPool;

    private int sound1;
    private int sound2;
    private int sound3;

    Map<Integer, Boolean> playList = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list_dialog);

        civ1 = findViewById(R.id.placeholder1);
        civ2 = findViewById(R.id.placeholder2);
        civ3 = findViewById(R.id.placeholder3);

        map.put(R.id.placeholder1, PLACEHOLDER_STATE.SELECTED_STOP);
        map.put(R.id.placeholder2, PLACEHOLDER_STATE.UNSELECTED_STOP);
        map.put(R.id.placeholder3, PLACEHOLDER_STATE.UNSELECTED_STOP);

        Log.w("", "Test create");

        civ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("hhhhhhh", "Test");

                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) civ1.getLayoutParams();

                Enum status = map.get(R.id.placeholder1);

                if (status.equals(PLACEHOLDER_STATE.SELECTED_STOP)) {
                    map.put(R.id.placeholder1, PLACEHOLDER_STATE.SELECTED_PLAY);
                    params.width = 100;
                    params.height = 100;
                    civ1.setLayoutParams(params);
                } else if (status.equals(PLACEHOLDER_STATE.SELECTED_PLAY)) {
                    map.put(R.id.placeholder1, PLACEHOLDER_STATE.SELECTED_STOP);
                    params.width = 80;
                    params.height = 80;
                }
                civ1.setLayoutParams(params);
                Toast.makeText(getApplicationContext(), "placeholder one clicked", Toast.LENGTH_SHORT).show();
            }
        });
        civ2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("hhhhhhh", "Test");
                Toast.makeText(getApplicationContext(), "circular view clicked", Toast.LENGTH_SHORT).show();

                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) civ2.getLayoutParams();
                params.width = 100;
                params.height = 100;
                civ2.setLayoutParams(params);
            }
        });
        civ3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("hhhhhhh", "Test");
                Toast.makeText(getApplicationContext(), "circular view clicked", Toast.LENGTH_SHORT).show();

                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) civ3.getLayoutParams();
                params.width = 100;
                params.height = 100;
                civ3.setLayoutParams(params);
            }
        });


        down_arrow = findViewById(R.id.down_arrow);

        down_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("", "Test arrow");
                Log.d("", "Test arrow");
                Toast.makeText(getApplicationContext(), "arrow", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder().setMaxStreams(3)
                    .setAudioAttributes(audioAttributes).build();
        } else {
            soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        }

        placeholder1 = soundPool.load(this, R.raw.christmas_snow, 1);
        placeholder2 = soundPool.load(this, R.raw.farm_animals, 1);
        placeholder3 = soundPool.load(this, R.raw.flipping_pages, 1);

        playList.put(R.id.btn1, false);
        playList.put(R.id.btn2, false);
        playList.put(R.id.btn3, false);
    }

    public void testClick(View view) {
        Log.w("", "Test button click ");
    }

    public void playSound(View view) {
        Log.d("Test", "Enter into playSound");
        Integer key = view.getId();
        Log.w("enter into play sound", String.valueOf(key));
        Integer soundId = getSoundTrack(key);

        TextView selectedButton = (TextView) view;
        String selectedButtonText = selectedButton.getText().toString();

        // set value to placeholder button
//        changes
        TextView selectedPlaceHolder = (TextView) findViewById(R.id.ps1);
        selectedPlaceHolder.setText(selectedButtonText);


        if (playList.get(key) == false) {
            Log.w("Ready for play ...", String.valueOf(sound1));
            setSoundTrackId(key, soundPool.play(soundId, 1, 1, 0, 3, 1));
            playList.put(key, true);

        } else {
            Log.w("Ready for Stop ...", String.valueOf(sound1));
            playList.put(key, false);
            soundPool.stop(getSoundTrackId(key));
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }

    private int getSoundTrack(int key) {
        if (key == R.id.btn1) {
            return placeholder1;
        } else if (key == R.id.btn2) {
            return placeholder2;
        } else {
            return placeholder3;
        }
    }

    private void setSoundTrackId(int key, int value) {
        if (key == R.id.btn1) {
            sound1 = value;
        } else if (key == R.id.btn2) {
            sound2 = value;
        } else if (key == R.id.btn3) {
            sound3 = value;
        }
    }

    private int getSoundTrackId(int key) {
        if (key == R.id.btn1) {
            return sound1;

        } else if (key == R.id.btn2) {
            return sound2;
        } else {
            return sound3;
        }
    }
}

