package ru.ktn.a4gf;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ru.ktn.a4gf.Algorithms.DegreeActivity;
import ru.ktn.a4gf.Algorithms.JakobiActivity;
import ru.ktn.a4gf.Algorithms.LegandreActivity;
import ru.ktn.a4gf.Algorithms.LinearActivity;
import ru.ktn.a4gf.Algorithms.LogarithmActivity;
import ru.ktn.a4gf.Algorithms.QuadrantActivity;
import ru.ktn.a4gf.Algorithms.SystemActivity;


public class CompareFragment extends Fragment implements View.OnClickListener{

    private int pageNumber;

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;

    private OnFragmentInteractionListenerCompare mListener;

    public CompareFragment() {}


    public static CompareFragment newInstance(int page) {
        CompareFragment fragment = new CompareFragment();
        Bundle args=new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            pageNumber = getArguments().getInt("num");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View result = inflater.inflate(R.layout.fragment_compare, container, false);

        btn1 = (Button) result.findViewById(R.id.btn1);
        btn2 = (Button) result.findViewById(R.id.btn2);
        btn3 = (Button) result.findViewById(R.id.btn3);
        btn4 = (Button) result.findViewById(R.id.btn4);
        btn5 = (Button) result.findViewById(R.id.btn5);
        btn6 = (Button) result.findViewById(R.id.btn6);
        btn7 = (Button) result.findViewById(R.id.btn7);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);

        return result;
    }

    static String getTitle(Context context, int position) {
        return "Сравнения";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListenerCompare) {
            mListener = (OnFragmentInteractionListenerCompare) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        mListener.onFragmentInteractionCompare(v);
    }

    public interface OnFragmentInteractionListenerCompare {
        void onFragmentInteractionCompare(View v);
    }
}
