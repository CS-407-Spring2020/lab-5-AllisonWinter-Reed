package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        //Display welcome
        TextView welcome = findViewById(R.id.welcomeText);
        Intent fromMain = getIntent();
        SharedPreferences userLog = getSharedPreferences("c.sakshi.lab5", MODE_PRIVATE);
        String username = userLog.getString("username", "");
        welcome.setText("Welcome " + username + "!");

        //Get SQLite Database instance
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);

        DBHelper sqlHelp = new DBHelper(sqLiteDatabase);

        notes = sqlHelp.readNotes(username);

        sqLiteDatabase.close();

        ArrayList<String> displayNotes = new ArrayList<>();
        for (Note note: notes) {
            displayNotes.add(String.format("Title:%s\nDate:%s", note.getTitle(), note.getDate()));
        }

        //Use listview to display notes on the screen
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = (ListView) findViewById(R.id.notesList);
        listView.setAdapter(adapter);

        //Add onItemClickListener for ListView item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Initialise intent to take user to third activity
                Intent intent = new Intent(getApplicationContext(), AddNote.class);
                //add the position of the item that was clicked on as noteid
                intent.putExtra("noteid", position);
                startActivity(intent);
            }
        });
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
                Intent intent1 = new Intent(this, AddNote.class);
                startActivity(intent1);
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

    public static ArrayList<Note> notes = new ArrayList<>();
}
