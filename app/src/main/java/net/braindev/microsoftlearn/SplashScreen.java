package net.braindev.microsoftlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //TextView textViewVersion = this.findViewById(R.id.textViewVersion);
        //textViewVersion.setText("Version "+ BuildConfig.VERSION_NAME);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                //LoadData();
                finishAffinity();
                startActivity(new Intent(SplashScreen.this, MainActivity .class));
            }
        }, 2000);
    }
}
