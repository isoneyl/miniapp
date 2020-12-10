package com.mini.app.es.entity;

import com.frameworkset.orm.annotation.ESId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author yangjianfeng
 * @ClassName: ChatMsgNum
 * @Description: 项目聊天未读数量Dao
 * @date 2019/08/12
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class EsChatMsgNum implements Serializable {
    private static final long serialVersionUID = 1L;

    @ESId(persistent = false)
    private String id;
    private Integer projectId;
    private Integer userId;
    private Integer num;
}
