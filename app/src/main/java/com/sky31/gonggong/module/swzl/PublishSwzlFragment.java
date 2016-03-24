package com.sky31.gonggong.module.swzl;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sky31.gonggong.R;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.LostAndFoundModel;
import com.sky31.gonggong.model.SwzlResModel;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PublishSwzlFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PublishSwzlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PublishSwzlFragment extends Fragment implements SwzlPublishView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Bind(R.id.swzl_thing_lost)
    com.gc.materialdesign.views.CheckBox thingLostCheckBox;
    @Bind(R.id.swzl_thing_get)
    com.gc.materialdesign.views.CheckBox thingGetCheckBox;




    @Bind(R.id.swzl_location)
    EditText locationText;
    @Bind(R.id.swzl_announcer)
    EditText announcerText;
    @Bind(R.id.swzl_bank_card)
    EditText bankcardText;
    @Bind(R.id.swzl_mobile)
    EditText mobileText;
    @Bind(R.id.swzl_description)
    EditText descriptionText;
    @Bind(R.id.swzl_thing_name)
    EditText thingNameText;
    @Bind(R.id.swzl_is_card)
    CheckBox cardConfirm;
    @Bind(R.id.swzl_submit)
    Button publishBtn;

    @Bind(R.id.swzl_bank_card_att)
    TextView bankCardAtt;
    @Bind(R.id.swzl_thing_name_att)
    TextView thingNameAtt;

    private LostAndFoundModel pubModel;
    private String action = Constants.Value.SWZL_SUBMIT_FOUND;
    private boolean isCard = false; // 默认值和初始值对应


    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PublishSwzlFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PublishSwzlFragment newInstance(String param1, String param2) {
        PublishSwzlFragment fragment = new PublishSwzlFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);




        return fragment;
    }

    public PublishSwzlFragment() {
        // Required empty public constructor
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
        View view =  inflater.inflate(R.layout.fragment_publish_swzl, container, false);
        ButterKnife.bind(this,view);

        //初始化model

        pubModel = new LostAndFoundModel();

        // setListener
        setListener();



        return view;

    }


    private void setListener(){

        //set RadioGroup Listener.





        //set CheckBox Listener

        thingGetCheckBox.setOncheckListener(new com.gc.materialdesign.views.CheckBox.OnCheckListener() {
            @Override
            public void onCheck(com.gc.materialdesign.views.CheckBox checkBox, boolean b) {
                if (b){
                    thingLostCheckBox.setChecked(false);
                    action = Constants.Value.SWZL_SUBMIT_FOUND;
                }

            }
        });
        thingLostCheckBox.setOncheckListener(new com.gc.materialdesign.views.CheckBox.OnCheckListener() {
            @Override
            public void onCheck(com.gc.materialdesign.views.CheckBox checkBox, boolean b) {
                if (b){
                    thingGetCheckBox.setChecked(true);
                    action = Constants.Value.SWZL_SUBMIT_LOST;
                }
            }
        });

        cardConfirm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isCard = isChecked;
                if (isChecked){
                    //当物品为银行卡时候，那么提供银行卡号输入选项。然后隐藏物品名字控件。
                    bankCardAtt.setVisibility(View.VISIBLE);
                    bankcardText.setVisibility(View.VISIBLE);

                    thingNameAtt.setVisibility(View.GONE);
                    thingNameText.setVisibility(View.GONE);
                }else {
                    bankCardAtt.setVisibility(View.GONE);
                    bankcardText.setVisibility(View.GONE);

                    thingNameAtt.setVisibility(View.VISIBLE);
                    thingNameText.setVisibility(View.VISIBLE);
                }
            }
        });

        //TODO：：： set publish btn  listener
        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isCard) {
                    if (TextUtils.isEmpty(bankcardText.getText()) || TextUtils.isEmpty(locationText.getText())
                            || TextUtils.isEmpty(descriptionText.getText()) || TextUtils.isEmpty(announcerText.getText())
                            || TextUtils.isEmpty(mobileText.getText())) {
                        Toast.makeText(getActivity(), "请把信息填写完整", Toast.LENGTH_SHORT).show();
                    } else {
                        pubModel.setThings_type("1");
                        pubModel.setBank_card(bankcardText.getText().toString());
                        pubModel.setAnnouncer(announcerText.getText().toString());
                        pubModel.setMobile(mobileText.getText().toString());
                        pubModel.setLocation(locationText.getText().toString());
                        pubModel.setDescription(descriptionText.getText().toString());
                        publishMethod(action);

                    }

                } else {
                    if (TextUtils.isEmpty(thingNameText.getText()) || TextUtils.isEmpty(locationText.getText())
                            || TextUtils.isEmpty(descriptionText.getText()) || TextUtils.isEmpty(announcerText.getText())
                            || TextUtils.isEmpty(mobileText.getText())) {
                        Toast.makeText(getActivity(), "请把信息填写完整", Toast.LENGTH_SHORT).show();
                    } else {
                        pubModel.setThings_type("0");
                        pubModel.setThings(thingNameText.getText().toString());
                        pubModel.setAnnouncer(announcerText.getText().toString());
                        pubModel.setMobile(mobileText.getText().toString());
                        pubModel.setLocation(locationText.getText().toString());
                        pubModel.setDescription(descriptionText.getText().toString());
                        publishMethod(action);
                    }
                }

            }
        });

    }

    private void publishMethod(String action) {
        SwzlPresenter presenter = new SwzlPresenter(this);
        int x= 5;
        if (action.equals(Constants.Value.SWZL_SUBMIT_FOUND)){
            x = 1;
        }else {
            x = 0;
        }
        presenter.publishSwzl(pubModel);
        presenter.getResModel(x);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
    public void publish(String code ,SwzlResModel model) {

        //回调方法。
        Toast.makeText(getActivity()," 发布成功！",Toast.LENGTH_SHORT).show();

        String showResult = "未知错误，请稍后再试！";
        switch (code)
        {
            case "0":
                showResult = "发布成功！";
                getActivity().finish();
                break;
            case "65535": showResult = "参数缺失，请检查提交数据是否完整！";
                break;
            case "1002":showResult = "服务器无响应，请稍后再试！";
                break;
            default:showResult = "未知错误！请稍后再试";
        }

        Log.d("11111111",code);


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
