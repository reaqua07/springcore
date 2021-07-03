package com.sparta.springcore.service;

import com.sparta.springcore.dto.ProductMypriceRequestDto;
import com.sparta.springcore.dto.ProductRequestDto;
import com.sparta.springcore.model.Product;
import com.sparta.springcore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
public class ProductService {
    // 멤버 변수 선언
    private final ProductRepository productRepository;

    // 생성자: ProductService() 가 생성될 때 호출됨
    @Autowired              // IoC의 객체를 DI 받겠다
    public ProductService(ProductRepository productRepository) {
        // 멤버 변수 생성
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() throws SQLException {
        // 멤버 변수 사용
        return productRepository.findAll();
    }

    public Product createProduct(ProductRequestDto requestDto) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto);
        productRepository.save(product);
        return product;
    }

    @Transactional
    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto) {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );

        int myPrice = requestDto.getMyprice();

        // 기초 수업 버전
        product.updateMyPrice(myPrice);

        product.setMyprice(myPrice);
        // transactional 이 있으면,, 값이 변화할 때 자동으로 save 한다다
//       productRepository.save(product);
        return product;
    }
}