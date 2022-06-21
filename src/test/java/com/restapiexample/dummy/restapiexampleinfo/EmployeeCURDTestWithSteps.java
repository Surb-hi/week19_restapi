package com.restapiexample.dummy.restapiexampleinfo;

import com.restapiexample.dummy.restapistepsinfo.EmployeeSteps;
import com.restapiexample.dummy.testbase.TestBase;
import com.restapiexample.dummy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;
@RunWith(SerenityRunner.class)
public class EmployeeCURDTestWithSteps extends TestBase {
    static String name = "name" + TestUtils.getRandomValue();
    static int salary = 23456;
    static int age = 35;
    static int id = 02;
    static String employee_name = "name" + TestUtils.getRandomValue();
    static int employee_salary = 2556;
    static int employee_age = 36;
    static String profile_image = "xyz" + TestUtils.getRandomValue();
    static int employeeId;

    @Steps
    EmployeeSteps employeeSteps;

    @Title("This will create a new Employee")
    @Test
    public void test001() {
        ValidatableResponse response = employeeSteps.createEmployee(name, salary, age, id);
        response.log().all().statusCode(200);
        employeeId = response.log().all().extract().path("id");
    }

    @Title("Verify that the employeeId added in to stack")
    @Test
    public void test002() {
        HashMap<String, Object> employeeMap = employeeSteps.getCreatedEmployeeId(employeeId);
        Assert.assertThat(employeeMap, hasValue(name));
        System.out.println(employeeId);
    }

    @Title("This will Updated created id")
    @Test
    public void test003() {
        ValidatableResponse response = employeeSteps.UpdateEmployee(employeeId,employee_name,employee_salary,employee_age,profile_image);
        response.log().all().statusCode(200);
        HashMap<String, Object> employeeMap = employeeSteps.getCreatedEmployeeId(employeeId);
        Assert.assertThat(employeeMap, hasValue(name));
        System.out.println();
    }

    @Title("This will Delate created id")
    @Test
    public void test004() {
        employeeSteps.deleteEmployee(employeeId).statusCode(200);
        employeeSteps.getServicesById(employeeId).statusCode(404);
    }
}
