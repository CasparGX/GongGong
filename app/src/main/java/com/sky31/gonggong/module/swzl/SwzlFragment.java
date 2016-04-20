package com.sky31.gonggong.module.swzl;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import com.rey.material.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gc.materialdesign.views.ButtonFloat;
import com.sky31.gonggong.R;
import com.sky31.gonggong.config.CommonFunction;
import com.sky31.gonggong.model.SwzlSearchResult;
import com.sky31.gonggong.widget.RefreshListView;

import javax.crypto.spec.DESKeySpec;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SwzlFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SwzlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SwzlFragment extends android.support.v4.app.Fragment implements SwzlSearchView ,Runnable{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private static int code;
    private String mParam2;

    private View mFragmentView = null;
    private SwzlSearchResult result;

    private static RefreshView refreshView;

    private OnFragmentInteractionListener mListener;

    @Bind(R.id.swzl_list_view)
    RefreshListView listView;

    @Bind(R.id.buttonFloat)
    FloatingActionButton publishBtn;

    @Bind(R.id.swzl_search)
    Button searchBtn;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param actionCode Parameter 1.
     *
     * @return A new instance of fragment SwzlFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SwzlFragment newInstance(int actionCode) {

        SwzlFragment fragment = new SwzlFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, actionCode);

        code = actionCode;

        fragment.setArguments(args);
        return fragment;
    }

    public SwzlFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            code = getArguments().getInt(ARG_PARAM1);
        }

        initData();

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mFragmentView = inflater.inflate(R.layout.fragment_swzl, container, false);
        ButterKnife.bind(this, mFragmentView);


        listView.initRunable(this);
        mListener.onFragmentInteraction(this);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("floatBtn", code + "");
                initData();

            }
        });
//        SwzlListviewAdapter adapter = new SwzlListviewAdapter(getActivity(),result);
//
//        listView.setAdapter(adapter);

        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),PublishSwzlActivity.class);
                startActivity(intent);
            }
        });

        return mFragmentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {
        if (mListener != null) {

            mListener.onFragmentInteraction(this);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {

            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    public void initData(){
        // 向服务器请求数据。
        SwzlSearchPresenter presenter = new SwzlSearchPresenter(this,getActivity());
        presenter.getSearchResult(code);

    }

    @Override
    public void getSearchData(int code ,SwzlSearchResult data) {
        // 回调借口。传入data
        switch (code){
            case 0:this.result = data;
                   setData();
                break;
            default:
                CommonFunction.errorToast(getActivity(),code);

        }

    }


    private void setData(){
        if (result.getData() != null){
            SwzlListviewAdapter adapter = new SwzlListviewAdapter(getActivity(),result.getData());
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            listView.dismissHeaderView();
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        Log.i("Fragment", "onResume->>" + code);


    }

    @Override
    public void run() {
        initData();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(SwzlFragment fragment);
    }

}
