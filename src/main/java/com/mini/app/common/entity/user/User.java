package com.mini.app.common.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: User
 * @Description: 用户信息实体类
 * @date 2020/10/21
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Table(name = "tb_sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 2669315376943498824L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer userId;

    private String userName;

    private String stuNum;

    private String phone;

    private Integer sex;

    private String addr;

    private String classRoom;

    private String openid;

    private String unionid;

    private String imgUrl;

    private Date createTime;

    private Date updateTime;

    @Transient
    private String token;

}
