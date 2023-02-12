package specifications;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class APIRequestSpecification {
    public static RequestSpecification ipAPIRequestSpecification() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addFilter(new AllureRestAssured());
        return requestSpecBuilder.build();
    }
}
