package com.hcmus.corrector;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TeacherActivity extends AppCompatActivity {
    private TextView studentText;
    private EditText teacherCorrection;
    private Button approveBtn, confirmCorrectBtn;
    private static final int RESPONSE_OK = 11;
    private static final int RESPONSE_WRONG = 22;
    private static final int TEACHER_RESPONSE_CODE = 33;
    private String studentAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        studentText = findViewById(R.id.teacherTitle);
        teacherCorrection = findViewById(R.id.teacherCorrection);
        approveBtn = findViewById(R.id.approveBtn);
        confirmCorrectBtn = findViewById(R.id.confirmCorrectBtn);

        Intent intent = getIntent();
        studentAnswer = intent.getStringExtra("studentAnswer");
        int requestCode = intent.getIntExtra("requestCode", -1);

        if (requestCode == 33) {
            studentText.setText("Câu trả lời: " + studentAnswer);
            teacherCorrection.setText(studentAnswer);
        }
        teacherCorrection.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean isSameAsStudent = s.toString().trim().equals(studentAnswer);
                confirmCorrectBtn.setEnabled(isSameAsStudent);
                approveBtn.setEnabled(!isSameAsStudent);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        confirmCorrectBtn.setOnClickListener(v -> confirmCorrect());
        approveBtn.setOnClickListener(v -> approveCorrection());
    }

    private void confirmCorrect() {
        Intent resultIntent = new Intent();
        String studentAnswer = studentText.getText().toString().replace("Câu trả lời: ", "");

        resultIntent.putExtra("correctedAnswer", studentAnswer);
        resultIntent.putExtra("responseCode", RESPONSE_OK);

        Toast.makeText(this, "✅ Xác nhận đúng!", Toast.LENGTH_SHORT).show();
        setResult(TEACHER_RESPONSE_CODE, resultIntent);
        finish();
    }

    private void approveCorrection() {
        String correctedAnswer = teacherCorrection.getText().toString().trim();
        Intent resultIntent = new Intent();

        if (!correctedAnswer.isEmpty()) {
            resultIntent.putExtra("correctedAnswer", correctedAnswer);

            if (correctedAnswer.equals(studentText.getText().toString().replace("Câu trả lời: ", ""))) {
                resultIntent.putExtra("responseCode", RESPONSE_OK);
                Toast.makeText(this, "Câu trả lời chính xác!", Toast.LENGTH_SHORT).show();
            } else {
                resultIntent.putExtra("responseCode", RESPONSE_WRONG);
                Toast.makeText(this, "Câu trả lời đã được sửa!", Toast.LENGTH_SHORT).show();
            }

            setResult(TEACHER_RESPONSE_CODE, resultIntent);

            finish();
        } else {
            Toast.makeText(this, "Vui lòng nhập câu sửa!", Toast.LENGTH_SHORT).show();
        }
    }

}
