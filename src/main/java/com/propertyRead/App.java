package com.propertyRead;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.Person;
import pl.jalokim.propertiestojson.util.PropertiesToJsonParser;
import utl.module.DateTimeModule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;


/**
 * Created by deshanchathusanka on 4/4/18.
 */

public class App 
{
    public Properties loadDirectFromProperties()
    {
        Properties properties=new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream("src/main/java/resources/Person.properties");
            properties.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public Properties loadFromProperties()
    {
        Properties properties=new Properties();
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("Person.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
    public static void main( String[] args )
    {
        //read from properties file using resource bundle
        Locale.setDefault(new Locale("en","US"));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("CountryDetails_us", Locale.getDefault());
        System.out.println("name : "+resourceBundle.getString("name")+"\tcapital : "+resourceBundle.getString("capital"));

        //load data from properties and convert to json
        App app=new App();
        Properties properties=app.loadFromProperties();
        //Properties properties=app.loadDirectFromProperties();
        String jsonFromProperties = PropertiesToJsonParser.parseToJson(properties);
        System.out.println("jsonStr : "+jsonFromProperties);

        //json to object mapping
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new DateTimeModule());//register new dateTime module
        try {
            Person person = mapper.readValue(jsonFromProperties, Person.class);
            System.out.println(person.getDob().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
