package ru.ktn.a4gf.Algorithms;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

import ru.ktn.a4gf.R;
import ru.ktn.a4gf.Solver;

public class SystemActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout.LayoutParams lpLin;
    ViewGroup.LayoutParams lpView;
    LinearLayout llmain;

    EditText editC[];
    EditText editM[];

    EditText editCount;

    Button btnCount;
    Button btnSolve;

    TextView textRes;

    Solver sol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);


        lpLin = new  LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpView = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        sol = new Solver();

        editCount = (EditText) findViewById(R.id.SyseditCount);
        btnCount = (Button) findViewById(R.id.SysbtnCount);
        llmain = (LinearLayout) findViewById(R.id.Sysllmain);

        btnCount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //region Обработка создания полей
        if(v.getId() == R.id.SysbtnCount){
            if(TextUtils.isEmpty(editCount.getText().toString())){
                Toast.makeText(this, "Не надо так!", Toast.LENGTH_SHORT).show();

                long mills = 300L;
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(mills);

                return;
            }
            int count = Integer.parseInt(editCount.getText().toString());
            if(count <= 0){
                Toast.makeText(this, "Не надо так!", Toast.LENGTH_SHORT).show();

                long mills = 300L;
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(mills);

                return;
            }

            editC = new EditText[count];
            editM = new EditText[count];

            //region Создание полей
            for(int i = 0; i < count; ++i){
                LinearLayout llBasic = new LinearLayout(this);
                llBasic.setLayoutParams(lpLin);
                llBasic.setOrientation(LinearLayout.HORIZONTAL);

                TextView text1 = new TextView(this);
                TextView text2 = new TextView(this);

                String str = "x ≡ ";
                text1.setText(str);
                str = " mod ";
                text2.setText(str);

                editC[i] = new EditText(this);
                editM[i] = new EditText(this);

                str = "C" + (i + 1);
                editC[i].setHint(str);
                str = "m" + (i + 1);
                editM[i].setHint(str);

                editC[i].setInputType(InputType.TYPE_CLASS_NUMBER);
                editM[i].setInputType(InputType.TYPE_CLASS_NUMBER);

                editC[i].setEms(5);
                editM[i].setEms(5);

                llBasic.addView(text1, lpView);
                llBasic.addView(editC[i], lpView);
                llBasic.addView(text2, lpView);
                llBasic.addView(editM[i], lpView);

                llmain.addView(llBasic);
            }
//endregion
            //region Создание кнопки Solve
            btnSolve = new Button(this);
            btnSolve.setText("Solve");
            btnSolve.setId(R.id.solve_id);

            btnSolve.setOnClickListener(this);

            llmain.addView(btnSolve, lpView);

            btnCount.setEnabled(false);
    //endregion

            textRes = new TextView(this);
            textRes.setTextIsSelectable(true);
            llmain.addView(textRes, lpView);

        }
        //endregion

        if(v.getId() == R.id.solve_id){
            for(int i = 0; i < editC.length; ++i){
                if(TextUtils.isEmpty(editC[i].getText().toString()) || TextUtils.isEmpty(editM[i].getText().toString())){
                    Toast.makeText(this, "Не надо так!", Toast.LENGTH_SHORT).show();

                    long mills = 300L;
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(mills);

                    return;
                }
            }

            Vector<Pair<Integer, Integer>> vec = new Vector<>(editC.length);
            for(int i = 0; i < editC.length; ++i){
                Pair<Integer, Integer> p = new Pair<>(Integer.valueOf(editC[i].getText().toString()), Integer.valueOf(editM[i].getText().toString()));
                vec.add(p);
            }

            long M = 1;
            for(int i = 0; i < vec.size(); ++i){
                M *= vec.get(i).second.intValue();
            }

            sol.strb.append("\n-------------------NEW---TASK--------------------\n");

            int res = sol.system_China_view(vec);
            sol.strb.append("\n\nx ≡ " + res + " (mod " + M + ")");

            textRes.setText(sol.strb.toString());

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(btnSolve.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
