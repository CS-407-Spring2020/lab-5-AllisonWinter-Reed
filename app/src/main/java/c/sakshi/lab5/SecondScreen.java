package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
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

}
