package edu.auburn.iselab;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import edu.auburn.iselab.model.Moment;
import edu.auburn.iselab.utils.MultiplierUtils;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void testInitMomentList() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        List<Moment> moments = MultiplierUtils.initMomentList(appContext);
        Log.i("moment", moments.get(0).toString());
        Log.i("moment", moments.size()+"");
    }
    @Test
    public void testGetMul1() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        String mes = "not equal";
        double result = MultiplierUtils.getValueByBound(appContext, 33.3);
        Assert.assertEquals(mes,result,0.0000088d,0);
    }
    @Test
    public void testGetMul2() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        String mes = "not equal";
        double result = MultiplierUtils.getValueByBound(appContext, 50.3);
        Assert.assertEquals(mes,result,0.0000207d,0);
    }
}
