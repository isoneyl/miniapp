package com.mini.app.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName: ApiRequest
 * @Description: 统一入参实体
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ApiRequest<T> implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     *  token
     */
    private String token;

    /**
     *  用户id
     */
    private Integer userId;

    /**
     * 后台ID
     **/
    private Integer manageId;

    /**
     * 后台ID
     **/
    private Integer roleId;

    /**
     * 当前页数
     */
    private Integer pageNo = 1;

    /**
     * 每页条数
     */
    private Integer pageSize = 10;

    private T data;

}
