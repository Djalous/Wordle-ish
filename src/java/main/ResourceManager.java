package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * Manages the resources stored in the resources folder.
 * @author Jack Rosenbecker
 * @version created on 4/1/23
 */
public class ResourceManager {
    /**
     * Loads a resource with the given file name from the resources folder.
     * @param filePath Path of the file you want to load. No beginning '/' necessary.
     * @return The file you want to load.
     * @throws FileNotFoundException If the file was not found.
     */
    public static File loadResource(String filePath) throws FileNotFoundException {
        ClassLoader classloader = getClassLoader();
        URL url = classloader.getResource(filePath);

        if (url == null) {
            throw new FileNotFoundException("Requested resource '" + filePath + "' not found");
        }

        return new File(url.getFile());
    }

    /**
     * Loads a fxml from the resources folder.
     * @param fxmlPath Path of the file you want to load. No beginning '/' necessary.
     * @return The parent node of the fxml.
     * @throws IOException If there was an error loading the file, such as if it wasn't found.
     */
    public static Parent loadFXML(String fxmlPath) throws IOException {
        ClassLoader classloader = getClassLoader();
        URL url = classloader.getResource(fxmlPath);

        if (url == null) {
            throw new FileNotFoundException("Requested resource '" + fxmlPath + "' not found");
        }

        return FXMLLoader.load(url);
    }

    private static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}
