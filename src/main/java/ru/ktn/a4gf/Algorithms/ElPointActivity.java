package ru.ktn.a4gf.Algorithms;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ru.ktn.a4gf.R;
import ru.ktn.a4gf.Solver2;

public class ElPointActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Log_ElPoint";

    EditText editA, editB, editP;
    Button btn;
    TextView textRes;
    Solver2 sol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_el_point);

        sol = new Solver2();

        editA = (EditText) findViewById(R.id.ElPointeditA);
        editB = (EditText) findViewById(R.id.ElPointeditB);
        editP = (EditText) findViewById(R.id.ElPointeditP);

        btn = (Button) findViewById(R.id.ElPointbtnSolve);
        textRes = (TextView) findViewById(R.id.ElPointtextAll);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ElPointbtnSolve){
            Log.d(TAG, "Нажата кнопка Solve");
            if(TextUtils.isEmpty(editA.getText().toString()) || TextUtils.isEmpty(editB.getText().toString()) || TextUtils.isEmpty(editP.getText().toString())) {
                Log.d(TAG, "Одно из полей ввода - пустое");
                Toast.makeText(this, "Не надо так!", Toast.LENGTH_SHORT).show();

                long mills = 300L;
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(mills);

                return;
            }
            Log.d(TAG, "Пошел процесс расчета");

            int a, b, p;
            int res;

            a = Integer.parseInt(editA.getText().toString());
            b = Integer.parseInt(editB.getText().toString());
            p = Integer.parseInt(editP.getText().toString());

            sol.strb.append("\n-------------------NEW---TASK--------------------\n");
            sol.strb.append("Все точки эллипса\n");
            sol.find_all_t(a, b, p);


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
