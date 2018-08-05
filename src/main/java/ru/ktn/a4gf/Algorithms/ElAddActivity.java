package ru.ktn.a4gf.Algorithms;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ru.ktn.a4gf.R;
import ru.ktn.a4gf.Solver2;

public class ElAddActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Log_ElMulte";

    EditText editX1, editY1;
    EditText editX2, editY2;
    EditText editA, editB, editP;

    Button btn;
    TextView textRes;

    Solver2 sol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_el_add);

        sol = new Solver2();

        editX1 = (EditText) findViewById(R.id.ElAddeditX1);
        editY1 = (EditText) findViewById(R.id.ElAddeditY1);

        editX2 = (EditText) findViewById(R.id.ElAddeditX2);
        editY2 = (EditText) findViewById(R.id.ElAddeditY2);


        editA = (EditText) findViewById(R.id.ElAddeditA);
        editB = (EditText) findViewById(R.id.ElAddeditB);
        editP = (EditText) findViewById(R.id.ElAddeditP);

        btn = (Button) findViewById(R.id.ElAddbtnSolve);
        textRes = (TextView) findViewById(R.id.ElAddtextAll);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ElAddbtnSolve){
            Log.d(TAG, "Нажата кнопка Solve");
            if(TextUtils.isEmpty(editX1.getText().toString()) || TextUtils.isEmpty(editY1.getText().toString()) || TextUtils.isEmpty(editX2.getText().toString())  || TextUtils.isEmpty(editY2.getText().toString())
                    || TextUtils.isEmpty(editA.getText().toString()) || TextUtils.isEmpty(editB.getText().toString()) || TextUtils.isEmpty(editP.getText().toString())) {
                Log.d(TAG, "Одно из полей ввода - пустое");
                Toast.makeText(this, "Не надо так!", Toast.LENGTH_SHORT).show();

                long mills = 300L;
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(mills);

                return;
            }
            Log.d(TAG, "Пошел процесс расчета");

            int x1, y1, x2, y2;
            int a, b, p;
            Pair<Integer, Integer> res;

            x1 = Integer.parseInt(editX1.getText().toString());
            y1 = Integer.parseInt(editY1.getText().toString());
            x2 = Integer.parseInt(editX2.getText().toString());
            y2 = Integer.parseInt(editY2.getText().toString());


            a = Integer.parseInt(editA.getText().toString());
            b = Integer.parseInt(editB.getText().toString());
            p = Integer.parseInt(editP.getText().toString());

            sol.strb.append("\n-------------------NEW---TASK--------------------\n");
            sol.strb.append("(" + x1 + ", " + y1 + ") + " + "(" + x2 + ", " + y2 + ") = R (x3, y3)\n");
            res = sol.plus_t(Pair.create(Integer.valueOf(x1), Integer.valueOf(y1)), Pair.create(Integer.valueOf(x2), Integer.valueOf(y2)), a, b, p);


            if(res.first != -1){
                sol.strb.append("\n\nR = (" + res.first + ", " + res.second + ")\n");
            }
            else{
                sol.strb.append("\n\nNo answer!\n");
            }

            textRes.setText(sol.toString());

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(btn.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
