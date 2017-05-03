package edu.auburn.iselab;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.auburn.iselab.adapter.DataListAdapter;
import edu.auburn.iselab.db.TaskDao;
import edu.auburn.iselab.model.Task;

public class ListActivity extends Activity {
    @BindView(R.id.list_view)
    ListView listView;
    private TaskDao dao;
    private DataListAdapter adapter;
    private List<Task> list;
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        tvTitle.setText("Data List");

        dao = new TaskDao(this);
        list = dao.getTasks();
        adapter = new DataListAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, ItemActivity.class);
                Task item = adapter.getItem(position);
                intent.putExtra("task", item);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(ListActivity.this)
                        .setTitle("Tips")
                        .setMessage("Delete this data?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dao.delete(list.get(position).getId());
                                list.clear();
                                list.addAll(dao.getTasks());
                                adapter.notifyDataSetChanged();
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
                return true;
            }
        });
    }
    @OnClick(R.id.ibBack)
    public void back(){
        this.finish();
    }
}
