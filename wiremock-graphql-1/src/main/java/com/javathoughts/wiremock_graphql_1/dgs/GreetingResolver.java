package com.javathoughts.wiremock_graphql_1.dgs;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;

@DgsComponent
public class GreetingResolver {

    @DgsQuery
    public String getGreeting(String name) {
        return "Hello, " + name + "!";
    }
}