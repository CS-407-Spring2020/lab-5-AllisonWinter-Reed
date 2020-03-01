package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class SecondScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        TextView welcome = findViewById(R.id.welcomeText);
        Intent fromMain = getIntent();
        String username = fromMain.getStringExtra("username");
        welcome.setText("Welcome " + username + "!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.second_screen_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNote:
                return true;
            case R.id.logout:
                Intent intent = new Intent(this, MainActivity.class);
                SharedPreferences userLog = getSharedPreferences("c.sakshi.lab5", MODE_PRIVATE);
                userLog.edit().remove("username").apply();
                startActivity(intent);
                return true;
        }
        return false;
    }
}
