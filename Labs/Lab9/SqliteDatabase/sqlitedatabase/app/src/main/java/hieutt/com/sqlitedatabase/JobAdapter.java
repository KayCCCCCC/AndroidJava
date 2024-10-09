package hieutt.com.sqlitedatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class JobAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<Job> jobList;

    public JobAdapter(MainActivity context, int layout, List<Job> jobList) {
        this.context = context;
        this.layout = layout;
        this.jobList = jobList;
    }

    @Override
    public int getCount() {
        return jobList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txtTenCv;
        ImageView imgDelete, imgEdit;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtTenCv = (TextView) convertView.findViewById(R.id.textView);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imageView);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imageView2);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Job job = jobList.get(position);
        holder.txtTenCv.setText(job.getTenCv());

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogUpdate(job.getTenCv(), job.getIdCv());
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogDelete(job.getTenCv(), job.getIdCv());
            }
        });

        return convertView;
    }
}
