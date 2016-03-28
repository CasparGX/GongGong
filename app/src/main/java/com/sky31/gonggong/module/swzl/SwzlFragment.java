package com.sky31.gonggong.module.swzl;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sky31.gonggong.R;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.SwzlSearchResult;
import com.sky31.gonggong.model.SwzlService;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SwzlFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SwzlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SwzlFragment extends android.support.v4.app.Fragment implements SwzlSearchView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int actionCode;
    private String mParam2;

    private View mFragmentView = null;
    private SwzlSearchResult result;

    private OnFragmentInteractionListener mListener;

    @Bind(R.id.swzl_list_view)
    ListView listView;


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
            actionCode = getArguments().getInt(ARG_PARAM1);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mFragmentView = inflater.inflate(R.layout.fragment_swzl, container, false);
        ButterKnife.bind(this, mFragmentView);

        initData();

        SwzlListviewAdapter adapter = new SwzlListviewAdapter(getActivity(),result);

        listView.setAdapter(adapter);


        return mFragmentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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


    private void initData(){
        // 向服务器请求数据。
        SwzlSearchPresenter presenter = new SwzlSearchPresenter(this,getActivity());
        presenter.getSearchResult(actionCode);

    }

    @Override
    public void getSearchData(SwzlSearchResult data) {
        // 回调借口。传入data
        this.result = data;
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
        public void onFragmentInteraction(Uri uri);
    }

}
