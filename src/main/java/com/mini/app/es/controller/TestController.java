package com.mini.app.es.controller;

import com.mini.app.es.entity.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.frameworkset.elasticsearch.entity.RestResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author liyunlng
 * @ClassName: TestController
 * @Description:
 * @date 2020/11/13
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private BBossESStarter bbossESStarter;

    final private String CONFIG = "es/chatmsg.xml";

    final private String NUM_CONFIG = "es/chatmsgNum.xml";

    final private String DOCMENT = "/_doc";

    final private String INDEX = "wisdom_chat_msg";

    final private String NUMINDEX = "wisdom_chat_msg_num";

    final private String NUM_SEARCH = NUMINDEX + "/_search";

    final private String CHAT_SEARCH = INDEX + "/_search";
    /**
     * @Author liyunlong
     * @Description 添加ES聊天数据
     * @Date 10:06 2020/11/16
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/esChat")
    @ResponseBody
    public String insertEsChatMsg() {
        ClientInterface clientUtil = bbossESStarter.getConfigRestClient(CONFIG);

        EsChatMsg2 esChatMsg2 = new EsChatMsg2();
        esChatMsg2.setContext("我是你哥")
                .setProjectId(1)
                .setSendId(1)
                .setMsgType(2)
                .setSendTime(new Date());

        EsChatMsg esChatMsg = new EsChatMsg();
        BeanUtils.copyProperties(esChatMsg2, esChatMsg);
        esChatMsg.setSendTime(esChatMsg2.getSendTime().getTime());
        String s = clientUtil.addDocument(INDEX, DOCMENT, esChatMsg);

        System.err.println(s);
        return s;
    }


    /**
     * @Author liyunlong
     * @Description 添加项目聊天未读数
     * @Date 10:08 2020/11/16
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/esNum")
    @ResponseBody
    public String insertEsChatNum() {
        ClientInterface clientUtil = bbossESStarter.getRestClient();

        EsChatMsgNum esChatMsgNum = new EsChatMsgNum();
        esChatMsgNum.setUserId(1)
                .setProjectId(1)
                .setNum(100);
        String s = clientUtil.addDocument(NUMINDEX, DOCMENT, esChatMsgNum);

        return s;
    }

    /**
     * @Author liyunlong
     * @Description 查询未读数量
     * @Date 10:08 2020/11/16
     * @Param []
     * @return java.util.List<com.mini.app.es.entity.EsChatMsgVo>
     **/
    @RequestMapping("/esNumQuery")
    @ResponseBody
    public List<EsChatMsgVo> queryEsChatNum() {
        ClientInterface clientUtil = bbossESStarter.getConfigRestClient(NUM_CONFIG);

        EsChatMsgNum esChatMsgNum = new EsChatMsgNum();
        esChatMsgNum.setUserId(2)
                .setNum(0);

        ESDatas<EsChatMsgVo> searchChatNum
                = clientUtil.searchList(NUM_SEARCH, "searchChatNum", esChatMsgNum, EsChatMsgVo.class);

        return searchChatNum.getDatas();
    }

    /**
     * @Author liyunlong
     * @Description 更新ES项目聊天未读数
     * @Date 10:09 2020/11/16
     * @Param []
     * @return com.mini.app.es.entity.EsChatMsgNum
     **/
    @RequestMapping("/esNumUpdate")
    @ResponseBody
    public EsChatMsgNum updateEsNumTpZero() {
        ClientInterface clientUtil = bbossESStarter.getConfigRestClient(NUM_CONFIG);

        Map<String, Object> map = new HashMap<>();
        map.put("userId", 111);
        map.put("projectId", 111);
        map.put("orgId", 0);
        EsChatMsgNum chatNum =
                clientUtil.searchObject(NUM_SEARCH, "searchChatNum", map, EsChatMsgNum.class);

        Integer num = 0;
        if (chatNum != null && chatNum.getNum() != null) {
            chatNum.setNum(chatNum.getNum()+1);
        } else {
            chatNum = new EsChatMsgNum();
            chatNum.setNum(num).setProjectId(111).setUserId(111 );
        }
        clientUtil.addDocument(NUMINDEX, DOCMENT, chatNum);
        return chatNum;
    }

    /**
     * @Author liyunlong
     * @Description 搜索项目聊天通用
     * @Date 15:06 2020/11/16
     * @Param []
     * @return java.util.List<com.mini.app.es.entity.EsChatMsgVo>
     **/
    @RequestMapping("/searchChat")
    @ResponseBody
    public ESDatas<EsChatMsgVo> searchChat() {
        Integer pageNo = 0;
        Integer pageSize = 10;
        // 有需要关联人员的
        EsChatMsgQuery esChatMsg = new EsChatMsgQuery();
        esChatMsg.setProjectId(79).setOrgId(0).setPageNum(pageNo).setPageSize(pageSize);
        esChatMsg.setSortName("sendTime").setSortType("desc")
        .setChatText("难受");

        ClientInterface clientUtil = bbossESStarter.getConfigRestClient(CONFIG);

        ESDatas<EsChatMsgVo> msgs
                = clientUtil.searchList(CHAT_SEARCH, "searchChatMsg", esChatMsg, EsChatMsgVo.class);

        return msgs;
    }

    @RequestMapping("/readChat")
    @ResponseBody
    public String readChat() {
        ClientInterface clientUtil = bbossESStarter.getConfigRestClient(NUM_CONFIG);
        Map<String, Object> map = new HashMap<>();
        map.put("projectId", 79);
        map.put("orgId", 0);
        map.put("userId", 71);
        String num = clientUtil.updateByQuery(NUMINDEX+"/_update_by_query", "updateChatNumToZero", map);
        return num;
    }

    @RequestMapping("/chatLocation")
    @ResponseBody
    public List<EsChatMsgVo> chatLocation() {
        EsChatMsgVo esChatMsg = new EsChatMsgVo();
        esChatMsg.setProjectId(222).setOrgId(0).setPageNum(null).setSource(false);
        esChatMsg.setSortName("sendTime").setSortType("desc");

        ClientInterface clientUtil = bbossESStarter.getConfigRestClient(CONFIG);

        ESDatas<EsChatMsgVo> msgs
                = clientUtil.searchList(CHAT_SEARCH, "searchChatMsg", esChatMsg, EsChatMsgVo.class);

        List<EsChatMsgVo> datas = msgs.getDatas();

        return datas;
    }

    @RequestMapping("/queryFileId")
    @ResponseBody
    public List<EsChatMsgVo> queryFileId() {
        EsChatMsgVo esChatMsg = new EsChatMsgVo();
        esChatMsg.setProjectId(222).setOrgId(0).setMsgType(2).setPageNum(null);

        ClientInterface clientUtil = bbossESStarter.getConfigRestClient(CONFIG);

        ESDatas<EsChatMsgVo> msgs
                = clientUtil.searchList(CHAT_SEARCH, "searchChatMsg", esChatMsg, EsChatMsgVo.class);

        List<EsChatMsgVo> datas = msgs.getDatas();

        return datas;
    }

    @RequestMapping("/queryFileIdByScrllId")
    @ResponseBody
    public List<EsChatMsgVo> queryFileIdByScrllId() throws InterruptedException {
        Thread.sleep(1000 * 60 * 20);
        EsChatMsgVo esChatMsg = new EsChatMsgVo();
        esChatMsg.setProjectId(79).setOrgId(0);

        ClientInterface clientUtil = bbossESStarter.getConfigRestClient(CONFIG);

        ESDatas<EsChatMsgVo> msgs
                = clientUtil.searchList(CHAT_SEARCH + "?scroll=1m", "queryFileIdByScrllId", esChatMsg, EsChatMsgVo.class);

        String scrollId = msgs.getScrollId();
        List<EsChatMsgVo> datas = msgs.getDatas();
        System.err.println(scrollId);
        System.err.println(datas.size());
        if (CollectionUtils.isNotEmpty(datas)) {
            while (true) {
                ESDatas<EsChatMsgVo> eSDatas
                        = clientUtil.searchScroll("1m", scrollId, EsChatMsgVo.class);
                List<EsChatMsgVo> datas1 = eSDatas.getDatas();

                if (CollectionUtils.isEmpty(datas1))  {
                    break;
                }
                datas.addAll(datas1);
            }
        }
        System.err.println(datas.size());

        return datas;
    }

}
