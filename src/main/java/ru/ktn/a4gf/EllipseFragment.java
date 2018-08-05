package ru.ktn.a4gf;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class EllipseFragment extends Fragment implements View.OnClickListener {

    private int pageNumber;
    Button btn1, btn2, btn3, btn4;

    private OnFragmentInteractionListenerEllipse mListener;

    public EllipseFragment() {}

    public static EllipseFragment newInstance(int page) {
        EllipseFragment fragment = new EllipseFragment();
        Bundle args = new Bundle();
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
        View result = inflater.inflate(R.layout.fragment_ellipse, container, false);

        btn1 = (Button) result.findViewById(R.id.Elbtn1);
        btn2 = (Button) result.findViewById(R.id.Elbtn2);
        btn3 = (Button) result.findViewById(R.id.Elbtn3);
        btn4 = (Button) result.findViewById(R.id.Elbtn4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        return result;
    }

    static String getTitle(Context context, int position) {
        return "Эллиптические кривые";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListenerEllipse) {
            mListener = (OnFragmentInteractionListenerEllipse) context;
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
        mListener.onFragmentInteractionEllipse(v);
    }

    public interface OnFragmentInteractionListenerEllipse {
        void onFragmentInteractionEllipse(View v);
    }
}
