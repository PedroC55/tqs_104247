

package com.PedroC55;

import static io.restassured.RestAssured.given;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

public class RestAssureTest {
    String api = "https://jsonplaceholder.typicode.com/todos";

    @Test
    void testAPIEndpoint(){
        given().when().get(api).then().statusCode(200); 
    }

    @Test
    void queryToDo4() {
        given().when().get(api + "/4")
                .then().statusCode(200)
                .and().body("title", equalTo("et porro tempora"))
                .and().body("id", equalTo(4));
    }

    @Test
    void testListAllToDos() {
        given().when().get(api)
                .then().statusCode(200)
                .and().body("id", hasItems(198, 199));
    }
}
