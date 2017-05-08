package edu.auburn.iselab;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.auburn.iselab.adapter.DataListAdapter;
import edu.auburn.iselab.adapter.DataListEditAdapter;
import edu.auburn.iselab.db.TaskDao;
import edu.auburn.iselab.model.Task;

public class ListActivity extends Activity {
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.tvEdit)
    TextView tvEdit;
    private TaskDao dao;
    private DataListAdapter adapter;
    private DataListEditAdapter editAdapter;
    private List<Task> list;
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.llBottom)
    LinearLayout llBottom;
    @BindView(R.id.tvAll)
    TextView tvAll;
    @BindView(R.id.tvInverse)
    TextView tvInverse;
    @BindView(R.id.tvDelete)
    TextView tvDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        tvTitle.setText("Data List");
        tvEdit.setVisibility(View.VISIBLE);
        dao = new TaskDao(this);
        list = dao.getTasks();
        adapter = new DataListAdapter(this, list);
        editAdapter = new DataListEditAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, ItemActivity.class);
                Task item = adapter.getItem(position);
                intent.putExtra("task", item);
                intent.putExtra("position", position);

                startActivity(intent);
            }
        });

    }

    @OnClick(R.id.ibBack)
    public void back() {
        this.finish();
    }

    @OnClick(R.id.tvEdit)
    public void edit() {
        if (tvEdit.getText().toString().equals("Edit")) {
            llBottom.setVisibility(View.VISIBLE);
            tvEdit.setText("Done");
            listView.setAdapter(editAdapter);
            editAdapter.notifyDataSetChanged();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Object obj = listView.getItemAtPosition(position);
                    if (obj == null) return;
                    Task t = (Task) obj;
                    CheckBox cb = (CheckBox) view.findViewById(R.id.cbStatus);
                    if (cb.isChecked()) {
                        cb.setChecked(false);
                        t.setChecked(false);
                    } else {
                        cb.setChecked(true);
                        t.setChecked(true);
                    }
                }
            });
        } else {
            llBottom.setVisibility(View.GONE);
            tvEdit.setText("Edit");
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ListActivity.this, ItemActivity.class);
                    Task item = adapter.getItem(position);
                    intent.putExtra("task", item);
                    intent.putExtra("position", position);
                    startActivity(intent);
                }
            });
        }
    }


    @OnClick(R.id.tvAll)
    public void selectAll(View view) {
        for (Task task : list) {
            task.setChecked(true);
        }
        editAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.tvInverse)
    public void inverse(View view) {
        for (Task task : list) {
            task.setChecked(!task.isChecked());
        }
        editAdapter.notifyDataSetChanged();
    }
    private int total = 0;
    @OnClick(R.id.tvDelete)
    public void delete(View view) {
        if (list.size()<1) return ;
        for (Task task : list) {
            if (task.isChecked()) {
                total += 1;
                break;
            }
        }
        if (total == 0) return;
        new AlertDialog.Builder(ListActivity.this)
                .setTitle("Tips")
                .setMessage("Confirm delete?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (Task task : list) {
                            if (task.isChecked()) {
                                dao.delete(task.getId());
                            }
                        }
                        list.clear();
                        list.addAll(dao.getTasks());
                        editAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }
}
