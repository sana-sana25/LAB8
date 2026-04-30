package com.example.labthreadsasynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView txtStatus;
    private TextView txtPercent;
    private ProgressBar progressBar;
    private ImageView img;
    private Handler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtStatus  = findViewById(R.id.txtStatus);
        txtPercent = findViewById(R.id.txtPercent);
        progressBar = findViewById(R.id.progressBar);
        img = findViewById(R.id.img);

        Button btnLoadThread = findViewById(R.id.btnLoadThread);
        Button btnCalcAsync  = findViewById(R.id.btnCalcAsync);
        Button btnToast      = findViewById(R.id.btnToast);

        mainHandler = new Handler(Looper.getMainLooper());

        btnToast.setOnClickListener(v ->
                Toast.makeText(getApplicationContext(), "✅ UI réactive !", Toast.LENGTH_SHORT).show()
        );

        btnLoadThread.setOnClickListener(v -> loadImageWithThread());
        btnCalcAsync.setOnClickListener(v -> new HeavyCalcTask().execute());
    }

    // -----------------------------------------
    // PARTIE 1 : THREAD
    // -----------------------------------------
    private void loadImageWithThread() {

        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(0);
        txtPercent.setVisibility(View.VISIBLE);
        txtStatus.setText("Chargement image en cours...");

        new Thread(() -> {

            // Simuler chargement progressif
            for (int i = 0; i <= 100; i += 10) {
                final int progress = i;
                mainHandler.post(() -> {
                    progressBar.setProgress(progress);
                    txtPercent.setText(progress + "%");
                });
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Charger l'image
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.photo);

            mainHandler.post(() -> {
                img.setImageBitmap(bitmap);
                progressBar.setVisibility(View.INVISIBLE);
                txtPercent.setVisibility(View.INVISIBLE);
                txtStatus.setText("Image chargée avec succès ✅");
            });

        }).start();
    }

    // -----------------------------------------
    // PARTIE 2 : ASYNCTASK
    // -----------------------------------------
    private class HeavyCalcTask extends AsyncTask<Void, Integer, Long> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            txtPercent.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);
            txtPercent.setText("0%");
            txtStatus.setText("Calcul lourd en cours...");
        }

        @Override
        protected Long doInBackground(Void... voids) {
            long result = 0;
            for (int i = 1; i <= 100; i++) {
                for (int k = 0; k < 200000; k++) {
                    result += (i * k) % 7;
                }
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            txtPercent.setText(values[0] + "%");
        }

        @Override
        protected void onPostExecute(Long result) {
            progressBar.setVisibility(View.INVISIBLE);
            txtPercent.setVisibility(View.INVISIBLE);
            txtStatus.setText("Calcul terminé ✅  résultat = " + result);
        }
    }
}