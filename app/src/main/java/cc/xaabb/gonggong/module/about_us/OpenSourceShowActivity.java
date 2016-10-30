package cc.xaabb.gonggong.module.about_us;

import android.os.Bundle;
import android.widget.TextView;

import com.sky31.gonggong.R;
import cc.xaabb.gonggong.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OpenSourceShowActivity extends BaseActivity {

    @Bind(R.id.open_source_textview)
    TextView openSourceTextview;

    private final static String BUTTER_KNIFE = "butterknife\n\n";
    private final static String BUTTER_KNIFE_LICENSE = "" +
            "Copyright 2013 Jake Wharton\n" +
            "\n" +
            "Licensed under the Apache License, Version 2.0 (the \"License\");\n\n\n" ;
    private final static String DILIVER = "=========================\n";
    private final static String RETROFIT = "Retrofit\n\n";
    private final static String RETROFIT_LICENSE ="Copyright 2013 Square, Inc.\n" +
            "\n" +
            "Licensed under the Apache License, Version 2.0 (the \"License\");\n\n\n";

    private final static String REY_MD_LICENSE = "Copyright 2015 Rey Pham.\n" +
            "\n" +
            "Licensed under the Apache License, Version 2.0 (the \"License\");\n\n\n";
    private final static String REY_MAIL = "Developed By\n Rey Pham - pea5137@gmail.com\n\n";
    private final static String REY_MD = "material design lib\n\n";

    private final static String PICASSO_LICENSE = "Copyright 2013 Square, Inc.\n" +
            "\n" +
            "Licensed under the Apache License, Version 2.0 (the \"License\");\n\n\n";
    private final static String PICASSO = "picasso\n\n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_source_show);
        ButterKnife.bind(this);
        openSourceTextview.append(DILIVER);
        openSourceTextview.append(BUTTER_KNIFE);
        openSourceTextview.append(BUTTER_KNIFE_LICENSE);
        openSourceTextview.append(DILIVER);
        openSourceTextview.append(PICASSO);
        openSourceTextview.append(PICASSO_LICENSE);
        openSourceTextview.append(DILIVER);
        openSourceTextview.append(RETROFIT);
        openSourceTextview.append(RETROFIT_LICENSE);
        openSourceTextview.append(DILIVER);
        openSourceTextview.append(REY_MD);
        openSourceTextview.append(REY_MAIL);
        openSourceTextview.append(REY_MD_LICENSE);



    }
}
