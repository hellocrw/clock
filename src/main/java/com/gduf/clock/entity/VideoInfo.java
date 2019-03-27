package com.gduf.clock.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "video_info")
@Builder
@Data
public class VideoInfo {
    @Id
    private String id;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "comment_map")
    private String commentMap;

    @Column(name = "video_path")
    private String videoPath;

    private Date time;
}