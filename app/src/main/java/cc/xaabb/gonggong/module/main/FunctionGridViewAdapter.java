package cc.xaabb.gonggong.module.main;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import cc.xaabb.gonggong.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import cc.xaabb.gonggong.config.CommonFunction;

/**
 * Created by wukunguang on 16-5-9.
 */
public class FunctionGridViewAdapter extends BaseAdapter {

    private Context context;

    public FunctionGridViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 9;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Log.d("gridView",position+"");
        ViewHolder viewHolder = null;
        if (convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.funtion_grid_view_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        ImageView imageView = viewHolder.functionItem;
        switch (position){
            case 0:
                imageView.setImageBitmap(CommonFunction.resToBitmap(context.getApplicationContext(), R.drawable.course_list_page));
                break;
            case 1:
                imageView.setImageBitmap(CommonFunction.resToBitmap(context.getApplicationContext(), R.drawable.grade_page));
                break;
            case 2:
                imageView.setImageBitmap(CommonFunction.resToBitmap(context.getApplicationContext(), R.drawable.library_page));
                break;
            case 3:
                imageView.setImageBitmap(CommonFunction.resToBitmap(context.getApplicationContext(), R.drawable.xuanxiu_page));
                break;
            case 4:
                imageView.setImageBitmap(CommonFunction.resToBitmap(context.getApplicationContext(), R.drawable.lost_and_found_page));
                break;
            case 5:
                imageView.setImageBitmap(CommonFunction.resToBitmap(context.getApplicationContext(), R.drawable.redio_page));
                break;
        }

        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.function_item)
        ImageView functionItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
