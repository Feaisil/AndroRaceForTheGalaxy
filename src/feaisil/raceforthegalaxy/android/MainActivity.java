package feaisil.raceforthegalaxy.android;

import java.io.FileNotFoundException;

import feaisil.raceforthegalaxy.android.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void startGame( View view)
    {
    	Intent intent = new Intent(this, LocalGameActivity.class);
    	startActivity(intent);
    }
}
