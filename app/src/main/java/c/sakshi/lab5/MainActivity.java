package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginClick(View view) {
        EditText username = (EditText) findViewById(R.id.username);
        String userStr = username.getText().toString();

        secondScreen(userStr);
    }

    private void secondScreen(String username) {
        Intent intent = new Intent(this, SecondScreen.class);
        intent.putExtra("username", username);

        startActivity(intent);
    }

}
