package edu.auburn.iselab;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.auburn.iselab.model.Task;

/**
 * Author: Gary
 * Time: 4/28/17
 */

public class ItemActivity extends Activity {
    @BindView(R.id.tvLever)TextView tvLever;
    @BindView(R.id.tvLoad)TextView tvLoad;
    @BindView(R.id.tvRepetitions)TextView tvRep;
    private Task task;
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);
        task = (Task)getIntent().getSerializableExtra("task");
        tvTitle.setText("Task"+task.getId());
        tvLever.setText(task.getLever()+"");
        tvLoad.setText(task.getLoad()+"");
        tvRep.setText(task.getRepetitions()+"");
    }
    @OnClick(R.id.ibBack)
    public void back(){
        this.finish();
    }
}
