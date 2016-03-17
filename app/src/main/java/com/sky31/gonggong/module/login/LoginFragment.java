package com.sky31.gonggong.module.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.sky31.gonggong.R;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.EcardModel;
import com.sky31.gonggong.model.StudentInfoModel;
import com.sky31.gonggong.module.ecard.EcardView;
import com.sky31.gonggong.module.main.ApiPresenter;
import com.sky31.gonggong.module.main.ApiView;
import com.sky31.gonggong.util.ACache;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sky31.gonggong.config.CommonFunction.errorToast;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements ApiView, LoginView, EcardView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.sid)
    EditText sid;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.btn_login)
    Button btnLogin;

    private AlertDialog dialogWait;
    private ACache aCache;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context context;
    private OnFragmentInteractionListener mListener;
    private PopupWindow popupWindowWait;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick(R.id.btn_login)
    void btnLogin(View view) {
        /*LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_wait, null);
        dialogWait = new AlertDialog.Builder(this.getActivity()).create();
        dialogWait.setView(dialogView);
        dialogWait.show();*/
        showPopupWindowWait(view);
        ApiPresenter apiPresenter = new ApiPresenter((LoginView) this);
        apiPresenter.login(sid.getText() + "", password.getText() + "");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        context = this.getActivity();
        aCache = ACache.get(context);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    @Override
    public void login(int code, StudentInfoModel studentInfoModel) {
        popupWindowWait.dismiss();
        if (code == 0) {
            ApiPresenter ecardPresenter = new ApiPresenter((EcardView) this);
            ecardPresenter.getBalance(aCache.getAsString(Constants.Key.SID),aCache.getAsString(Constants.Key.PASSWORD));
            Intent backIntent = new Intent();
            backIntent.putExtra("name", studentInfoModel.getData().getName());
            this.getActivity().setResult(Activity.RESULT_OK, backIntent);
            this.getActivity().finish();
            onDetach();
        } else {
            errorToast(this.getActivity(), code);
        }
    }

    private void showPopupWindowWait(View view) {
        //自定义布局
        View contentView = LayoutInflater.from(context).inflate(
                R.layout.popupwindow_wait, null);
        popupWindowWait = new PopupWindow(contentView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindowWait.setTouchable(true);

        popupWindowWait.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindowWait.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_default_black));

        // 设置好参数之后再show
        popupWindowWait.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    @Override
    public void getBalance(int code, @Nullable EcardModel ecardModel) {

    }

    @Override
    public Context getViewContext() {
        return context;
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
        void onFragmentInteraction(Uri uri);
    }
}
