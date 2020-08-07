package com.cybertek.jdbc.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

        //1 Created properties object
        private static Properties properties=new Properties();

        static{//only running once and first//we can not write code outside any block
            //2.Get our path and store in String or directly pass into fileInputStream object
            String path="configuration.properties";
            //3.Open the file
            try {
                FileInputStream file = new FileInputStream(path);
                //4.Load th file
                properties.load(file);
                //5.close.file
                file.close();
            }catch (IOException e){
                System.out.println("Properties file not found");
            }


        }
        //Our custom method to read and get values from configuration.properties file/like a getter for properties variable
        public static String getProperty(String keyWord){
            return properties.getProperty(keyWord);
        }




    }


