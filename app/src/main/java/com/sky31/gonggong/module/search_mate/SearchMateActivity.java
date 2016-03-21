package com.sky31.gonggong.module.search_mate;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.sky31.gonggong.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 16-3-18.
 */
public class SearchMateActivity extends Activity {
    @Bind(R.id.et_sid)
    EditText etSid;
    @Bind(R.id.til_sid)
    TextInputLayout tilSid;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.til_name)
    TextInputLayout tilName;
    @Bind(R.id.et_card)
    EditText etCard;
    @Bind(R.id.til_card)
    TextInputLayout tilCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_mate);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tilCard.setHint(getResources().getString(R.string.card));
        tilSid.setHint(getResources().getString(R.string.sid));
        tilName.setHint(getResources().getString(R.string.name));
    }
}
