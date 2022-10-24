package com.example.employeesalarycalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button bt1,bt2;
    TextView txt;
    EditText ed1,ed2,ed3,ed4,ed5;
    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.txtView);
        AsyncClass asyncClass = new AsyncClass();
        bt1=findViewById(R.id.btnStart);
        bt2 = findViewById(R.id.btnStop);

        ed1 = findViewById(R.id.empname);
        ed2 = findViewById(R.id.empsalary);
        ed3 = findViewById(R.id.emptax);
        ed4 = findViewById(R.id.empnsal);
        ed5 = findViewById(R.id.empmsal);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncClass.doInBackground();
                asyncClass.onProgressUpdate();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setSelected(false);
                asyncClass.onPostExecute("AsyncTask Completed");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                empsal();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clear();
            }
        });
    }

    public void empsal(){

        int salary = Integer.parseInt(ed2.getText().toString());
        int tax,nsal;
        Double msal=null;

        if(salary<250000){
            tax = salary;
        }
        else if(salary>250000 && salary<500000){
            tax = (salary/12) * 5/100;
        }
        else if (salary>500000 && salary<1000000){
            tax = (salary/12) * 20/100;
        }
        else if (salary>1000000){
            tax = (salary/12) * 30/100;
        }
        else{
            tax = 0;
        }

        ed3.setText(String.valueOf(tax));

        nsal = salary - tax;

        ed4.setText(String.valueOf(nsal));

        msal = nsal/12.0;

        ed5.setText(String.valueOf(msal));

    }

    public void clear(){
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
        ed5.setText("");

        ed1.requestFocus();
    }

    private class AsyncClass extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Toast.makeText(getApplicationContext(),"Banner is Moving",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            txt.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            txt.setSelected(true);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
        }
    }
}