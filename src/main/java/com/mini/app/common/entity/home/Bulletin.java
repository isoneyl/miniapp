package com.mini.app.common.entity.home;

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
 * @ClassName: Bulletin
 * @Description:
 * @date 2020/11/6
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Table(name = "tb_home_bulletin")
public class Bulletin {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer	id;

    private String	bulletinTitle;

    private String	publishOrg;

    private Integer	manageId;

    private String	manageName;

    private String	content;

    private Date createTime;

}
