package com.asldaw.workmanagerjava;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class RefreshDatabase extends Worker {

    Context myContext;


    public RefreshDatabase(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.myContext = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Data data = getInputData();
        int myNumber = data.getInt("intKey", 0);
        refreshDatabase(myNumber);

        return Result.success();
    }

    private void refreshDatabase(int myNumber) {
        SharedPreferences sharedPreferences = myContext.getSharedPreferences("com.asldaw.workmanagerjava", Context.MODE_PRIVATE);
        int mySaveNumber = sharedPreferences.getInt("myNumber", 0);
        mySaveNumber = mySaveNumber + myNumber;
        System.out.println(mySaveNumber);
        sharedPreferences.edit().putInt("myNumber", mySaveNumber).apply();
    }

}
