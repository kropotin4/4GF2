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


public class TestsFragment extends Fragment implements View.OnClickListener {

    private int pageNumber;
    Button btn1, btn2;

    private OnFragmentInteractionListenerTests mListener;

    public TestsFragment() {}

    public static TestsFragment newInstance(int page) {
        TestsFragment fragment = new TestsFragment();
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
        View result = inflater.inflate(R.layout.fragment_tests, container, false);

        btn1 = (Button) result.findViewById(R.id.Tbtn1);
        btn2 = (Button) result.findViewById(R.id.Tbtn2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        return result;
    }

    static String getTitle(Context context, int position) {
        return "В разработке";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListenerTests) {
            mListener = (OnFragmentInteractionListenerTests) context;
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
        mListener.onFragmentInteractionTests(v);
    }

    public interface OnFragmentInteractionListenerTests {
        void onFragmentInteractionTests(View v);
    }
}
