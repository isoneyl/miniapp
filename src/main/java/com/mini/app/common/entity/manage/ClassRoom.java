package com.mini.app.common.entity.manage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author liyunlng
 * @ClassName: ClassRoom
 * @Description:
 * @date 2020/11/5
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Table(name = "tb_manage_class")
public class ClassRoom {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer classId;

    private String classRoom;

    private Integer collegeId;

    private String collegeName;

    private Date createTime;

}
