package com.sky31.gonggong.module.search_mate;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.rey.material.widget.Button;
import com.rey.material.widget.CheckBox;
import com.rey.material.widget.EditText;
import com.rey.material.widget.SnackBar;
import com.sky31.gonggong.R;
import com.sky31.gonggong.base.BaseActivity;
import com.sky31.gonggong.model.MateInfoModel;
import com.sky31.gonggong.util.Debug;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sky31.gonggong.module.main.MainActivity.resources;

/**
 * Created by root on 16-3-18.
 */
public class SearchMateActivity extends BaseActivity implements SearchMateView {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.til_sid)
    EditText tilSid;
    @Bind(R.id.til_name)
    EditText tilName;
    @Bind(R.id.til_card)
    EditText tilCard;
    @Bind(R.id.check_benbu)
    CheckBox checkBenbu;
    @Bind(R.id.check_xingxiang)
    CheckBox checkXingxiang;
    @Bind(R.id.btn_search)
    Button btnSearch;
    @Bind(R.id.rv_searchmate)
    RecyclerView rvSearchmate;
    @Bind(R.id.searchmate_body)
    LinearLayout searchmateBody;


    private SearchMateListAdapter rvSearchmateAdapter;
    private List<MateInfoModel.DataEntity> mateinfoList = new ArrayList<>();

    @OnClick(R.id.btn_search)
    void onClickBtnSearch() {
        String sid = tilSid.getText().toString();
        String name = tilName.getText().toString();
        String card = tilCard.getText().toString();
        String type = null;
        if (checkBenbu.isChecked())
            type = "xtu";
        else
            type = "xx";
        Debug.i("searchMate", sid + " " + name + " " + card);
        //onGetSearchMate();
        SearchMatePresenter searchMatePresenter = new SearchMatePresenter(this);
        searchMatePresenter.getMateInfo(sid, name, card, type);

        //hiddenKeyboard(getApplicationContext());
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btnSearch.getWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_mate);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        //etName.getBackground().mutate().setColorFilter(getResources().getColor(R.color.textColorPrimary), PorterDuff.Mode.SRC_ATOP);
        tilCard.setHint(getResources().getString(R.string.card));
        tilSid.setHint(getResources().getString(R.string.sid));
        tilName.setHint(getResources().getString(R.string.name));
        initCheckBox();

        MateInfoModel.DataEntity rvHeaderItem = new MateInfoModel.DataEntity();
        rvHeaderItem.setName("姓名");
        rvHeaderItem.setSid("学号");
        rvHeaderItem.setClassX("班级");
        mateinfoList.add(rvHeaderItem);
        rvSearchmate.setLayoutManager(new LinearLayoutManager(this));
        rvSearchmateAdapter = new SearchMateListAdapter(this, mateinfoList);
        rvSearchmate.setAdapter(rvSearchmateAdapter);
    }

    private void initCheckBox() {
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBenbu.setChecked(checkBenbu == buttonView);
                    checkXingxiang.setChecked(checkXingxiang == buttonView);
                }
            }
        };

        checkBenbu.setOnCheckedChangeListener(listener);
        checkXingxiang.setOnCheckedChangeListener(listener);
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);//设置导航栏图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolbar.setTitle(title);//设置主标题
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    @Override
    public void onGetSearchMate() {
        btnSearch.setVisibility(View.GONE);
    }

    @Override
    public void finishGetSearchMate(MateInfoModel mateInfoModel) {
        if (mateInfoModel != null) {

            rvSearchmateAdapter.clearData();
            //mateinfoList.addAll(1,mateInfoModel.getData());
            mateinfoList = mateInfoModel.getData();
            for (MateInfoModel.DataEntity item : mateinfoList) {
                int position = rvSearchmateAdapter.getData().size();
                rvSearchmateAdapter.add(position, item);
                rvSearchmateAdapter.notifyItemInserted(position);
            }
            //rvSearchmateAdapter.notifyDataSetChanged();
        } else {
            SnackBar snackBar = new SnackBar(this);
            snackBar.applyStyle(R.style.SnackBarDefault);
            snackBar.make(this)
                    .text(R.string.no_result)
                    .textColor(resources.getColor(R.color.white))
                    .actionText("知道了")
                    .actionTextColor(resources.getColor(R.color.colorAccent))
                    .duration(3000)
                    .show(SearchMateActivity.this);
        }

        btnSearch.setVisibility(View.VISIBLE);
    }


}
