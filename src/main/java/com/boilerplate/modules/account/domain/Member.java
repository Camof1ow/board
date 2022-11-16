package com.boilerplate.modules.account.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Getter
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false,unique = true)
  private String nickname;

  @Column(nullable = false,unique = true)
  private String email;

  @Column(nullable = false)
  @JsonIgnore
  private String password;

  @Column
  private Long point;

  @Column(nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @Enumerated(value = EnumType.STRING) //DB갈 때 올 때 값을 String으로 변환해줘야함
  private RoleEnum role;

//  @OneToOne
  private Long ranking;

  public void updateRole(RoleEnum role){
    this.role = role;
  }


}
