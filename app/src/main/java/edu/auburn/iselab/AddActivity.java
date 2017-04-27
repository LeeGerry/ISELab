package edu.auburn.iselab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.auburn.iselab.db.TaskDao;
import edu.auburn.iselab.model.Task;
import edu.auburn.iselab.utils.MultiplierUtils;

public class AddActivity extends AppCompatActivity {
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.etLever)
    EditText etLever;
    @BindView(R.id.etLoad)
    EditText etLoad;
    @BindView(R.id.etNumer)
    EditText etNumber;
    private String lever, load, number;
    private Task task;
    private TaskDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
        etLever = (EditText) findViewById(R.id.etLever);
        etLoad = (EditText) findViewById(R.id.etLoad);
        etNumber = (EditText) findViewById(R.id.etNumer);
        dao = new TaskDao(this);
    }
    @OnClick(R.id.btnCancel)
    public void cancel(View v){
        finish();
    }
    @OnClick(R.id.btnSave)
    public void save(View view){
        lever = etLever.getText().toString().trim();
        load = etLoad.getText().toString().trim();
        number = etNumber.getText().toString().trim();
        if (TextUtils.isEmpty(lever) || TextUtils.isEmpty(load) || TextUtils.isEmpty(number)){
            Toast.makeText(this, "please input all values needed", Toast.LENGTH_SHORT).show();
            return;
        }
        task = new Task();
        double d_lever = Double.parseDouble(lever);
        double d_load = Double.parseDouble(load);
        task.setLever(d_lever);
        task.setLoad(d_load);
        double d_moment = d_lever * d_load / 12;
        task.setMoment(d_moment);
        double d_mul = MultiplierUtils.getValueByBound(this, d_moment);
        task.setMultiplier(d_mul);
        int num = Integer.parseInt(number);
        task.setRepetitions(num);
        double d_damage = d_mul * num;
        task.setDamage(d_damage);
        task.setDate(System.currentTimeMillis());

        dao.addTask(task);
    }
}
