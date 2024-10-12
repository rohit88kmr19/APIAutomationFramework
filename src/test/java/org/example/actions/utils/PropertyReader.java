package org.example.actions.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {

    // Responsibility of this class to give the value of by key

    public static String readKey(String key) {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/java/org/example/actions/Resources/data.properties");
            properties.load(fileInputStream);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return properties.getProperty(key);

    }
}