package com.restapiexample.dummy.cucumber.steps;

import com.restapiexample.dummy.restapistepsinfo.EmployeeSteps;
import com.restapiexample.dummy.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class employees {
    static String name = "name" + TestUtils.getRandomValue();
    static int salary = 23456;
    static int age = 35;
    static int id = 02;
    static String employee_name = "name" + TestUtils.getRandomValue();
    static int employee_salary = 2556;
    static int employee_age = 36;
    static String profile_image = "xyz" + TestUtils.getRandomValue();
    static int employeeId;
    static ValidatableResponse response;

    @Steps
    EmployeeSteps employeeSteps;

    @When("^User sends a GET request to list endpoint$")
    public void userSendsAGETRequestToListEndpoint() {
    response=employeeSteps.getAllUsers();
    }

    @Then("^User get back a valid status code (\\d+)$")
    public void userGetBackAValidStatusCode(int code) {
        response.log().all().statusCode(code);
    }

    @When("^User sends a Post request to list endpoint$")
    public void userSendsAPostRequestToListEndpoint() {
        response = employeeSteps.createEmployee(name, salary, age, id);
        employeeId = response.log().all().extract().path("id");
    }

    @And("^Verify that user created sucessfully$")
    public void verifyThatUserCreatedSucessfully() {
        HashMap<String, Object> employeeMap = employeeSteps.getCreatedEmployeeId(employeeId);
        Assert.assertThat(employeeMap, hasValue(name));
        System.out.println(employeeId);
    }

    @When("^User sends a patch request to list endpoint$")
    public void userSendsAPatchRequestToListEndpoint() {
        name = "name" + TestUtils.getRandomValue();
        response = employeeSteps.UpdateEmployee(employeeId,employee_name,employee_salary,employee_age,profile_image);
    }

    @When("^User sends a delete request to list endpoint$")
    public void userSendsADeleteRequestToListEndpoint() {
        employeeSteps.deleteEmployee(employeeId);
        employeeSteps.getServicesById(employeeId);
    }
}
