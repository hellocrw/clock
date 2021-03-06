package com.gduf.clock.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "image_info")
@Data
@Builder
public class ImageInfo {
    @Id
    private String id;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "daily_map")
    private String dailyMap;

    @Column(name = "img_path")
    private String imgPath;

    private Date time;

}