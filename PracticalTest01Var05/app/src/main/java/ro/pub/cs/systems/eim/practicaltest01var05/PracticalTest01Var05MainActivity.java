package ro.pub.cs.systems.eim.practicaltest01var05;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {
    private Button topLeftButton;
    private Button topRightButton;
    private Button centerButton;
    private Button bottomLeftButton;
    private Button bottomRightButton;

    private Button navigateButton;

    private TextView textView;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private int numberOfClicks = 0;


    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String crtTextView = String.valueOf(textView.getText());
            if (!crtTextView.isEmpty()) {
                textView.setText(crtTextView + ", " + ((Button) view).getText());
            } else {
                textView.setText(((Button) view).getText());
            }
            numberOfClicks++;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_main);

        textView = findViewById(R.id.text_view);
        topLeftButton = findViewById(R.id.top_left_button);
        topRightButton = findViewById(R.id.top_right_button);
        centerButton = findViewById(R.id.center_button);
        bottomLeftButton = findViewById(R.id.bottom_left_button);
        bottomRightButton = findViewById(R.id.bottom_right_button);

        topLeftButton.setOnClickListener(buttonClickListener);
        topRightButton.setOnClickListener(buttonClickListener);
        centerButton.setOnClickListener(buttonClickListener);
        bottomLeftButton.setOnClickListener(buttonClickListener);
        bottomRightButton.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.NUMBER_OF_CLICKS)) {
                numberOfClicks = savedInstanceState.getInt(Constants.NUMBER_OF_CLICKS);
            }
        }

        Toast.makeText(this, "Number of clicks: " + String.valueOf(numberOfClicks), Toast.LENGTH_SHORT).show();


        navigateButton = findViewById(R.id.navigate_button);
        navigateButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05SecondaryActivity.class);
            intent.putExtra(Constants.PRIMARY_TEXT, String.valueOf(textView.getText()));
            startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.NUMBER_OF_CLICKS, numberOfClicks);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned: " + resultCode, Toast.LENGTH_LONG).show();
            textView.setText("");
            numberOfClicks = 0;
        }
    }
}