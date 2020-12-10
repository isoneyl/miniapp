package com.mini.app.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.frameworkset.elasticsearch.entity.ESBaseData;

import java.util.List;
import java.util.Map;

/**
 * @author liyunlng
 * @ClassName: EsChatMsgVo
 * @Description:
 * @date 2020/11/14
 */
@AllArgsConstructor
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class EsChatMsgQuery extends ESBaseData {

    private static final long serialVersionUID = 1L;


    private Integer projectId;//项目id

    private Integer msgType;// 消息类型

    private String context;//消息主体

    private Integer fileId; //关联id

    private Integer sendId; //发送人

    private long sendTime;//发送时间

    private String fileName;//文件名称

    private String fileType;// 文件类型

    private String rfsId; //文件的rfsId

    private String chatText; //无html的文件内容

    private Integer paperId; //底稿文档的主键 ，只有在讨论是底稿文件的时候才有值

    private Integer orgId = 0; //组织ID

    private Integer fileSource; // 是否是底稿文件定位 0 是  1 不是

    private String sortName; // ES排序使用

    private String sortType;// ES排序使用

    private Integer pageNum;

    private Integer pageSize;

    private Boolean source; // 是否返回所有字段; null不返回

    private String chatContext; // 消息内容



}
