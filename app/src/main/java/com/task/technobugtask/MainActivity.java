package com.task.technobugtask;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.task.technobugtask.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private TaskViewModel taskViewModel;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        taskViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(TaskViewModel.class);

        binding.floatingActionButton.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, DataInsertActivity.class);
            intent.putExtra("type", "Add Task");
            startActivityForResult(intent, 1);

        });

        binding.recTaskList.setLayoutManager(new LinearLayoutManager(this));
        binding.recTaskList.setHasFixedSize(true);
        TaskAdapter adapter = new TaskAdapter();
        binding.recTaskList.setAdapter(adapter);

        taskViewModel.getAllTask().observe(this, taskData -> {

            adapter.submitList(taskData);

        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                if (direction == ItemTouchHelper.RIGHT) {

                    taskViewModel.delete(adapter.getTaskData(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "task deleted", Toast.LENGTH_SHORT).show();

                } else {

                    Intent intent = new Intent(MainActivity.this, DataInsertActivity.class);
                    intent.putExtra("type", "update");
                    intent.putExtra("title", adapter.getTaskData(viewHolder.getAdapterPosition()).getTitle());
                    intent.putExtra("description", adapter.getTaskData(viewHolder.getAdapterPosition()).getDescription());
                    intent.putExtra("id", adapter.getTaskData(viewHolder.getAdapterPosition()).getId());
                    startActivityForResult(intent, 2);
                }

            }
        }).attachToRecyclerView(binding.recTaskList);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {

            String title = data.getStringExtra("title");
            String desc = data.getStringExtra("description");
            TaskData taskData = new TaskData(title, desc);
            taskViewModel.insert(taskData);
            Toast.makeText(this, "task added", Toast.LENGTH_SHORT).show();

        } else if (requestCode == 2) {

            String title = data.getStringExtra("title");
            String desc = data.getStringExtra("description");
            TaskData taskData = new TaskData(title, desc);
            taskData.setId(data.getIntExtra("id", 0));
            taskViewModel.update(taskData);
            Toast.makeText(this, "task updated", Toast.LENGTH_SHORT).show();
        }
    }
}