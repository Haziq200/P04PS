package sg.edu.rp.dmsd.p04ps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etNote;
    Button btnInsert;
    Button btnShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        etNote = findViewById(R.id.etRevisionNote);
        btnInsert = findViewById(R.id.buttonInsert);
        btnShowList = findViewById(R.id.buttonShowList);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RadioGroup rg = findViewById(R.id.rgStars);
                int selectedButtonId = rg.getCheckedRadioButtonId();
                RadioButton rb = findViewById(selectedButtonId);
                String strSelectButton = Integer.toString(selectedButtonId);


                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertNote(etNote.getText().toString(),strSelectButton);
                Toast.makeText(getBaseContext(),"INSERT",Toast.LENGTH_SHORT).show();
                db.close();
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper db = new DBHelper(MainActivity.this);

                ArrayList<String> data = db.getNoteContent();
                db.close();

                String txt = "";
                for(int i = 0;i < data.size();i++){
                    Log.d("Database Content",i + ". " + data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
            }
        });

    }
}
