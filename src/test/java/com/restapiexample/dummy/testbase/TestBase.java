package com.restapiexample.dummy.testbase;


import com.restapiexample.dummy.constants.Path;
import com.restapiexample.dummy.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

/**
 * Created by Jay
 */
public class TestBase {
    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init() {
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
        RestAssured.basePath = Path.EMPLOYEE;

        //RestAssured.port = Integer.parseInt(propertyReader.getProperty("port"));
    }

}
