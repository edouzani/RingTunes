package org.ucombinator.ringtunes;

import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class PassThroughActivity extends AppCompatActivity {
    private final int requestCode = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_through);

        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent returnIntent = null;
        if (requestCode == this.requestCode) {
            if (resultCode == RESULT_OK) {
                returnIntent = new Intent();
                Uri result = data.getData();
                returnIntent.putExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI, result);
            }
        }
        if (returnIntent == null) {
            setResult(resultCode);
        } else {
            setResult(resultCode, returnIntent);
        }
        finish();
    }
}
