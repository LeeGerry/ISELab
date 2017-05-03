package edu.auburn.iselab.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import edu.auburn.iselab.model.Moment;

/**
 * Author: Gary
 * Time: 4/27/17
 */

public class MultiplierUtils {
    /**
     * init moment
     * @param ctx
     * @return
     */
    public static List<Moment> initMomentList(Context ctx){
        List<Moment> moments = new ArrayList<>();
        try {
            InputStream is = ctx.getApplicationContext().getAssets().open("moment.txt");
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(reader);
            Moment m ;
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                String[] strs = str.split("\t");
                m = new Moment(Double.parseDouble(strs[0]),Double.parseDouble(strs[1]),Double.parseDouble(strs[2]));
                moments.add(m);
            }
            return moments;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public static Double getValueByBound(Context ctx, double moment){
        if(moment >= 200) return .0459673d;
        if (moment <= 0)  return .0000017d;
        List<Moment> list = initMomentList(ctx);
        for (Moment m: list){
            if (moment>=m.getLower() && moment<m.getUpper()){
                return m.getValue();
            }
        }
        return -1.0d;
    }
}
