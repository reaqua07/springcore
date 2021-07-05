package com.sparta.springcore.repository;

import com.sparta.springcore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByUserId(Long userId, Pageable pageable);

    // 2. 상품명이 title인 관심상품 1개 조회
//    Product findByTitle(String title);

    // 3. 상품명에 word가 포함된 모든 상품들 조회
//    List<Product> findAllByTitleContaining(String word);

    // 4. 최저가가 fromPrice ~ toPrice 인 모든 상품들을 조회
//    List<Product> findAllByLpriceBetween(int fromPrice, int toPrice);
}