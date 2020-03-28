package com.dfh.csma.system.model;

public class FileMsg {

    private String status;
    private String filename;
    private String filelocation;


    public FileMsg(String status, String filename, String filelocation) {
        super();
        this.status = status;
        this.filename = filename;
        this.filelocation = filelocation;
    }

    public FileMsg() {
        super();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilelocation() {
        return filelocation;
    }

    public void setFilelocation(String filelocation) {
        this.filelocation = filelocation;
    }

    @Override
    public String toString() {
        return "FileMsg [status=" + status + ", filename=" + filename + ", filelocation=" + filelocation + "]";
    }


}
