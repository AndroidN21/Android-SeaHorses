package hcmus.nhom21.parcheesigame;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
<<<<<<< HEAD
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
=======
 * @see <activity_lossinggame href="http://d.android.com/tools/testing">Testing documentation</activity_lossinggame>
>>>>>>> VanHien
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("hcmus.nhom21.demoparchessi", appContext.getPackageName());
    }
}