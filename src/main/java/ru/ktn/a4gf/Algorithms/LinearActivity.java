package ru.ktn.a4gf.Algorithms;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ru.ktn.a4gf.R;
import ru.ktn.a4gf.Solver;

public class LinearActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Log_lin";

    EditText editA, editB, editP;
    Button btn;
    TextView textRes;
    Solver sol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);

        sol = new Solver();

        editA = (EditText) findViewById(R.id.LineditA);
        editB = (EditText) findViewById(R.id.LineditB);
        editP = (EditText) findViewById(R.id.LineditP);
        btn = (Button) findViewById(R.id.LinbtnSolve);
        textRes = (TextView) findViewById(R.id.LintextAll);

        btn.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v.getId() == R.id.LinbtnSolve){
            Log.d(TAG, "Нажата кнопка Solve");
            if(TextUtils.isEmpty(editA.getText().toString()) || TextUtils.isEmpty(editP.getText().toString()) || TextUtils.isEmpty(editB.getText().toString())) {
                Log.d(TAG, "Одно из полей ввода - пустое");
                Toast.makeText(this, "Не надо так!", Toast.LENGTH_SHORT).show();

                long mills = 300L;
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(mills);

                return;
            }
            Log.d(TAG, "Пошел процесс расчета");

            int a, b, p, res = 0;
            a = Integer.parseInt(editA.getText().toString());
            b = Integer.parseInt(editB.getText().toString());
            p = Integer.parseInt(editP.getText().toString());

            sol.strb.append("\n-------------------NEW---TASK--------------------\n");
            sol.strb.append(a + "*x ≡ " + b + " (mod " + p + ")\n");
            res = sol.evklid_view(a, b, p);


            if(res != -1){
                sol.strb.append("\n\nx ≡ " + res + " (mod " + p + ")\n");
            }
            else{
                sol.strb.append("\n\nNo answer!\n");
            }

            textRes.setText(sol.toString());

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(btn.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_l, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        sol.strb.delete(0, sol.strb.length());
        textRes.setText(" ");

        return super.onOptionsItemSelected(item);
    }
}
