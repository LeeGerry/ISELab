package edu.auburn.iselab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.auburn.iselab.db.TaskDao;

public class MainActivity extends Activity {
    @BindView(R.id.btnAdd)Button btnAdd;
    @BindView(R.id.btnRead)Button btnRead;
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @BindView(R.id.btnSetting)
    Button btnSetting;
    private TaskDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tvTitle.setText(getString(R.string.app_name));
        ibBack.setVisibility(View.GONE);
        dao = new TaskDao(this);
    }
    @OnClick(R.id.btnAdd)
    public void add(){
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.btnRead)
    public void read(){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.btnSetting)
    public void setting(){
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        double total = dao.calculateTotalTrauma();

        tvTotal.setText(String.format("%.5f", total));
    }
}
