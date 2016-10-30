package cc.xaabb.gonggong.module.article.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sky31.gonggong.R;
import cc.xaabb.gonggong.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
public class ArticleListActivity extends BaseActivity {


    @Bind(R.id.article_title_underline)
    ImageView articleTitleUnderline;
    @Bind(R.id.article_viewpager)
    ViewPager articleViewpager;
    @Bind(R.id.article_list_toolbar)
    Toolbar articleListToolbar;
    @Bind(R.id.article_list_calender)
    TextView articleListCalender;
    @Bind(R.id.article_list_info)
    TextView articleListInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        ButterKnife.bind(this);
        initViewPager();
        initToolBar();
    }


    /***
     * 加载viewpager
     */
    private void initViewPager() {

        List<Fragment> fragmentList = new ArrayList<>();
        ArticleListActivityFragment calendarFragment = new ArticleListActivityFragment("活动日历");
        ArticleListCommonFragment infoFragment = ArticleListCommonFragment.newInstance("学习宝典");

        fragmentList.add(calendarFragment);
        fragmentList.add(infoFragment);

        ArticleViewPagerAdapter adapter = new ArticleViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        articleViewpager.setAdapter(adapter);

        articleViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset > 0 && positionOffset < 1) {
                    articleTitleUnderline.setTranslationX(positionOffsetPixels / 2);
                    //Log.d("offset Pix", positionOffset + "");
                }
            }

            @Override
            public void onPageSelected(int position) {
                //设置顶部标题，根据当前滑动来设置可用性。
                if (position == 0) {
                    articleListInfo.setClickable(true);
                    articleListCalender.setClickable(false);
                } else if (position == 1) {
                    articleListInfo.setClickable(false);
                    articleListCalender.setClickable(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        articleListInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articleViewpager.setCurrentItem(1,true);
            }
        });

        articleListCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articleViewpager.setCurrentItem(0,true);
            }
        });

    }

    /***
     * 加载toolbar
     */
    private void initToolBar() {

        setSupportActionBar(articleListToolbar);
        articleListToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


}
