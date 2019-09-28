package com.kakao.mis.tire.webflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebFluxConfig implements WebFluxConfigurer {

  public void addCorsMappings(CorsRegistry registry) {
    registry
      .addMapping("/hello/**")
      .allowedOrigins("http://localhost:8080")
      .allowedMethods(HttpMethod.GET.name())
      .allowCredentials(true);
  }

  @Bean
  public CorsWebFilter corsFilter() {
    CorsConfiguration config = new CorsConfiguration();

    config.setAllowCredentials(true);
    config.addAllowedOrigin("http://localhost:8080");
    config.addAllowedMethod(HttpMethod.GET.name());

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/hello/**", config);

    return new CorsWebFilter(source);
  }
}
