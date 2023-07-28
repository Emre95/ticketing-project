package com.cydeo.entity;

import com.cydeo.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
//@Where(clause = "is_deleted=false")
public class User extends BaseEntity{

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String userName;
    private String phone;
    private String password;
//    private String confirmPassword;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @ManyToOne
    private Role role;

}
