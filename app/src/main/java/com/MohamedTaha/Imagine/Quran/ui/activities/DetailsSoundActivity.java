package com.MohamedTaha.Imagine.Quran.ui.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.helper.Utilities;
import com.MohamedTaha.Imagine.Quran.helper.util.PlayerConstants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.MohamedTaha.Imagine.Quran.service.MediaPlayerService.activeAudio;
import static com.MohamedTaha.Imagine.Quran.service.MediaPlayerService.transportControls;
import static com.MohamedTaha.Imagine.Quran.ui.activities.ListSoundReader.IsPlay;

public class DetailsSoundActivity extends AppCompatActivity {
    @BindView(R.id.btnPrevious)
    Button btnPrevious;
    private static Button btnPlay;
    private static Button btnPause;
    @BindView(R.id.btnNext)
    Button btnNext;
    private static TextView MainActivityNameSora;
    private static TextView MainActivityNameShekh;
    @BindView(R.id.MainActivity_TV_BufferDuration)
    TextView MainActivityTVBufferDuration;
    @BindView(R.id.MainActivity_TV_Duration)
    TextView MainActivityTVDuration;
    @BindView(R.id.MainActivity_SeekBar)
    ProgressBar MainActivitySeekBar;
    Utilities utilities;
    private static ImageView DetailsSoundActivity_IV_Picture_Shekh;
    public static boolean IS_OPEN = false;
    public static final String BROADCAST_FINISH_ACTIVITY = "com.example.FinishActivityBroadCast.finish.activity";
    public static  ProgressBar DetailsSoundActivity_loading_indicator;
    public static boolean isDetailsActivityTrue = false;

    private BroadcastReceiver finishActivity = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_sound_activity);
        ButterKnife.bind(this);
        utilities = new Utilities();
        IS_OPEN = true;
        registerReceiver(finishActivity, new IntentFilter(BROADCAST_FINISH_ACTIVITY));
        DetailsSoundActivity_loading_indicator = (ProgressBar)findViewById(R.id.DetailsSoundActivity_loading_indicator);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPause = (Button) findViewById(R.id.btnPause);
        MainActivityNameSora = (TextView) findViewById(R.id.MainActivity_Name_Sora);
        MainActivityNameShekh = (TextView) findViewById(R.id.MainActivity_Name_Shekh);
        DetailsSoundActivity_IV_Picture_Shekh = (ImageView) findViewById(R.id.DetailsSoundActivity_IV_Picture_Shekh);
        MainActivitySeekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isDetailsActivityTrue = true;
        PlayerConstants.PROGRESSER_HANDLER = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Integer i[] = (Integer[]) msg.obj;
                MainActivityTVBufferDuration.setText("" + utilities.milliSecondsToTimer(i[0]));
                MainActivityTVDuration.setText("" + utilities.milliSecondsToTimer(i[1]));
                MainActivitySeekBar.setProgress(i[2]);
            }
        };
        updateUI();
        Glide.with(getApplicationContext())
                .load(activeAudio.getUrl_image())
                .apply(new RequestOptions().placeholder(R.mipmap.logo).centerCrop())
                .into(DetailsSoundActivity_IV_Picture_Shekh);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(finishActivity);
        isDetailsActivityTrue = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isDetailsActivityTrue = false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.item_anim_no_thing, R.anim.item_anim_slide_from_bottom);
    }

    @OnClick({R.id.btnPrevious, R.id.btnPlay, R.id.btnPause, R.id.btnNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnPrevious:
                transportControls.skipToPrevious();
                IsPlay = true;
                updateUI();
                break;
            case R.id.btnPlay:
                transportControls.play();
                IsPlay = true;
                updateUI();
                break;
            case R.id.btnPause:
                transportControls.pause();
                IsPlay = false;
                updateUI();
                break;
            case R.id.btnNext:
                transportControls.skipToNext();
                IsPlay = true;
                updateUI();
                break;
        }
    }

    public static void updateUI() {
        if (!IsPlay) {
            btnPlay.setVisibility(View.VISIBLE);
            btnPause.setVisibility(View.GONE);
        } else {
            btnPlay.setVisibility(View.GONE);
            btnPause.setVisibility(View.VISIBLE);
        }
        MainActivityNameShekh.setText("" + activeAudio.getName_shekh());
        MainActivityNameSora.setText("" + activeAudio.getName_sora());
    }
}