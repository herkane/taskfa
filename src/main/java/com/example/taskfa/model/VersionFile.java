package com.example.taskfa.model;

import java.io.File;

public class VersionFile {
    private File file;
    private String description;
    private String name;

    public VersionFile(File file, String description)
    {
        this.file = file;
        this.description = description;
    }

    public VersionFile(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public VersionFile() {
    }

    public void setFile(File fileName) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
