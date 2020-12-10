package com.mini.app.es.config;

import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author liyunlng
 * @ClassName: EsIndexConfig
 * @Description:
 * @date 2020/11/14
 */
@Component
public class EsIndexConfig {

    private final BBossESStarter bbossESStarter;

    final private String CHATINDEX = "wisdom_chat_msg";

    final private String NUMINDEX = "wisdom_chat_msg_num";

    public EsIndexConfig(BBossESStarter bbossESStarter) {
        this.bbossESStarter = bbossESStarter;
    }

    /**
     * 如果索引不存在则新建索引
     **/
    @PostConstruct
    public void createChatMsgIndex() {
        String CONFIG = "es/chatmsg.xml";
        boolean index = bbossESStarter.getRestClient().existIndice(CHATINDEX);

        if (!index) {
            System.err.println("创建索引");
            ClientInterface clientUtil = bbossESStarter.getConfigRestClient(CONFIG);
            String chatMsgMapping = clientUtil.createIndiceMapping(CHATINDEX, "chatMsgMapping");
            System.err.println(chatMsgMapping);
        }
    }

    @PostConstruct
    public void createChatMsgNumIndex() {
        String CONFIG = "es/chatmsgNum.xml";
        boolean index = bbossESStarter.getRestClient().existIndice(NUMINDEX);

        if (!index) {
            System.err.println("创建索引");
            ClientInterface clientUtil = bbossESStarter.getConfigRestClient(CONFIG);
            String chatMsgMapping = clientUtil.createIndiceMapping(NUMINDEX, "chatMsgNumMapping");
            System.err.println(chatMsgMapping);
        }
    }
}
