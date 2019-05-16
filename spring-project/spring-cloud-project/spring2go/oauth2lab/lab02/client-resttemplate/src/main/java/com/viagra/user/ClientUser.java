package com.viagra.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@Setter
public class ClientUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String accessToken;

    private Calendar accessTokenValidity;

    private String refreshToken;

    /*
    * @Transient 希望该属性不会在数据表中产生字段，但又可以在程序中使用它
    * */
    @Transient
    private List<Entry> entries = new ArrayList<>();

}
