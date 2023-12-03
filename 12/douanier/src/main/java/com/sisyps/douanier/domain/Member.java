package com.sisyps.douanier.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;
    private String username;
    private String password;
    //필수 이용 약관 동의 내역
    @ElementCollection
    private Map<String, Boolean> mandatoryTerms;
    //선택 이용 약관 동의 내역
    @ElementCollection
    private Map<String, Boolean> additionalTerms;
}
