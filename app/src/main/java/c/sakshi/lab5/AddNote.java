package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNote extends AppCompatActivity {

    int noteid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        TextView noteText = findViewById(R.id.notesText);
        Intent receivedIntent = getIntent();
        noteid = receivedIntent.getIntExtra("noteid", -1);

        if (noteid != -1) {
            //Display content of not by retrieving "notes" ArrayList in SecondScreen
            Note note = SecondScreen.notes.get(noteid);
            String noteContent = note.getContent();
            noteText.setText(noteContent);
        }
    }

    public void saveClick(View view) {
         EditText noteText = findViewById(R.id.notesText);
         String noteContent = noteText.getText().toString();

         Context context = getApplicationContext();
         SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);

         DBHelper sqlHelp = new DBHelper(sqLiteDatabase);

         SharedPreferences userLog = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
         String username = userLog.getString("username", "");

         String title;
         DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy HH:mm:ss");
         String date = dateFormat.format(new Date());

         if(noteid == -1) {
             title = "NOTE_" + (SecondScreen.notes.size() + 1);
             sqlHelp.saveNotes(username, title, noteContent, date);
         } else {
             title = "NOTE_" + (noteid + 1);
             sqlHelp.updateNotes(title, date, noteContent, username);
         }

         Intent intent = new Intent(getApplicationContext(), SecondScreen.class);
         startActivity(intent);
    }
}
