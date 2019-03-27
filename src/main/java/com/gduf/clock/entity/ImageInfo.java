package com.gduf.clock.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "image_info")
@Builder
@Data
public class ImageInfo {
    @Id
    private String id;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "comment_map")
    private String commentMap;

    @Column(name = "img_path")
    private String imgPath;

    private Date time;

}