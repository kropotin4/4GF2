package ru.ktn.a4gf.Algorithms;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ru.ktn.a4gf.R;
import ru.ktn.a4gf.Solver;

public class DegreeActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Log_Deg";

    EditText editA, editB, editP;
    Button btn;
    TextView textRes;
    Solver sol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degree);

        sol = new Solver();

        editA = (EditText) findViewById(R.id.DegeditA);
        editB = (EditText) findViewById(R.id.DegeditB);
        editP = (EditText) findViewById(R.id.DegeditP);
        btn = (Button) findViewById(R.id.DegbtnSolve);
        textRes = (TextView) findViewById(R.id.DegtextAll);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.DegbtnSolve){
            Log.d(TAG, "Нажата кнопка Solve");
            if(TextUtils.isEmpty(editA.getText().toString()) || TextUtils.isEmpty(editP.getText().toString())  || TextUtils.isEmpty(editB.getText().toString()) || Integer.parseInt(editP.getText().toString()) < 2) {
                Log.d(TAG, "Одно из полей ввода - пустое");
                Toast.makeText(this, "Не надо так!", Toast.LENGTH_SHORT).show();

                long mills = 300L;
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(mills);

                return;
            }
            Log.d(TAG, "Пошел процесс расчета");

            int a, b, p, res;
            a = Integer.parseInt(editA.getText().toString());
            b = Integer.parseInt(editB.getText().toString());
            p = Integer.parseInt(editP.getText().toString());


            res = sol.to_pow_mod(a, b, p);


            String print = "\n" + a + "<sup><small><small>" + b + "</small></small></sup> = " + res;
            textRes.setText(Html.fromHtml(print));
        }
    }

}
