package com.example.androidwordle;

import org.junit.Test;

import static org.junit.Assert.*;

import android.content.Context;
import android.content.res.Resources;

import java.io.FileNotFoundException;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestResourceLoading {
    @Test
    public void loadWordFiles() throws FileNotFoundException {
        //File file = ResourceManager.loadResource("wordle-full.txt");
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.androidwordle", appContext.getPackageName());
        Resources r = appContext.getResources();
        r.getAssets().open("wordle-full.txt");

    }
}