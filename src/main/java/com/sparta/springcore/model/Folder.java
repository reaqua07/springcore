package com.sparta.springcore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Folder extends Timestamped {

    public Folder(String name, User user) {
        this.name = name;
        this.user = user;
    }

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String name;

    @ManyToOne // 한명의 유저는 여러 폴더를 가질 수 있다
    @JoinColumn(nullable = false)
    private User user;
    // db에는 userid로 저장되지만 여기서는 user 테이블과 연결되어있음을 나타내기 위해 user 객체가 됨
}