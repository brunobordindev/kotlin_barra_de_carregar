package br.com.tarefaassincrona;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.tarefaassincrona.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main );

        binding.btnIniciar.setOnClickListener(view -> {
            MyAsyncTask myAsyncTask = new MyAsyncTask();
            myAsyncTask.execute(10);


        });
    }

    /*
    1 -> Parâmetro a ser passado para a classe / pode ser Void também
    2 -> Tipo de valor que será utilizado para p progresso da tarefa
    3 -> Retorno após tarefa finalizada
     */

    class MyAsyncTask extends AsyncTask<Integer, Integer, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... integers) {

            int numero = integers[0];
            for (int i = 0; i < numero; i++){

                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Finalizado";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            binding.progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            binding.progressBar.setProgress(0);
            binding.progressBar.setVisibility(View.INVISIBLE);
        }



    }
}