package com.example.root.christmastree;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements GetLevelAsyncTask.OnFrameReceivedListener, SetLevelAsyncTask.OnSetLevelListener {

    public static  String PORT = "8080";
    public static  String HTTP = "http://localhost";

    int maxX;
    int maxY;
    @BindView(R.id.seekBar)
    SeekBar seekBar;

    @BindView(R.id.fillView)
    ImageView fill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        SharedPreferences sharedPrefs = getSharedPreferences(SettingsActivity.PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor ed;
        if(!sharedPrefs.contains("initialized")){
            ed = sharedPrefs.edit();

            //Indicate that the default shared prefs have been set
            ed.putBoolean("initialized", true);

            //Set some default shared pref
            ed.putString("myDefString", "wowsaBowsa");

            ed.commit();
        }
        else{

            SharedPreferences.Editor editor = getSharedPreferences(SettingsActivity.PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("port", PORT);
            editor.putString("http", HTTP);
            editor.apply();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        Display mdisp = getWindowManager().getDefaultDisplay();
        Point mdispSize = new Point();
        mdisp.getSize(mdispSize);
        maxX = mdispSize.x;
        maxY = mdispSize.y;
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                onSeekbarChanged(i)  ;         }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void onSeekbarChanged(int i) {

        sendFrame(i);
        Bitmap bitmap = Bitmap.createBitmap((int) getWindowManager()
                .getDefaultDisplay().getWidth(), (int) getWindowManager()
                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        fill.setImageBitmap(bitmap);

        // Rectangle

        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(10);
        float leftx = 0;
        float topy = maxY-(300+(i*15));
        float rightx = maxX;
        float bottomy = maxY;
        canvas.drawRect(leftx, topy, rightx, bottomy, paint);

    }

    private void sendFrame(int i) {
        SetLevelAsyncTask setLevelAsyncTask = new SetLevelAsyncTask(this,HTTP,PORT);
        setLevelAsyncTask.execute(new Frame(i));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }

        if(id == R.id.action_connect) {
            connect();
        }


        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences(SettingsActivity.PREFS_NAME, MODE_PRIVATE);
         PORT = prefs.getString("port", PORT);
         HTTP = prefs.getString("http", HTTP);

    }

    private void connect() {
        GetLevelAsyncTask getLevelAsyncTask = new GetLevelAsyncTask(this,HTTP,PORT);
        getLevelAsyncTask.execute();
    }

    @Override
    public void onFrameReceived(Integer level) {
        if (level == null){
            Toast.makeText(this, "Service doesn't answer.", Toast.LENGTH_LONG).show();
        seekBar.setProgress(0);
    }
        else{
            seekBar.setProgress(level);
        }
    }

    @Override
    public void onLevelSet(SendFrameResponse response) {
        if(response==null){

           // seekBar.setProgress(0);
        }
    }
}
