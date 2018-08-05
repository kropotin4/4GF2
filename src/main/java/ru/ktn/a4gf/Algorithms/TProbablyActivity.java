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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import ru.ktn.a4gf.R;
import ru.ktn.a4gf.Solver;
import ru.ktn.a4gf.Solver3;

public class TProbablyActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Log_TProbably";

    EditText editM, editR;
    Button btn;
    TextView textRes;
    RadioGroup rgroup;
    Solver3 sol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tprobably);

        sol = new Solver3();

        editM = (EditText) findViewById(R.id.TProbablyEditM);
        editR = (EditText) findViewById(R.id.TProbablyEditR);
        btn = (Button) findViewById(R.id.TProbablyBtnSolve);
        textRes = (TextView) findViewById(R.id.TProbablyTextAll);
        rgroup = (RadioGroup) findViewById(R.id.TProbablyRgroup);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.TProbablyBtnSolve){
            Log.d(TAG, "Нажата кнопка Solve");
            if(TextUtils.isEmpty(editM.getText().toString()) || TextUtils.isEmpty(editR.getText().toString())) {
                Log.d(TAG, "Одно из полей ввода - пустое");
                Toast.makeText(this, "Не надо так!", Toast.LENGTH_SHORT).show();

                long mills = 300L;
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(mills);

                return;
            }
            Log.d(TAG, "Пошел процесс расчета");

            int m, r, res = 0;
            double P;
            m = Integer.parseInt(editM.getText().toString());
            r = Integer.parseInt(editR.getText().toString());

            ///region Проверка m и r
            if(m <= 0){
                Toast.makeText(this, "m должно быть > 0", Toast.LENGTH_SHORT).show();

                long mills = 300L;
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(mills);

                editM.setText("");
                return;
            }
            else if(m <= 3){
                Toast.makeText(this, "Сами догадайтесь", Toast.LENGTH_SHORT).show();

                long mills = 300L;
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(mills);

                editM.setText("");
                return;
            }

            if(r <= 0){
                Toast.makeText(this, "Ноль попыток?", Toast.LENGTH_SHORT).show();

                long mills = 300L;
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(mills);

                editM.setText("");
                return;
            }
            ///endregion

            sol.strb.append("\n----------------------NEW---TASK-----------------------\n");
            switch (rgroup.getCheckedRadioButtonId()){
                case (R.id.TProbablyRbtn1):
                    res = sol.ProbablyTests(m, r, 0);
                    if(res == 1){
                        P = (1 / (double)Solver.to_pow(2, r)) * 100;
                        textRes.setText(sol.strb.append("m - простое с вероятностью " + (100 - P)));
                    }
                    else{
                        textRes.setText(sol.strb.append("m - составное"));
                    }
                    break;
                case (R.id.TProbablyRbtn2):
                    res = sol.ProbablyTests(m, r, 1);
                    if(res == 1){
                        P = (1 / (double)Solver.to_pow(2, r)) * 100;
                        textRes.setText(sol.strb.append("m - простое с вероятностью " + (100 - P)));
                    }
                    else{
                        textRes.setText(sol.strb.append("m - составное"));
                    }
                    break;
                case (R.id.TProbablyRbtn3):
                    res = sol.ProbablyTests(m, r, 2);
                    if(res == 1){
                        P = (1 / (double)Solver.to_pow(4, r)) * 100;
                        textRes.setText(sol.strb.append("m - простое с вероятностью " + (100 - P)));
                    }
                    else{
                        textRes.setText(sol.strb.append("m - составное"));
                    }
                    break;
            }
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
