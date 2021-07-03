package com.sparta.springcore.model;

import com.sparta.springcore.dto.ProductRequestDto;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Nested // 클래스 내부 클래스 임을 나타냄 // 사용 이유 : test를 돌렸을 때 보기 좋게 구조화 하기 위해
    @DisplayName("회원이 요청한 관심상품 객체 생성")
    class CreateUserProduct {
        // 내부에서도 자유로운 사용을 위한 멤버변수 선언
        private Long userId;
        private String title;
        private String image;
        private String link;
        private int lprice;

        @BeforeEach     // 모든 test 케이스에 공통적으로 들어갈 내용을 담음 // 각 테스트에서 변경되는 부분이 있다면 그곳에서 따로 변경 가능
        void setup() {
            userId = 100L;
            title = "오리온 꼬북칩 초코츄러스맛 160g";
            image = "https://shopping-phinf.pstatic.net/main_2416122/24161228524.20200915151118.jpg";
            link = "https://search.shopping.naver.com/gate.nhn?id=24161228524";
            lprice = 2350;
        }

        @Test
        @DisplayName("정상 케이스")
        void createProduct_Normal() {
            // given
            ProductRequestDto requestDto = new ProductRequestDto(
                    title,
                    image,
                    link,
                    lprice
            );

            // when
            Product product = new Product(requestDto, userId);

            // then
            assertNull(product.getId());
            assertEquals(userId, product.getUserId());
            assertEquals(title, product.getTitle());
            assertEquals(image, product.getImage());
            assertEquals(link, product.getLink());
            assertEquals(lprice, product.getLprice());
            assertEquals(0, product.getMyprice());
        }

        @Nested
        @DisplayName("실패 케이스")
        class FailCases {
            @Nested
            @DisplayName("회원 Id")
            class userId {
                @Test
                @DisplayName("null")
                void fail1() {
                    // given
                    userId = null;      // 공통 주입 영역에서 userId 만 다르게 주는 것

                    ProductRequestDto requestDto = new ProductRequestDto(
                            title,
                            image,
                            link,
                            lprice
                    );

                    // when
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        new Product(requestDto, userId);
                    });

                    // then
                    assertEquals("회원 Id 가 유효하지 않습니다.", exception.getMessage());
                }

                @Test
                @DisplayName("마이너스")
                void fail2() {
                    // given
                    userId = -100L;

                    ProductRequestDto requestDto = new ProductRequestDto(
                            title,
                            image,
                            link,
                            lprice
                    );

                    // when
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        new Product(requestDto, userId);
                    });

                    // then
                    assertEquals("회원 Id 가 유효하지 않습니다.", exception.getMessage());
                }
            }

            @Nested
            @DisplayName("상품명")
            class Title {
                @Test
                @DisplayName("null")
                void fail1() {
                    // given
                    title = null;

                    ProductRequestDto requestDto = new ProductRequestDto(
                            title,
                            image,
                            link,
                            lprice
                    );

                    // when
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        new Product(requestDto, userId);
                    });

                    // then
                    assertEquals("저장할 수 있는 상품명이 없습니다.", exception.getMessage());
                }

                @Test
                @DisplayName("빈 문자열")
                void fail2() {
                    // given
                    String title = "";

                    ProductRequestDto requestDto = new ProductRequestDto(
                            title,
                            image,
                            link,
                            lprice
                    );

                    // when
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        new Product(requestDto, userId);
                    });

                    // then
                    assertEquals("저장할 수 있는 상품명이 없습니다.", exception.getMessage());
                }
            }

            @Nested
            @DisplayName("상품 이미지 URL")
            class Image {
                @Test
                @DisplayName("null")
                void fail1() {
                    // given
                    image = null;

                    ProductRequestDto requestDto = new ProductRequestDto(
                            title,
                            image,
                            link,
                            lprice
                    );

                    // when
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        new Product(requestDto, userId);
                    });

                    // then
                    assertEquals("상품 이미지 URL 포맷이 맞지 않습니다.", exception.getMessage());
                }

                @Test
                @DisplayName("URL 포맷 형태가 맞지 않음")
                void fail2() {
                    // given
                    image = "shopping-phinf.pstatic.net/main_2416122/24161228524.20200915151118.jpg";

                    ProductRequestDto requestDto = new ProductRequestDto(
                            title,
                            image,
                            link,
                            lprice
                    );

                    // when
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        new Product(requestDto, userId);
                    });

                    // then
                    assertEquals("상품 이미지 URL 포맷이 맞지 않습니다.", exception.getMessage());
                }
            }

            @Nested
            @DisplayName("상품 최저가 페이지 URL")
            class Link {
                @Test
                @DisplayName("null")
                void fail1() {
                    // given
                    link = "https";

                    ProductRequestDto requestDto = new ProductRequestDto(
                            title,
                            image,
                            link,
                            lprice
                    );

                    // when
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        new Product(requestDto, userId);
                    });

                    // then
                    assertEquals("상품 최저가 페이지 URL 포맷이 맞지 않습니다.", exception.getMessage());
                }

                @Test
                @DisplayName("URL 포맷 형태가 맞지 않음")
                void fail2() {
                    // given
                    link = "https";

                    ProductRequestDto requestDto = new ProductRequestDto(
                            title,
                            image,
                            link,
                            lprice
                    );

                    // when
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        new Product(requestDto, userId);
                    });

                    // then
                    assertEquals("상품 최저가 페이지 URL 포맷이 맞지 않습니다.", exception.getMessage());
                }
            }

            @Nested
            @DisplayName("상품 최저가")
            class LowPrice {
                @Test
                @DisplayName("0")
                void fail1() {
                    // given
                    lprice = 0;

                    ProductRequestDto requestDto = new ProductRequestDto(
                            title,
                            image,
                            link,
                            lprice
                    );

                    // when
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        new Product(requestDto, userId);
                    });

                    // then
                    assertEquals("상품 최저가가 0 이하입니다.", exception.getMessage());
                }

                @Test
                @DisplayName("음수")
                void fail2() {
                    // given
                    lprice = -1500;

                    ProductRequestDto requestDto = new ProductRequestDto(
                            title,
                            image,
                            link,
                            lprice
                    );

                    // when
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        new Product(requestDto, userId);
                    });

                    // then
                    assertEquals("상품 최저가가 0 이하입니다.", exception.getMessage());
                }
            }
        }
    }
}