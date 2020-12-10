package com.mini.app.es.entity;

import com.frameworkset.orm.annotation.ESId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Transient;
import java.util.Date;

/**
 * @author liyunlng
 * @ClassName: EsChatMsg
 * @Description:
 * @date 2020/11/14
 */
@AllArgsConstructor
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class EsChatMsg2 {

    private static final long serialVersionUID = 1L;

    @ESId(persistent = false)
    private String id;  //主键

    private Integer projectId;//项目id

    private Integer MsgType;// 消息类型

    private String context;//消息主体

    private Integer fileId; //关联id

    private Integer sendId; //发送人

    private Date sendTime;//发送时间

    private String fileName;//文件名称

    private String fileType;// 文件类型

    private String rfsId; //文件的rfsId

    private String chatText; //无html的文件内容

    private Integer paperId; //底稿文档的主键 ，只有在讨论是底稿文件的时候才有值

    private Integer orgId; //组织ID

    private String chatContext; // 消息内容


}
