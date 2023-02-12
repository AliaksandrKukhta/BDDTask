
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import responsePojo.ErrorRoot;
import responsePojo.Root;
import utils.ReadProperty;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static specifications.APIRequestSpecification.ipAPIRequestSpecification;

public class RestAPIStep {
    ReadProperty readProperty = new ReadProperty();
    private Response response;
    private Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;

    }

    @Given("Request current weather for city {string}")
    public void requestCurrentWeatherForCity(String city) {
        try {
            RestAssured.baseURI = readProperty.getPropertyValue("local", "base_URL");
            response = given().spec(ipAPIRequestSpecification())
                    .when()
                    .get(RestAssured.baseURI + readProperty.getPropertyValue("key", "access_key") + "&query=" + city);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Given("Request negative for city {string} without Access key")
    public void requestNegativeForCityWithoutAccessKey(String city) {
        try {
            RestAssured.baseURI = readProperty.getPropertyValue("local", "base_URL");
            response = given().spec(ipAPIRequestSpecification())
                    .when()
                    .get(RestAssured.baseURI + "&query=" + city);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Given("Request negative for city {string} with double query")
    public void requestNegativeForCityWithDoubleQuery(String city) {
        try {
            RestAssured.baseURI = readProperty.getPropertyValue("local", "base_URL");
            response = given().spec(ipAPIRequestSpecification())
                    .when()
                    .get(RestAssured.baseURI + readProperty.getPropertyValue("key", "access_key") + "&query=" + city + "&query=");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Given("Request negative for city {string} with bad URL")
    public void requestNegativeForCityWithBadURL(String city) {
        try {
            RestAssured.baseURI = readProperty.getPropertyValue("local", "bad_URL");
            response = given().spec(ipAPIRequestSpecification())
                    .when()
                    .get(RestAssured.baseURI + readProperty.getPropertyValue("key", "access_key") + "&query=" + city);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Then("Error Response is {int}")
    public void errorResponseIs(int statusCode) {
        ErrorRoot errorRoot = response.then().extract().body().as(ErrorRoot.class);
        int actualResponseCode = errorRoot.error.code;
        assertEquals(statusCode, actualResponseCode);
    }

    @Then("Information are {string}, {string}, {string}, {string}")
    public void informationAre(String language, String name, String region, String country) {
        Root root = response.then().extract().body().as(Root.class);
        String actualLanguage = root.request.getLanguage();
        String actualName = root.location.getName();
        String actualRegion = root.location.getRegion();
        String actualCountry = root.location.getCountry();
        assertEquals("Expected language " + language + " not Equals actual language " + actualLanguage, language, actualLanguage);
        assertEquals("Expected name " + name + " not Equals actual name " + actualName, name, actualName);
        assertEquals("Expected region " + region + " not Equals actual region " + actualRegion, region, actualRegion);
        assertEquals("Expected country " + country + " not Equals actual country " + actualCountry, country, actualCountry);
    }

    @Then("Response is {int}")
    public void responseIsStatusCode(int statusCode) {
        int actualResponseCode = response.then().extract().statusCode();
        assertEquals(statusCode, actualResponseCode);
    }
}
