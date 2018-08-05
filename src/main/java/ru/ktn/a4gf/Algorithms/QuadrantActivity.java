package ru.ktn.a4gf.Algorithms;

import android.content.Context;
import android.graphics.Typeface;
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
import android.widget.TextView;
import android.widget.Toast;

import ru.ktn.a4gf.R;
import ru.ktn.a4gf.Solver;

public class QuadrantActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Log_quad";

    EditText editA, editP;
    Button btn;
    TextView textRes;
    Solver sol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadrant);

        sol = new Solver();

        editA = (EditText) findViewById(R.id.QeditA);
        editP = (EditText) findViewById(R.id.QeditP);
        btn = (Button) findViewById(R.id.QbtnSolve);
        textRes = (TextView) findViewById(R.id.QtextAll);

        textRes.setTypeface(Typeface.MONOSPACE);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.QbtnSolve){
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

            sol.strb.append("<br>-------------NEW---TASK--------------<br>");
            sol.strb.append("x<sup><small>2</sup></small> ≡ " + a + " (mod " + p + ")<br>");

            res = sol.quadrant_view(a, p);


            if(res != 0){
                if(sol.to_pow_mod(res, 2, p) == sol.to_mod(a, p)){
                    sol.strb.append("<br>x ≡ ±" + res + " (mod " + p + ")<br>");
                }
                else{
                    sol.strb.append("<br>No answer<br>");
                }

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
