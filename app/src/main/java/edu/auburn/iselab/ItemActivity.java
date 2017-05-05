package edu.auburn.iselab;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.auburn.iselab.model.Task;
import edu.auburn.iselab.utils.MultiplierUtils;
import edu.auburn.iselab.utils.PrefUtils;

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
    @BindView(R.id.tvDamage)
    TextView tvDamage;
    @BindView(R.id.tvLeverTip) TextView tvLeverTip;
    @BindView(R.id.tvLoadTip) TextView tvLoadTip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);
        task = (Task)getIntent().getSerializableExtra("task");
        int position = getIntent().getIntExtra("position", 1);
        tvTitle.setText("Task"+(position+1));
        tvDamage.setText(String.format("%.5f", task.getDamage()));
        tvRep.setText(task.getRepetitions()+"");
        int unit = PrefUtils.getInt(this, AppConfig.UNIT, AppConfig.UNIT_I);
        if (unit == AppConfig.UNIT_I){
            tvLeverTip.setText(getString(R.string.i_lever));
            tvLoadTip.setText(getString(R.string.i_load));
            tvLever.setText(task.getLever()+"");
            tvLoad.setText(task.getLoad()+"");
        }else {
            tvLeverTip.setText(getString(R.string.m_lever));
            tvLoadTip.setText(getString(R.string.m_load));
            String lever = String.format("%.5f", MultiplierUtils.inchToCm(task.getLever()));
            tvLever.setText(lever);
            String load = String.format("%.5f", MultiplierUtils.lbToKg(task.getLoad()));
            tvLoad.setText(load);
        }
    }
    @OnClick(R.id.ibBack)
    public void back(){
        this.finish();
    }
}
