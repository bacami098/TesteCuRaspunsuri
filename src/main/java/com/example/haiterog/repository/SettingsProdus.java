package com.example.haiterog.repository;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SettingsProdus
{
    private static SettingsProdus instance;

    private String repoType;

    private final String repoFile;

    private SettingsProdus(String repoType, String repoFile)
    {
        this.repoFile = repoFile;
        this.repoType = repoType;
    }

    public String getRepoType()
    {
        return this.repoType;
    }

    public String getRepoFile()
    {
        return this.repoFile;
    }

    private static Properties loadSettings()
    {
        try (FileReader fr = new FileReader("C:\\MAP\\test\\src\\main\\java\\com\\example\\haiterog\\repository\\settingsProdus.properties"))
        {
            Properties settings = new Properties();
            settings.load(fr);
            return settings;
        }
        catch (IOException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static synchronized SettingsProdus getInstance()
    {
        Properties properties = loadSettings();
        instance = new SettingsProdus(properties.getProperty("repo_type"), properties.getProperty("repo_file"));
        return instance;
    }
}