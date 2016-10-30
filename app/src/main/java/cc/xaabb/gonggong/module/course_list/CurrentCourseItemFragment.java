package cc.xaabb.gonggong.module.course_list;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sky31.gonggong.R;
import cc.xaabb.gonggong.model.CourseListModel;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CurrentCourseItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentCourseItemFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    @Bind(R.id.current_course_name)
    TextView currentCourseName;
    @Bind(R.id.current_course_teacher)
    TextView currentCourseTeacher;
    @Bind(R.id.current_course_position)
    TextView currentCoursePosition;
    @Bind(R.id.current_course_cost)
    TextView currentCourseCost;

    // TODO: Rename and change types of parameters
    private CourseListModel.DataBean mParam1;



    public CurrentCourseItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param
     * @return A new instance of fragment CurrentCourseItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentCourseItemFragment newInstance(CourseListModel.DataBean dataBean) {
        CurrentCourseItemFragment fragment = new CurrentCourseItemFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, dataBean);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (CourseListModel.DataBean) getArguments().getSerializable(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_curren_course_item, container, false);

        ButterKnife.bind(this, view);

        initItemViews();
        return view;
    }


    /**
     * 初始化视图。
     */
    private void initItemViews() {
        currentCourseCost.setText(mParam1.getSection_start()+"-"+mParam1.getSection_end()+"节");
        currentCourseName.setText(mParam1.getCourse());
        currentCourseTeacher.setText(mParam1.getTeacher());
        currentCoursePosition.setText(mParam1.getLocation());
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
