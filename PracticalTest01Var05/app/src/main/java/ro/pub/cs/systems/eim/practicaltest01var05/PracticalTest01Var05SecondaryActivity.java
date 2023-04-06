package ro.pub.cs.systems.eim.practicaltest01var05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var05SecondaryActivity extends AppCompatActivity {
    private EditText editText;
    private Button verifyButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_secondary);

        editText = findViewById(R.id.secondary_edit_text);
        verifyButton = findViewById(R.id.verify_button);
        cancelButton = findViewById(R.id.cancel_button);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.PRIMARY_TEXT)) {
            editText.setText(intent.getExtras().getString(Constants.PRIMARY_TEXT));
        }

        verifyButton.setOnClickListener(view -> {
            setResult(RESULT_OK, null);
            finish();
        });

        cancelButton.setOnClickListener(view -> {
            setResult(RESULT_CANCELED, null);
            finish();
        });
    }
}