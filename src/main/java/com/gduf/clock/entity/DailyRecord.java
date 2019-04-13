package com.gduf.clock.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "daily_record")
@Data
@Builder
public class DailyRecord {
    @Id
    private String id;

    @Column(name = "daily_map")
    private String dailyMap;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "insist_day")
    private Integer insistDay;

    private Date time;

    private String content;

}