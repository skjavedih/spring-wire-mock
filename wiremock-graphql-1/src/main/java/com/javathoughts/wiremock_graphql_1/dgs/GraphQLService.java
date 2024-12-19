package com.javathoughts.wiremock_graphql_1.dgs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;

import java.util.HashMap;
import java.util.Map;

@Service
public class GraphQLService {

    private final RestTemplate restTemplate;

    // URL for the GraphQL server (typically localhost during local testing)
    @Value("${graphql.endpoint}")
    private String graphqlEndpoint;

    public GraphQLService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String executeGraphQLQuery(String query) {
        // Create the GraphQL query payload
        String graphqlRequest = "{ \"query\": \"" + query + "\" }";

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("query", query);

        // Create the HttpHeaders and set the content-type to application/json
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Create an HttpEntity with the request body and headers
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        // Send the request to the /graphql endpoint
        ResponseEntity<String> response = restTemplate.postForEntity(graphqlEndpoint, entity, String.class);

        return response.getBody();
    }
}