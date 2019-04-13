package com.gduf.clock.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "speech_info")
@Data
@Builder
public class SpeechInfo {
    @Id
    private String id;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "daily_map")
    private String dailyMap;

    @Column(name = "speech_path")
    private String speechPath;

    private Date time;


}