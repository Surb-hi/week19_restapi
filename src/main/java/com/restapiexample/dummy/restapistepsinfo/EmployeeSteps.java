package com.restapiexample.dummy.restapistepsinfo;

import com.restapiexample.dummy.constants.EndPoints;
import com.restapiexample.dummy.model.EmployeePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class EmployeeSteps {
    @Step("Creating Employee with perameter")
    public ValidatableResponse createEmployee(String name,int salary ,int age ,int id) {
        EmployeePojo employeePojo = new EmployeePojo();
        employeePojo.setName(name);
        employeePojo.setSalary(salary);
        employeePojo.setAge(age);
        employeePojo.setId(id);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(employeePojo)
                .when()
                .post(EndPoints.CREATE_EMPLOYEE)
                .then();
    }
    @Step("Getting the Employee information from Id")
    public HashMap<String, Object> getCreatedEmployeeId(int employeeId){

        HashMap<String, Object> EmployeeMap = SerenityRest.given().log().all()
                .when()
                .pathParam("employeeId",employeeId)
                .get(EndPoints.GET_SINGLE_EMPLOYEE_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
        return EmployeeMap;
    }
    @Step("Update Employee with perameter")
    public ValidatableResponse UpdateEmployee(int employeeId,String employee_name,int employee_salary,int employee_age,String profile_image) {
        EmployeePojo employeePojo = new EmployeePojo();
        employeePojo.setEmployee_name(employee_name);
        employeePojo.setEmployee_salary(employee_salary);
        employeePojo.setEmployee_age(employee_age);
        employeePojo.setProfile_image(profile_image);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(employeePojo)
                .when()
                .pathParam("employeeId",employeeId)
                .patch(EndPoints.UPDATE_EMPLOYEE_BY_ID)
                .then();
    }
    @Step("Deleting Employee information with in Employee")
    public ValidatableResponse deleteEmployee(int employeeId) {
        return SerenityRest.given().log().all()
                .pathParam("employeeId",employeeId)
                .when()
                .delete(EndPoints.DELETE_EMPLOYEE_BY_ID)
                .then();
    }

    @Step("Getting Employee information with EmployeeId")
    public ValidatableResponse getServicesById(int employeeId) {
        return SerenityRest.given().log().all()
                .pathParam("employeeId",employeeId)
                .when()
                .get(EndPoints.GET_SINGLE_EMPLOYEE_BY_ID)
                .then();
    }
    @Step("Getting All users information")
    public ValidatableResponse getAllUsers(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_EMPLOYEE)
                .then();
    }
}
