package cc.xaabb.gonggong.module.holiday;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky31.gonggong.R;
import cc.xaabb.gonggong.model.HolidayAllModel;
import cc.xaabb.gonggong.model.HolidayNextModel;
import cc.xaabb.gonggong.util.Debug;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HolidayFragment extends Fragment implements HolidayView {


    @Bind(R.id.rv_holiday)
    RecyclerView rvHoliday;

    private HolidayAllListAdapter rvHolidayAdapter;
    private List<HolidayAllModel.DataEntity> holidayData = new ArrayList<>();


    public HolidayFragment() {
        // Required empty public constructor
    }

    public static HolidayFragment newInstance(String param1, String param2) {
        HolidayFragment fragment = new HolidayFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void init() {
        rvHoliday.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHolidayAdapter = new HolidayAllListAdapter(getActivity(), null);
        rvHoliday.setAdapter(rvHolidayAdapter);
        getHoliday();
    }


    private void getHoliday() {
        HolidayPresenter holidayPresenter = new HolidayPresenter(this);
        holidayPresenter.getHolidayAll();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_holiday, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onGetHolidayNext() {

    }

    @Override
    public void finishGetHolidayNext(HolidayNextModel holidayNextModel) {

    }

    @Override
    public void onGetHolidayAll() {

    }

    @Override
    public void finishGetHolidayAll(HolidayAllModel holidayAllModel) {
        holidayData = holidayAllModel.getData();
        for (HolidayAllModel.DataEntity item : holidayData) {
            if (item.getInterval() < 0) {
                continue;
            }
            int position = rvHolidayAdapter.getData().size();
            rvHolidayAdapter.add(position, item);
            rvHolidayAdapter.notifyItemInserted(position);
            Debug.i("rvHoliday", position + "");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
