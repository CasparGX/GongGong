package com.sky31.gonggong.module.swzl.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.gc.materialdesign.widgets.ProgressDialog;
import com.sky31.gonggong.R;
import com.sky31.gonggong.base.BaseActivity;
import com.sky31.gonggong.config.CommonFunction;
import com.sky31.gonggong.model.LostAndFoundModel;
import com.sky31.gonggong.model.SwzlSearchResult;
import com.sky31.gonggong.module.swzl.SwzlDetailActivity;
import com.sky31.gonggong.module.swzl.SwzlSearchPresenter;
import com.sky31.gonggong.module.swzl.SwzlSearchView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SwzlSearchActivity extends BaseActivity implements SwzlSearchView {

    @Bind(R.id.swzl_search_edit_text)
    EditText swzlSearchEditText;
    @Bind(R.id.swzl_search_toolbar)
    Toolbar swzlSearchToolbar;
    @Bind(R.id.swzl_search_result)
    ListView swzlSearchResult;


    private ProgressDialog progressDialog;
    private SwzlSearchView searchView;
    private List<LostAndFoundModel> models;

    private String key;
    private CharSequence editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swzl_search);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this, "正在加载");
        searchView = this;
        initToolBar();

    }

    private void initToolBar() {

        //setSupportActionBar(swzlSearchToolbar);

        swzlSearchToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //加载toolbar 右侧菜单。

        swzlSearchToolbar.inflateMenu(R.menu.swzl_search_menu);

        //设置文字改变监听
        swzlSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().trim().length() > 0) {
                    initData();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        swzlSearchToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.swzl_search_by_key:
                        initData();

                        break;

                }
                return false;
            }
        });

    }


    /***
     * 加载数据请求。
     */
    void initData() {

        if (TextUtils.isEmpty(swzlSearchEditText.getText().toString().trim())) {
            //用户必须输入
            Toast.makeText(getBaseContext(), getResources().
                    getString(R.string.swzl_search_non_input), Toast.LENGTH_SHORT).
                    show();
        } else {
            key = swzlSearchEditText.getText().toString().trim();
            //key = key.replaceAll("(>)+","");
            SwzlSearchPresenter presenter = new SwzlSearchPresenter(searchView, getBaseContext());
            presenter.doSearchByKey(key);

        }

    }

    @Override
    public void getSearchData(int code, SwzlSearchResult data) {

        Log.d("Search_TAG", code + "");

        if (code == 0) {

            models = data.getData_2();
            if (models != null) {
                Log.d("Search_TAG", models.size() + "");
                initListViewDataAndView();
            }


        } else {
            CommonFunction.errorToast(getApplicationContext(), code);
        }

    }

    private void initListViewDataAndView() {

        SwzlSearchResultListAdapter adapter = new SwzlSearchResultListAdapter(getBaseContext(), models);

        swzlSearchResult.setAdapter(adapter);

        swzlSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                intent.putExtra("model", models.get(position));
                intent.setClass(SwzlSearchActivity.this, SwzlDetailActivity.class);
                startActivity(intent);

            }
        });

    }
}
