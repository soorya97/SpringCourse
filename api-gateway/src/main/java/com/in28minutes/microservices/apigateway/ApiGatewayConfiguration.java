package com.in28minutes.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder){

		return builder.routes()
				//http://localhost:8765/get
				.route(p -> p.path("/get")
						.filters(f -> f
							.addRequestHeader("MyHeader", "MyURI")
							.addRequestParameter("Param", "MyValue"))
						.uri("http://httpbin.org:80"))

				//lb = load balancer
				//http://localhost:8765/currency-exchange/from/USD/to/INR
				.route(p -> p.path("/currency-exchange/**")
						.uri("lb://currency-exchange"))

				//http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/10
				.route(p -> p.path("/currency-conversion/**")
						.uri("lb://currency-conversion"))

				//http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/10
				.route(p -> p.path("/currency-conversion-feign/**")
						.uri("lb://currency-conversion"))

				//http://localhost:8765/currency-conversion-new/from/USD/to/INR/quantity/10
				.route(p -> p.path("/currency-conversion-new/**")
						.filters(f -> f.rewritePath(
								"/currency-conversion-new/",
								"/currency-conversion-feign/"
						))
						.uri("lb://currency-conversion"))

				.build();
	}
}
