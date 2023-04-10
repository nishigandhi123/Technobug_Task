package com.task.technobugtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.task.technobugtask.databinding.ActivityDataInsertBinding;

public class DataInsertActivity extends AppCompatActivity {

    ActivityDataInsertBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type = getIntent().getStringExtra("type");
        if (type.equals("update")) {

            setTitle("Update Task");
            binding.title.setText(getIntent().getStringExtra("title"));
            binding.desc.setText(getIntent().getStringExtra("description"));
            int id = getIntent().getIntExtra("id", 0);
            binding.add.setText("Update Task");
            binding.add.setOnClickListener(v -> {

                Intent intent = new Intent();
                intent.putExtra("title", binding.title.getText().toString());
                intent.putExtra("description", binding.desc.getText().toString());
                intent.putExtra("id", id);
                setResult(RESULT_OK, intent);
                finish();
            });

        } else {

            setTitle("Add Task");
            binding.add.setText("Add Task");
            binding.add.setOnClickListener(view -> {

                if (binding.title.getText().toString().trim().length() == 0 && binding.desc.getText().toString().trim().length() == 0) {

                    Toast.makeText(this, "Please enter data", Toast.LENGTH_SHORT).show();

                } else {

                    Intent intent = new Intent();
                    intent.putExtra("title", binding.title.getText().toString());
                    intent.putExtra("description", binding.desc.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }

            });
        }




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DataInsertActivity.this, MainActivity.class));
    }
}