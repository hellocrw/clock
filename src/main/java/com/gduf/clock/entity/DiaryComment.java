package com.gduf.clock.entity;

import lombok.Builder;

import java.util.Date;
import javax.persistence.*;

@Table(name = "diary_comment")
@Builder
public class DiaryComment {
    @Id
    private String id;

    @Id
    private Date time;

    @Column(name = "open_id")
    private String openId;

    /**
     * 附加文件的对应关系
     */
    @Column(name = "comment_map")
    private String commentMap;

    private String comment;

}