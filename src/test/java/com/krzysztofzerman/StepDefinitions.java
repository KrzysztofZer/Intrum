package com.krzysztofzerman;

import com.google.gson.Gson;
import com.krzysztofzerman.jsonbodies.Person;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class StepDefinitions {
    Gson gson = new Gson();
    Person person;
    Response response;

    @Given("provide data for request: {string}, {string}, {string}, {string}, {string}")
    public void provideDataForRequest(String id, String firstName, String lastName, String email, String companyId) {
        person = new Person(Integer.parseInt(id), firstName, lastName, email, Integer.parseInt(companyId));
        RestAssured.baseURI = "http://intrumhomework.mocklab.io";
    }

    @When("send data with POST method to {string}")
    public void sendDataWithPOSTMethodTo(String endpoint) {
        String jsonBody = gson.toJson(person);
        response = given()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post(endpoint)
                .then().extract().response();
    }

    @Then("assert response for status code {int}")
    public void assertResponseForStatusCode(int statusCode) {
        assert response.getStatusCode() == statusCode;
    }

}
