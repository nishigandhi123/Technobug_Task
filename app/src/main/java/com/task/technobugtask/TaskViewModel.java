package com.task.technobugtask;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepo taskRepo;
    private LiveData<List<TaskData>> taskList;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskRepo = new TaskRepo(application);
        taskList = taskRepo.getAllData();
    }

    public void insert(TaskData taskData) {
        taskRepo.insertData(taskData);
    }
    public void update(TaskData taskData) {
        taskRepo.updateData(taskData);
    }
    public void delete(TaskData taskData) {
        taskRepo.deleteData(taskData);
    }

    public LiveData<List<TaskData>> getAllTask() {
        return taskList;
    }
}
