package ru.ktn.a4gf.Algorithms;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import ru.ktn.a4gf.R;
import ru.ktn.a4gf.Solver;

public class LogarithmActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Log_log";

    EditText editA, editB, editP;
    RadioGroup rgroup;
    Button btn;
    TextView textRes;
    Solver sol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logarithm);

        sol = new Solver();

        editA = (EditText) findViewById(R.id.LogeditA);
        editB = (EditText) findViewById(R.id.LogeditB);
        editP = (EditText) findViewById(R.id.LogeditP);
        rgroup = (RadioGroup) findViewById(R.id.Logrgroup);
        btn = (Button) findViewById(R.id.LogbtnSolve);
        textRes = (TextView) findViewById(R.id.LogtextAll);

        btn.setOnClickListener(this);

        textRes.setText("Важно:\n\nАлгоритмы работают по-разному, т.е. возможны различные результаты, а также ситуации, когда один из них работает, а остальные нет.");
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.LogbtnSolve){
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

            sol.strb.append("<br>----------------------NEW---TASK-----------------------<br>");
            sol.strb.append(a + "<sup><small>x</sup></small> ≡ " + b + " (mod " + p + ")<br>");


            switch (rgroup.getCheckedRadioButtonId()){
                case (R.id.Logrbtn1):
                    res = sol.log_equation_view(a, b, p, 0);
                    break;
                case (R.id.Logrbtn2):
                    res = sol.log_equation_view(a, b, p, 1);
                    break;
                case (R.id.Logrbtn3):
                    res = sol.log_equation_view(a, b, p, 2);
                    break;
            }


            if(res != -1){
                if(sol.to_pow_mod(a, res, p) == sol.to_mod(b, p)) {
                    sol.strb.append("<br>x ≡ " + res + " (mod " + (p - 1) + ")<br>");
                }
                else{
                    sol.strb.append("<br>No answer<br>");
                }
            }
            else{
                sol.strb.append("<br>No answer<br>");
            }

            textRes.setText(Html.fromHtml(sol.toString()));

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
