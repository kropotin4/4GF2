package ru.ktn.a4gf;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import ru.ktn.a4gf.Algorithms.DegreeActivity;
import ru.ktn.a4gf.Algorithms.ElAddActivity;
import ru.ktn.a4gf.Algorithms.ElMultipleActivity;
import ru.ktn.a4gf.Algorithms.ElOrderActivity;
import ru.ktn.a4gf.Algorithms.ElPointActivity;
import ru.ktn.a4gf.Algorithms.JakobiActivity;
import ru.ktn.a4gf.Algorithms.LegandreActivity;
import ru.ktn.a4gf.Algorithms.LinearActivity;
import ru.ktn.a4gf.Algorithms.LogarithmActivity;
import ru.ktn.a4gf.Algorithms.QuadrantActivity;
import ru.ktn.a4gf.Algorithms.SystemActivity;
import ru.ktn.a4gf.Algorithms.TDetermActivity;
import ru.ktn.a4gf.Algorithms.TProbablyActivity;
import ru.ktn.a4gf.CustomPageTransforms.AccordionTransformer;
import ru.ktn.a4gf.CustomPageTransforms.DefaultTransformer;
import ru.ktn.a4gf.CustomPageTransforms.RotateUpTransformer;
import ru.ktn.a4gf.CustomPageTransforms.ZoomOutSlideTransformer;
import ru.ktn.a4gf.CustomPageTransforms.ZoomOutTransformer;

public class MainActivity extends AppCompatActivity  implements
        CompareFragment.OnFragmentInteractionListenerCompare,
        EllipseFragment.OnFragmentInteractionListenerEllipse,
        TestsFragment.OnFragmentInteractionListenerTests{

    ViewPager pager;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new MyAdapter(this, getSupportFragmentManager()));
        pager.setCurrentItem(1);
        pager.setPageTransformer(true, new RotateUpTransformer());

       /* FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        CompareFragment compareFragment = CompareFragment.newInstance(0);
        ft.add(R.id.mainActivity, compareFragment);
        ft.commit();*/
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case (R.id.item_main):
                Toast.makeText(this, "Версия - 2.1\n\nСоздатели: \nКропотин (алгоритмы + изменения после переноса на Android),\nЗыков (Android версия - идея и первая реализация)", Toast.LENGTH_LONG).show();
                break;
            case (R.id.item_choose1):
                pager.setPageTransformer(true, new DefaultTransformer());
                item.setChecked(true);
                menu.findItem(R.id.item_choose2).setChecked(false);
                menu.findItem(R.id.item_choose3).setChecked(false);
                menu.findItem(R.id.item_choose4).setChecked(false);
                menu.findItem(R.id.item_choose5).setChecked(false);

                break;
            case (R.id.item_choose2):
                pager.setPageTransformer(true, new AccordionTransformer());
                item.setChecked(true);
                menu.findItem(R.id.item_choose1).setChecked(false);
                menu.findItem(R.id.item_choose3).setChecked(false);
                menu.findItem(R.id.item_choose4).setChecked(false);
                menu.findItem(R.id.item_choose5).setChecked(false);
                break;
            case (R.id.item_choose3):
                pager.setPageTransformer(true, new ZoomOutSlideTransformer());
                item.setChecked(true);
                menu.findItem(R.id.item_choose2).setChecked(false);
                menu.findItem(R.id.item_choose1).setChecked(false);
                menu.findItem(R.id.item_choose4).setChecked(false);
                menu.findItem(R.id.item_choose5).setChecked(false);
                break;
            case (R.id.item_choose4):
                pager.setPageTransformer(true, new ZoomOutTransformer());
                item.setChecked(true);
                menu.findItem(R.id.item_choose2).setChecked(false);
                menu.findItem(R.id.item_choose1).setChecked(false);
                menu.findItem(R.id.item_choose3).setChecked(false);
                menu.findItem(R.id.item_choose5).setChecked(false);
                break;
            case (R.id.item_choose5):
                pager.setPageTransformer(true, new RotateUpTransformer());
                item.setChecked(true);
                menu.findItem(R.id.item_choose2).setChecked(false);
                menu.findItem(R.id.item_choose1).setChecked(false);
                menu.findItem(R.id.item_choose4).setChecked(false);
                menu.findItem(R.id.item_choose3).setChecked(false);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteractionCompare(View v) {
        switch (v.getId()){
            case (R.id.btn1):
                Intent intL = new Intent(this, LegandreActivity.class);
                startActivity(intL);
                break;
            case (R.id.btn2):
                Intent intJ = new Intent(this, JakobiActivity.class);
                startActivity(intJ);
                break;
            case (R.id.btn3):
                Intent intQ = new Intent(this, QuadrantActivity.class);
                startActivity(intQ);
                break;
            case (R.id.btn4):
                Intent intLog = new Intent(this, LogarithmActivity.class);
                startActivity(intLog);
                break;
            case (R.id.btn5):
                Intent intLin = new Intent(this, LinearActivity.class);
                startActivity(intLin);
                break;
            case (R.id.btn6):
                Intent intSys = new Intent(this, SystemActivity.class);
                startActivity(intSys);
                break;
            case (R.id.btn7):
                Intent intDeg = new Intent(this, DegreeActivity.class);
                startActivity(intDeg);
                break;
        }
    }

    @Override
    public void onFragmentInteractionEllipse(View v) {
        switch (v.getId()){
            case (R.id.Elbtn1):
                Intent intMul = new Intent(this, ElMultipleActivity.class);
                startActivity(intMul);
                break;
            case (R.id.Elbtn2):
                Intent intAdd = new Intent(this, ElAddActivity.class);
                startActivity(intAdd);
                break;
            case(R.id.Elbtn3):
                Intent intOrd = new Intent(this, ElOrderActivity.class);
                startActivity(intOrd);
                break;
            case(R.id.Elbtn4):
                Intent intPoint = new Intent(this, ElPointActivity.class);
                startActivity(intPoint);
                break;
        }
    }

    @Override
    public void onFragmentInteractionTests(View v) {
        switch (v.getId()){
            case (R.id.Tbtn1):
                Intent intDeterm = new Intent(this, TDetermActivity.class);
                startActivity(intDeterm);
                break;
            case (R.id.Tbtn2):
                Intent intProbably = new Intent(this, TProbablyActivity.class);
                startActivity(intProbably);
                break;
        }
    }
}
