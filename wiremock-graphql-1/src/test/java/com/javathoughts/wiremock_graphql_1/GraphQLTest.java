package com.javathoughts.wiremock_graphql_1;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class GraphQLTest {

    private static final String GRAPHQL_ENDPOINT = "http://localhost:8081/graphql"; // WireMock server
    private static final TestRestTemplate restTemplate = new TestRestTemplate();

    @BeforeAll
    public static void setupWireMock() {
        // WireMock setup for testing with recorded responses
        WireMock.configureFor("localhost", 8081);

        // Serve the recorded response from __files/response-graphql-1.json
        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/graphql"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("body-graphql-Uf0lR.json"))); // The recorded response file
    }

    @Test
    public void testGraphQLQuery() {
        // The GraphQL query to test
        String query = "{ \"query\": \"{ getGreeting(name: \\\"Javed\\\") }\" }";

        // Send request to WireMock (which will serve the mocked response)
        ResponseEntity<String> response = restTemplate.postForEntity(GRAPHQL_ENDPOINT, query, String.class);

        // Verify the response contains the expected mocked data
        assertTrue(response.getBody().contains("Hello, Javed!"));
    }
}