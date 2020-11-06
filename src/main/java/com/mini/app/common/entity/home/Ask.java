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
 * @ClassName: ASK
 * @Description:
 * @date 2020/11/6
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Table(name = "tb_home_ask")
public class Ask {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer	id;

    /**
     * 发布机构
     */
    private String	publishOrg;

    /**
     * 作者ID
     */
    private Integer	manageId;

    /**
     * 作者
     */
    private String	manageName;

    /**
     * 内容
     */
    private String	content;

    /**
     * 创建时间
     */
    private Date createTime;
}
