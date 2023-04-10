package com.task.technobugtask;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.task.technobugtask.databinding.TaskListRowBinding;

import java.util.List;

public class TaskAdapter extends ListAdapter<TaskData, TaskAdapter.ViewHolder> {

    private Context context;
    private List<TaskData> taskDataList;

    public TaskAdapter() {

        super(CALLBACK);

    }

    private static final DiffUtil.ItemCallback<TaskData> CALLBACK = new DiffUtil.ItemCallback<TaskData>() {
        @Override
        public boolean areItemsTheSame(@NonNull TaskData oldItem, @NonNull TaskData newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull TaskData oldItem, @NonNull TaskData newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getDescription().equals(newItem.getDescription());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TaskData taskData = getItem(position);
        holder.binding.title.setText(taskData.getTitle());
        holder.binding.description.setText(taskData.getDescription());

        boolean isChecked = holder.binding.checkBox.isChecked();

        if (isChecked) {

            
        }

    }

    public TaskData getTaskData(int position) {

        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TaskListRowBinding binding;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = TaskListRowBinding.bind(itemView);
        }
    }
}
