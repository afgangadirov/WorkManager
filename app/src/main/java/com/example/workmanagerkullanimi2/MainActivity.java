package com.example.workmanagerkullanimi2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.workmanagerkullanimi2.databinding.ActivityMainBinding;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding tasarim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasarim = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(tasarim.getRoot());

        tasarim.buttonDo.setOnClickListener(view -> {

            /*Constraints calismaKosulu = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();

            WorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class)
                    .setInitialDelay(7, TimeUnit.SECONDS)
                    .setConstraints(calismaKosulu)
                    .build();

            WorkManager.getInstance(this).enqueue(request);
*/

            WorkRequest request = new PeriodicWorkRequest.Builder(MyWorkerBildirim.class,15,TimeUnit.MINUTES)
                    .setInitialDelay(7, TimeUnit.SECONDS)
                    .build();

            WorkManager.getInstance(this).enqueue(request);


            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId())
                    .observe(this,workInfo -> {
                        String status = workInfo.getState().name();
                        Log.e("Background job status",status);
                    });

        });




    }
}