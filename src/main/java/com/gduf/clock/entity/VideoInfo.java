package com.gduf.clock.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "video_info")
@Data
@Builder
public class VideoInfo {
    @Id
    private String id;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "daily_map")
    private String dailyMap;

    @Column(name = "video_path")
    private String videoPath;

    private Date time;


}