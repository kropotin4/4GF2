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
import ru.ktn.a4gf.Solver;
import ru.ktn.a4gf.Solver2;

public class ElMultipleActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Log_ElMulte";

    EditText editX, editY, editN;
    EditText editA, editB, editP;
    Button btn;
    TextView textRes;
    Solver2 sol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_el_multiple);

        sol = new Solver2();

        editX = (EditText) findViewById(R.id.ElMulteditX);
        editY = (EditText) findViewById(R.id.ElMulteditY);
        editN = (EditText) findViewById(R.id.ElMulteditN);

        editA = (EditText) findViewById(R.id.ElMulteditA);
        editB = (EditText) findViewById(R.id.ElMulteditB);
        editP = (EditText) findViewById(R.id.ElMulteditP);

        btn = (Button) findViewById(R.id.ElMultbtnSolve);
        textRes = (TextView) findViewById(R.id.ElMulttextAll);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ElMultbtnSolve){
            Log.d(TAG, "Нажата кнопка Solve");
            if(TextUtils.isEmpty(editX.getText().toString()) || TextUtils.isEmpty(editY.getText().toString()) || TextUtils.isEmpty(editN.getText().toString())
                    || TextUtils.isEmpty(editA.getText().toString()) || TextUtils.isEmpty(editB.getText().toString()) || TextUtils.isEmpty(editP.getText().toString())) {
                Log.d(TAG, "Одно из полей ввода - пустое");
                Toast.makeText(this, "Не надо так!", Toast.LENGTH_SHORT).show();

                long mills = 300L;
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(mills);

                return;
            }
            Log.d(TAG, "Пошел процесс расчета");

            int x, y, N;
            int a, b, p;
            Pair<Integer, Integer> res;

            x = Integer.parseInt(editX.getText().toString());
            y = Integer.parseInt(editY.getText().toString());
            N = Integer.parseInt(editN.getText().toString());

            a = Integer.parseInt(editA.getText().toString());
            b = Integer.parseInt(editB.getText().toString());
            p = Integer.parseInt(editP.getText().toString());

            sol.strb.append("\n-------------------NEW---TASK--------------------\n");
            sol.strb.append("(" + x + ", " + y + ") * " + N + " = R (x2, y2)\n");
            res = sol.multiple_t(Pair.create(Integer.valueOf(x), Integer.valueOf(y)), N, a, b, p);


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
