package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String usernameKey = "username";
        SharedPreferences userLog = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        if(!userLog.getString(usernameKey, "").equals("")) {
            Intent intent = new Intent(this, SecondScreen.class);
            intent.putExtra("username", userLog.getString(usernameKey, ""));
            startActivity(intent);
        }
        else {
            setContentView(R.layout.activity_main);
        }
    }

    public void loginClick(View view) {
        EditText username = (EditText) findViewById(R.id.username);
        String userStr = username.getText().toString();
        SharedPreferences userLog =
                getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        userLog.edit().putString("username", userStr).apply();

        secondScreen(userStr);
    }

    private void secondScreen(String username) {
        Intent intent = new Intent(this, SecondScreen.class);
        intent.putExtra("username", username);

        startActivity(intent);
    }

}
