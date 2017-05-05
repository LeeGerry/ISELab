package edu.auburn.iselab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.auburn.iselab.db.TaskDao;
import edu.auburn.iselab.model.Task;
import edu.auburn.iselab.utils.Arith;
import edu.auburn.iselab.utils.MultiplierUtils;
import edu.auburn.iselab.utils.PrefUtils;


public class AddActivity extends Activity {
    @BindView(R.id.btnSave) Button btnSave;
    @BindView(R.id.btnCancel) Button btnCancel;
    @BindView(R.id.etLever) EditText etLever;
    @BindView(R.id.etLoad) EditText etLoad;
    @BindView(R.id.etNumer) EditText etNumber;
    private String lever, load, number;
    private Task task;
    private TaskDao dao;
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btnCal)
    Button ibCal;
    @BindView(R.id.tvDamage)
    TextView tvDamage;
    @BindView(R.id.tvLeverTip) TextView tvLeverTip;
    @BindView(R.id.tvLoadTip) TextView tvLoadTip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
        tvTitle.setText("ADD LIFTT");
        dao = new TaskDao(this);

        int unit = PrefUtils.getInt(this, AppConfig.UNIT, AppConfig.UNIT_I);
        if (unit == AppConfig.UNIT_I){
            tvLeverTip.setText(getString(R.string.i_lever));
            tvLoadTip.setText(getString(R.string.i_load));
        }else {
            tvLeverTip.setText(getString(R.string.m_lever));
            tvLoadTip.setText(getString(R.string.m_load));
        }
    }
    @OnClick(R.id.btnCal)
    public void calculate(){
        if (!validate()) return;

        double d_lever;
        double d_load;

        int unit = PrefUtils.getInt(this, AppConfig.UNIT, AppConfig.UNIT_I);
        if (unit == AppConfig.UNIT_I){
            d_lever = Double.parseDouble(lever);
            d_load = Double.parseDouble(load);
        }else {// non us (cm & kg should be convert to us unit)
            d_lever = MultiplierUtils.cmToInch(Double.parseDouble(lever));
            d_load = MultiplierUtils.cmToInch(Double.parseDouble(load));
        }

        double d_moment = Arith.div(Arith.mul(d_lever, d_load), 12, 5);

        double d_mul = MultiplierUtils.getValueByBound(this, d_moment);
        int num = Integer.parseInt(number);
        double d_damage = Arith.mul(d_mul, num);
        String d = String.format("%.5f", d_damage);
        tvDamage.setText(d);
    }
    @OnClick(R.id.btnCancel)
    public void cancel(){
        finish();
    }
    @OnClick(R.id.btnSave)
    public void save(){
        if (!validate()) return;
        task = new Task();

        double d_lever;
        double d_load;

        int unit = PrefUtils.getInt(this, AppConfig.UNIT, AppConfig.UNIT_I);
        if (unit == AppConfig.UNIT_I){
            d_lever = Double.parseDouble(lever);
            d_load = Double.parseDouble(load);
        }else {// non us (cm & kg should be convert to us unit)
            d_lever = MultiplierUtils.cmToInch(Double.parseDouble(lever));
            d_load = MultiplierUtils.cmToInch(Double.parseDouble(load));
        }


        task.setLever(d_lever);
        task.setLoad(d_load);
        double d_moment = Arith.div(Arith.mul(d_lever, d_load), 12, 5);

        task.setMoment(d_moment);
        double d_mul = MultiplierUtils.getValueByBound(this, d_moment);
        task.setMultiplier(d_mul);
        int num = Integer.parseInt(number);
        task.setRepetitions(num);
        double d_damage = Arith.mul(d_mul, num);
        String d = String.format("%.5f", d_damage);
        task.setDamage(Double.parseDouble(d));
        task.setDate(System.currentTimeMillis());
        dao.addTask(task);
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean validate() {
        lever = etLever.getText().toString().trim();
        load = etLoad.getText().toString().trim();
        number = etNumber.getText().toString().trim();
        if (TextUtils.isEmpty(lever) || TextUtils.isEmpty(load) || TextUtils.isEmpty(number)){
            Toast.makeText(this, "please input all values needed", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @OnClick(R.id.ibBack)
    public void back(){
        this.finish();
    }
}
