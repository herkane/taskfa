package com.example.taskfa.model;

public class File {
    private String fileName;
    private FileStatus status;

    public File(String fileName, FileStatus status)
    {
        this.fileName = fileName;
        this.status = status;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FileStatus getStatus() {
        return status;
    }

    public void setStatus(FileStatus status) {
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }
}
