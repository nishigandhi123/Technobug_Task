package com.task.technobugtask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    public void insert(TaskData taskData);

    @Update
    public void update(TaskData taskData);

    @Delete
    public void delete(TaskData taskData);

    @Query("SELECT * FROM my_task")
    public LiveData<List<TaskData>> getAllData();
}
