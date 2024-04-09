package com.example.androidwordle;

import android.content.res.Resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Manages the resources stored in the resources folder.
 * @author Jack Rosenbecker
 * @version created on 4/1/23
 */
public class ResourceManager {
    private final Resources resources;

    public ResourceManager(Resources resources) {
        this.resources = resources;
    }


    /**
     * Loads a resource with the given file name from the resources folder.
     * @param filePath Path of the file you want to load. No beginning '/' necessary.
     * @return The file you want to load.
     * @throws FileNotFoundException If the file was not found.
     */
    public InputStream loadResource(String filePath) throws IOException {
        return resources.getAssets().open(filePath);
    }
}
