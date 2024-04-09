package com.example.androidwordle;

import android.content.Context;
import android.content.res.Resources;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestResourceLoading {
    @Test
    public void testResources() throws IOException {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.androidwordle", appContext.getPackageName());
        Resources r = appContext.getResources();
        InputStream in = r.getAssets().open("wordle-full.txt");
    }
}