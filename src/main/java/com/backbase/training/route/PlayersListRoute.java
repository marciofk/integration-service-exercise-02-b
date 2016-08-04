package com.backbase.training.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class PlayersListRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("restlet:/player/listPlayers?restletMethods=GET&amp;restletBinding=#queryStringToHeadersRestletBinding")
                .routeId("com.backbase.training.exercise02b")
                .removeHeaders("CamelHttp*", "CamelHttpMethod")
                .to("http://localhost:9999/training-server/rest/player/players")
                .unmarshal().json(JsonLibrary.Jackson)
                .processRef("playerSort")
                .marshal().json(JsonLibrary.Jackson);

    }
}
