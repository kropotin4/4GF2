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
import ru.ktn.a4gf.Solver3;

public class TDetermActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Log_TDeterm";

    EditText editM;
    Button btn;
    TextView textRes;
    RadioGroup rgroup;
    Solver3 sol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tdeterm);

        sol = new Solver3();

        editM = (EditText) findViewById(R.id.TDetermEditM);
        btn = (Button) findViewById(R.id.TDetermBtnSolve);
        textRes = (TextView) findViewById(R.id.TDetermTextAll);
        rgroup = (RadioGroup) findViewById(R.id.TDetermRgroup);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.TDetermBtnSolve){
            Log.d(TAG, "Нажата кнопка Solve");
            if(TextUtils.isEmpty(editM.getText().toString())) {
                Log.d(TAG, "Одно из полей ввода - пустое");
                Toast.makeText(this, "Не надо так!", Toast.LENGTH_SHORT).show();

                long mills = 300L;
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(mills);

                return;
            }
            Log.d(TAG, "Пошел процесс расчета");

            int m, res = 0;
            m = Integer.parseInt(editM.getText().toString());

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

            sol.strb.append("\n----------------------NEW---TASK-----------------------\n");
            switch (rgroup.getCheckedRadioButtonId()){
                case (R.id.TDetermRbtn1):
                    res = sol.DetermTests_view(m, 1); // критерий Вильнос
                    if(res == 0){
                        textRes.setText(sol.strb.append("m - простое"));
                    }
                    else{
                        textRes.setText(sol.strb.append("m - составное"));
                    }
                    break;
                case (R.id.TDetermRbtn2):
                    res = sol.DetermTests_view(m, 0); // тест Лукаса
                    if(res == 1){
                        textRes.setText(sol.strb.append("m - простое"));
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
