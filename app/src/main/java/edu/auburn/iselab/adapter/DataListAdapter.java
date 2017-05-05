package edu.auburn.iselab.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.auburn.iselab.R;
import edu.auburn.iselab.model.Task;

/**
 * Author: Gary
 * Time: 4/28/17
 */

public class DataListAdapter extends BaseAdapter {
    private Context ctx;
    private List<Task> tasks;
    public DataListAdapter(Context ctx, List<Task> tasks) {
        this.ctx = ctx;
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Task getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHold holder;
        if (view != null) {
            holder = (ViewHold) view.getTag();
        } else {
            view = View.inflate(ctx, R.layout.item_list, null);
            holder = new ViewHold(view);
            view.setTag(holder);
        }
        Task t = getItem(position);
        holder.tvName.setText("task"+(position+1));
        long date = t.getDate();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        String d = format.format(date);
        holder.tvTime.setText(d);
        return view;
    }
    static class ViewHold{
        @BindView(R.id.tvName) TextView tvName;
        @BindView(R.id.tvTime) TextView tvTime;
        public ViewHold(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
