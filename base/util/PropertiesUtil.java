package br.com.realizecfi.orbi.base.util;

import br.com.realizecfi.orbi.base.AppiumDriverManager;
import br.com.realizecfi.orbi.base.ProjectSettings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PropertiesUtil {
    public static final String LOCAL_PROPERTY_FILE_NAME = "application-local.properties";
    public static final String DEFAULT_PROPERTY_FILE_NAME = "application.properties";

    BufferedReader reader;
    java.util.Properties properties = new java.util.Properties();

    public PropertiesUtil() {
        createPropertiesFile();
    }

    public void createPropertiesFile() {
        try {
            reader = new BufferedReader(new FileReader(ProjectSettings.PROPERTIES_PATH + getFileName()));
            properties.load(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPropertyByName(String property) {
        String propertyName = properties.getProperty(property);

        if (propertyName != null) {
            return propertyName;
        } else {
            throw new RuntimeException("Não foi encontrado a propriedade: " + property);
        }
    }

    public String getPropertyByNameBase64(String property) {
        String propertyName = properties.getProperty(property);

        if (propertyName != null) {
            return new EncodedUtil().getBase64TextDecoder(propertyName);
        } else {
            throw new RuntimeException("Não foi encontrado a propriedade '" + property + "' no arquivo:" + property);
        }
    }

    public String getFileName() {
        if (AppiumDriverManager.shared().getProfile().equals("LOCAL")) {
            return LOCAL_PROPERTY_FILE_NAME;
        } else {
            return DEFAULT_PROPERTY_FILE_NAME;
        }
    }
}
