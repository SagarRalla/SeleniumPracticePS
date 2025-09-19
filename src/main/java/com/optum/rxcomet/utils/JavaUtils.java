package com.optum.rxcomet.utils;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class JavaUtils {

	//This method will read key-value pair from properties file
	public static Properties readPropertiesFile(String fileName){
        FileInputStream fis = null;				  //Prepare to read file
        Properties prop = null;					  //Store key-value pairs
        try {
            fis = new FileInputStream(fileName);  //Opens the file for reading.
            prop = new Properties();			  //Creates an empty Properties object.
            prop.load(fis);						  //Loads key-value pairs from the file into the Properties object.
            prop.getProperty("Stage_Env");		  //This will give the value of Test_Env key from properties file
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                fis.close();
            }catch (Throwable t){
                t.printStackTrace();
            }
        }
        return prop;
    }
	
	public static String generateRandomNumber()
	{
        Integer randomNumber = (int) Math.floor((Math.random() * 1000000000) + 1);
        return  randomNumber.toString();
    }
	
	public static String dateFormat(String input, String output, String inputDate){
        try {
            SimpleDateFormat sdfSource = new SimpleDateFormat(input);
            Date date = sdfSource.parse(inputDate);
            SimpleDateFormat sdfDestination = new SimpleDateFormat(output);
            return sdfDestination.format(date);

        }catch (Throwable t){
            t.printStackTrace();
        }
        return "";
    }
}
