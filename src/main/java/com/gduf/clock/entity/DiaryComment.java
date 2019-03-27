package com.gduf.clock.entity;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

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