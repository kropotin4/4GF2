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

public class ElOrderActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Log_ElOrd";

    EditText editX, editY;
    EditText editA, editB, editP;
    Button btn;
    TextView textRes;
    Solver2 sol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_el_order);

        sol = new Solver2();

        editX = (EditText) findViewById(R.id.ElOrdeditX);
        editY = (EditText) findViewById(R.id.ElOrdeditY);

        editA = (EditText) findViewById(R.id.ElOrdeditA);
        editB = (EditText) findViewById(R.id.ElOrdeditB);
        editP = (EditText) findViewById(R.id.ElOrdeditP);

        btn = (Button) findViewById(R.id.ElOrdbtnSolve);
        textRes = (TextView) findViewById(R.id.ElOrdtextAll);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ElOrdbtnSolve){
            Log.d(TAG, "Нажата кнопка Solve");
            if(TextUtils.isEmpty(editX.getText().toString()) || TextUtils.isEmpty(editY.getText().toString())
                    || TextUtils.isEmpty(editA.getText().toString()) || TextUtils.isEmpty(editB.getText().toString()) || TextUtils.isEmpty(editP.getText().toString())) {
                Log.d(TAG, "Одно из полей ввода - пустое");
                Toast.makeText(this, "Не надо так!", Toast.LENGTH_SHORT).show();

                long mills = 300L;
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(mills);

                return;
            }
            Log.d(TAG, "Пошел процесс расчета");

            int x, y;
            int a, b, p;
            int res;

            x = Integer.parseInt(editX.getText().toString());
            y = Integer.parseInt(editY.getText().toString());

            a = Integer.parseInt(editA.getText().toString());
            b = Integer.parseInt(editB.getText().toString());
            p = Integer.parseInt(editP.getText().toString());

            sol.strb.append("\n-------------------NEW---TASK--------------------\n");
            sol.strb.append("Порядок M(" + x + ", " + y + ") = n\n");
            res = sol.order_t_view(Pair.create(Integer.valueOf(x), Integer.valueOf(y)), a, b, p);


            if(res != -1){
                sol.strb.append("\n\nn = " + res + "\n");
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
