package edu.auburn.iselab;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.auburn.iselab.utils.PrefUtils;

/**
 * Author: Gary
 * Time: 4/28/17
 */

public class SettingActivity extends Activity {
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.btnMetric)
    RadioButton rbMetric;
    @BindView(R.id.btnImperial)
    RadioButton rbImperial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        tvTitle.setText("Setting");
        int unit = PrefUtils.getInt(this, AppConfig.UNIT, AppConfig.UNIT_I);
        if (unit == AppConfig.UNIT_I) {
            rbImperial.setChecked(true);
        } else {
            rbMetric.setChecked(true);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                PrefUtils.setInt(SettingActivity.this, AppConfig.UNIT, checkedId == R.id.btnImperial ? AppConfig.UNIT_I : AppConfig.UNIT_M);
            }
        });
    }

    @OnClick(R.id.ibBack)
    public void back() {
        this.finish();
    }
}
