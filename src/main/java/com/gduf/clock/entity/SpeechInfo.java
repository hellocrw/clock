package com.gduf.clock.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "speech_info")
@Builder
@Data
public class SpeechInfo {
    @Id
    private String id;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "comment_map")
    private String commentMap;

    @Column(name = "speech_path")
    private String speechPath;

    private Date time;

}