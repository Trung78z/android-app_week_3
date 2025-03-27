package com.hcmus.corrector;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StudentActivity extends AppCompatActivity {
    private EditText studentInput;
    private Button sendToTeacherBtn;
    private TextView resultText;
    private Button btnClear;
    private static final int RESPONSE_OK = 11;
    private static final int RESPONSE_WRONG = 22;
    private static final int STUDENT_REQUEST_CODE = 99;
    private static final int TEACHER_RESPONSE_CODE = 33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        studentInput = findViewById(R.id.studentInput);
        sendToTeacherBtn = findViewById(R.id.sendToTeacherBtn);
        resultText = findViewById(R.id.resultText);
        btnClear = findViewById(R.id.btnClear);

        sendToTeacherBtn.setOnClickListener(v -> sendToTeacher());

        btnClear.setOnClickListener(v -> clearInput());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void clearInput() {
        studentInput.setText("");
        resultText.setText("");
        resultText.setVisibility(View.GONE);
        Toast.makeText(this, "Đã làm sạch dữ liệu", Toast.LENGTH_SHORT).show();
    }

    private void sendToTeacher() {
        String studentAnswer = studentInput.getText().toString().trim();
        if (studentAnswer.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập câu trả lời!", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("MainActivity", "Sending studentAnswer: " + studentAnswer);
            Intent intent = new Intent(StudentActivity.this, TeacherActivity.class);
            intent.putExtra("studentAnswer", studentAnswer);
            intent.putExtra("requestCode", 33);
            startActivityForResult(intent, STUDENT_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == STUDENT_REQUEST_CODE && resultCode == TEACHER_RESPONSE_CODE && data != null) {
            int responseCode = data.getIntExtra("responseCode", -1);
            String correctedAnswer = data.getStringExtra("correctedAnswer");

            if (correctedAnswer != null) {
                Log.d("MainActivity", "Received correctedAnswer: " + correctedAnswer + ", responseCode: " + responseCode);

                if (responseCode == RESPONSE_OK) {
                    resultText.setText("✅ Câu trả lời đúng!");
                } else if (responseCode == RESPONSE_WRONG) {
                    resultText.setText("❌ Sai!\nKết quả đúng: " + correctedAnswer);
                }
                resultText.setVisibility(View.VISIBLE);
            } else {
                Log.d("MainActivity", "No corrected answer received.");
            }
        }
    }

}
