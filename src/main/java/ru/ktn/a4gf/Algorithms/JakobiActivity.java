package ru.ktn.a4gf.Algorithms;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ru.ktn.a4gf.R;
import ru.ktn.a4gf.Solver;

public class JakobiActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Log_J";

    EditText editA, editP;
    Button btn;
    TextView textRes;
    Solver sol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jakobi);

        sol = new Solver();

        editA = (EditText) findViewById(R.id.JeditA);
        editP = (EditText) findViewById(R.id.JeditP);
        btn = (Button) findViewById(R.id.JbtnSolve);
        textRes = (TextView) findViewById(R.id.JtextAll);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.JbtnSolve){
            Log.d(TAG, "Нажата кнопка Solve");
            if(TextUtils.isEmpty(editA.getText().toString()) || TextUtils.isEmpty(editP.getText().toString())) {
                Log.d(TAG, "Одно из полей ввода - пустое");
                Toast.makeText(this, "Не надо так!", Toast.LENGTH_SHORT).show();

                long mills = 300L;
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(mills);

                return;
            }
            Log.d(TAG, "Пошел процесс расчета");

            int a, p, res = 0;
            a = Integer.parseInt(editA.getText().toString());
            p = Integer.parseInt(editP.getText().toString());

            if(p <= 2){
                Toast.makeText(this, "P маловато!", Toast.LENGTH_SHORT).show();

                long mills = 300L;
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(mills);

                editP.setText("");
                return;
            }
            else if (p % 2 == 0){
                Toast.makeText(this, "P должно быть нечетно!", Toast.LENGTH_SHORT).show();

                long mills = 300L;
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(mills);

                editP.setText("");
                return;
            }

            res = sol.symbol_J(a, p);


            if(res != 1 && res != -1){
                textRes.setText("Error");
                return;
            }
            String print = "\tJ(" + a + ", " + p + ") = " + res;
            textRes.setText(print);
        }
    }
}
