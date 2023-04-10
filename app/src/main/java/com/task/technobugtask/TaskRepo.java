package com.task.technobugtask;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepo {

    private TaskDao taskDao;
    private LiveData<List<TaskData>> taskList;

    public TaskRepo(Application application) {

        TaskDatabase taskDatabase = TaskDatabase.getInstance(application);
        taskDao=taskDatabase.taskDao();
        taskList = taskDao.getAllData();
    }

    public void insertData(TaskData taskData) {new InsertTask(taskDao).execute(taskData);}
    public void updateData(TaskData taskData) {new UpdateTask(taskDao).execute(taskData);}
    public void deleteData(TaskData taskData) {new DeleteTask(taskDao).execute(taskData);}
    public LiveData<List<TaskData>> getAllData() {
        return taskList;
    }

    private static class InsertTask extends AsyncTask<TaskData, Void, Void> {

        private TaskDao taskDao;

        public InsertTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(TaskData... taskData) {
            taskDao.insert(taskData[0]);
            return null;
        }
    }

    private static class UpdateTask extends AsyncTask<TaskData, Void, Void> {

        private TaskDao taskDao;

        public UpdateTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(TaskData... taskData) {
            taskDao.update(taskData[0]);
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<TaskData, Void, Void> {

        private TaskDao taskDao;

        public DeleteTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(TaskData... taskData) {
            taskDao.delete(taskData[0]);
            return null;
        }
    }
}
