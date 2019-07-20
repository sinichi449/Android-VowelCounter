package com.sinichi.vowelcounter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtInputWords;
    private Button btnCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtInputWords = findViewById(R.id.edtInputWords);
        btnCount = findViewById(R.id.btnCount);

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidForm(edtInputWords)) {
                    String input = edtInputWords.getText().toString();
                    int c = countVowels(input);
                    String boldenResult = "<b>" + input + "</b>";
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("There is " + c + " vowels in the words " + "\"" + Html.fromHtml(boldenResult) + "\"" + ".").setNeutralButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    builder.show();
                } else {
                    edtInputWords.setError("You must type something to submit!");
                }
            }
        });
    }

    private static boolean checkVowels(char input) {
        if (input >= 'A' && input <= 'Z') {
           input = (char) (input + 'a' - 'A'); // converting to lower case
        }

        if (input == 'a' || input == 'e' || input == 'i' || input == 'u' || input == 'o') {
            return true;
        }
        return false;
    }

    private static int countVowels(String userInput) {
//        int count = 0;
//        int index = 0;
//        boolean isVowel;
//        char input;
//
//        // Looping to check the sentences whether there are vowels inside
//        do {
//            input = userInput.charAt(index); // check start from the first index of the words/
//            isVowel = checkVowels(input); // check whether a character is a vowel
//            if (isVowel) {
//                count++; // if vowel, then count as 1 point
//            }
//            index++; // and the index start to moving
//        } while (index != userInput.length()); // while index not reach the maximum length of the sentence/words yet, back to count again
//        return count;
        int counter = 0;
        boolean isVowel;
        char alphabet;

        for (int index = 0; index != userInput.length(); index++) {
            alphabet = userInput.charAt(index);
            isVowel = checkVowels(alphabet);
            if (isVowel) {
                counter++;
            }
        }
        return counter;
    }

    private boolean isValidForm(EditText editText) {
        if (TextUtils.isEmpty(editText.getText().toString())) {
            return false;
        }
        return true;
    }
}
