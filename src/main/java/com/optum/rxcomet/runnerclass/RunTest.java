package com.optum.rxcomet.runnerclass;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.optum.rxcomet.tests.ImplementationTest;
import com.optum.rxcomet.utils.ExcelUtils;
import com.optum.rxcomet.utils.FilePathUtils;

public class RunTest {

	@Test
	 public void runMain() 
	 {
		File folder = new File(FilePathUtils.getDataDirectoryPath());
        Class impClass = ImplementationTest.class;
        
        //Creates a new TestNG suite programmatically
        XmlSuite suite = new XmlSuite();
        suite.setName("Suite");
        
        //Creates a new TestNG test inside the suite
        XmlTest test = new XmlTest(suite);
        test.setName("Test");
        int count = 1;
        
        String[] myFiles = folder.list(new FilenameFilter() {
            public boolean accept(File directory, String fileName) {
                return fileName.endsWith(".xls");
            }
        });

        for (String fileEntry : myFiles)  
        {
        	//Creates a new sub-suite and sub-test for each file
            XmlSuite subSuite = new XmlSuite();
            subSuite.setName("Sub suite" + count);
            XmlTest subTest = new XmlTest(suite);
            subTest.setName("Sub test" + count);
            
            String fileName = fileEntry.split(".xls")[0];
            String clientName = ExcelUtils.getAnswer(fileName, "Client Name");

            if (fileName.contains("mplementationData")) 
            {
            	//Creates a list to hold test classes
            	//XmlClass is a TestNG class that represents a Java class
                List<XmlClass> clazz = new ArrayList<XmlClass>();
                
                //Adds the ImplementationTest class (stored in impClass) to the list
                clazz.add(new XmlClass(impClass));
                
                //Assigns the list of test classes (clazz) to the current subTest
                subTest.setXmlClasses(clazz);
                
                //Passes parameters to the test class
                Map<String, String> map = new HashMap<>();
                map.put("fileName", fileName);
                map.put("testCaseName", fileName);
                map.put("clientName", clientName);
                subTest.setParameters(map);
            }
//            else {
//                List<XmlClass> clazz = new ArrayList<XmlClass>();
//                clazz.add(new XmlClass(benefitsAndServices));
//                subTest.setXmlClasses(clazz);
//                Map<String, String> map = new HashMap<>();
//                map.put("fileName", fileName);
//                map.put("testCaseName", fileName);
//                map.put("clientName", clientName);
//                subTest.setParameters(map);
//            }
            suite.setParentSuite(subSuite);
            count++;
        }

        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.setVerbose(10);
        tng.run();
	 }
}
