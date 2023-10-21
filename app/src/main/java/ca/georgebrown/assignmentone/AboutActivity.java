package ca.georgebrown.assignmentone;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AboutActivity extends AppCompatActivity {
    // Student Name Casey Hsu, 101376814

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Add back button to toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Add functionality to back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Close activity
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
