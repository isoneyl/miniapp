package com.mini.app.common.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author liyunlng
 * @ClassName: UserRole
 * @Description:
 * @date 2020/10/31
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Table(name = "tb_sys_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 213213123123123123L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private Integer userId;

    private Integer roleId;
}
