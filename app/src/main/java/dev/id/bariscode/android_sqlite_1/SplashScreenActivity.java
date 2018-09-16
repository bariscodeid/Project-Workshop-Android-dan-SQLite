package dev.id.bariscode.android_sqlite_1;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler tahansplash = new Handler();
        tahansplash.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent pindahke_home = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(pindahke_home);
            }
        }, 5000);

    }
}
