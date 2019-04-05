package com.gduf.clock.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_info")
@Data
@Builder
public class UserInfo {
    @Id
    @Column(name = "open_id")
    private String openId;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "avatar_url")
    private String avatarUrl;

    private String gender;

    private String country;

    private String province;

    private String city;

    private String language;

    private Date time;

}