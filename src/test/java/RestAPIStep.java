
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import responseDTO.ErrorRoot;
import responseDTO.Root;
import utils.ReadProperty;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static specifications.APIRequestSpecification.aPIRequestSpecification;

public class RestAPIStep {
    ReadProperty readProperty = new ReadProperty();
    private Response response;
    private Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;

    }

    @Given("Request current weather for city {string}")
    public void getCurrentWeatherForCityResponse(String city) {
        try {
            RestAssured.baseURI = readProperty.getPropertyValue("local", "base_URL");
            response = given().spec(aPIRequestSpecification())
                    .when()
                    .get(RestAssured.baseURI + readProperty.getPropertyValue("key", "access_key") + "&query=" + city);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Given("Request negative for city {string} without Access key")
    public void getNegativeWeatherForCityResponseWithoutAccessKey(String city) {
        try {
            RestAssured.baseURI = readProperty.getPropertyValue("local", "base_URL");
            response = given().spec(aPIRequestSpecification())
                    .when()
                    .get(RestAssured.baseURI + "&query=" + city);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Given("Request negative for city {string} with double query")
    public void getNegativeWeatherForCityResponseWithDoubleQuery(String city) {
        try {
            RestAssured.baseURI = readProperty.getPropertyValue("local", "base_URL");
            response = given().spec(aPIRequestSpecification())
                    .when()
                    .get(RestAssured.baseURI + readProperty.getPropertyValue("key", "access_key") + "&query=" + city + "&query=");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Given("Request negative for city {string} with bad URL")
    public void getNegativeWeatherForCityResponseWithBadURL(String city) {
        try {
            RestAssured.baseURI = readProperty.getPropertyValue("local", "bad_URL");
            response = given().spec(aPIRequestSpecification())
                    .when()
                    .get(RestAssured.baseURI + readProperty.getPropertyValue("key", "access_key") + "&query=" + city);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Then("Error Response is {int}")
    public void errorResponseIs(int statusCode) {
        ErrorRoot errorRoot = response.then().extract().body().as(ErrorRoot.class);
        int actualResponseStatusCode = errorRoot.error.getCode();
        assertEquals("Expected statusCode " + statusCode + " not Equals actual statusCode " +
                actualResponseStatusCode, statusCode, actualResponseStatusCode);
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
        int actualResponseStatusCode = response.then().extract().statusCode();
        assertEquals("Expected statusCode " + statusCode + " not Equals actual statusCode " +
                actualResponseStatusCode, statusCode, actualResponseStatusCode);
    }
}
