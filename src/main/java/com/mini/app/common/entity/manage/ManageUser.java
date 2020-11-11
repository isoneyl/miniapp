package com.mini.app.common.entity.manage;

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
 * @author liyunlng
 * @ClassName: ManageUser
 * @Description:
 * @date 2020/11/4
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Table(name = "tb_manage_user")
public class ManageUser implements Serializable {

    private static final long serialVersionUID = 2661235376943498822L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer manageId;

    private String manageAccound;

    private String manageName;

    private String phone;

    private String managePwd;

    private String email;

    private Integer gender;

    private Date createTime;

    @Transient
    private String roleName;

    @Transient
    private Integer roleId;

}
