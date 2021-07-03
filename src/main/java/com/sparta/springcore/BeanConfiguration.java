package com.sparta.springcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 스프링이 실행될때 먼저 살펴보라고 표시(객체 생성이 되어야 DI가 가능함)
public class BeanConfiguration {
    @Bean                       // IoC가 구분할 수 있는 이름  = 함수명
    public ProductRepository productRepository() {
        String dbId = "sa";
        String dbPassword = "";
        String dbUrl = "jdbc:h2:mem:springcoredb";
        return new ProductRepository(dbId, dbPassword, dbUrl);
    }

    @Bean
    @Autowired  // IoC에 있는 DI를 사용하겠다 표시
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }
}