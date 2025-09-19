package com.optum.rxcomet.utils;

public class FilePathUtils {

	public static String getWorkingDirectoryPath(){
        return System.getProperty("user.dir");
    }

    public static String getResourceDirectoryPath(){
        return getWorkingDirectoryPath()+"/src/test/resources/";
    }
    
    public static String getDataDirectoryPath(){
        return getResourceDirectoryPath()+"/data";
    }
}

