package com.abumaster.middleware.rmdb.service;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 功能详细描述
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2020/11/19
 */
@Slf4j
public class XmlUtilTest {


    @Test
    public void readFile() throws Exception{
        String path="C:\\Users\\zgf\\Desktop\\result.xml";
        File file = new File(path);
        Document document = DocumentHelper.parseText(FileUtil.readString(file,"utf-8"));

        // 读取带有命名空间的xml文件
        //bar(document);
        bar2(document);
    }


    public void bar2(Document document) throws Exception{
        Map map=new HashMap();
        map.put("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
        map.put("ns","http://server.webservice.dsp.dm.com");
        String path="C:\\Users\\zgf\\Desktop\\result.xml";
        SAXReader reader = new SAXReader();
        reader.getDocumentFactory().setXPathNamespaceURIs(map);
        Document doc = reader.read(new File(path));
        Node node = doc.selectSingleNode("//soapenv:Envelope/soapenv:Body/ns:requestResponse/ns:return");

        // 读取响应的数据
        Document doc2 = DocumentHelper.parseText(node.getStringValue());
        String id = doc2.selectSingleNode("//response/head/response_id").getStringValue();
        String status=doc2.selectSingleNode("//response/head/status_code").getStringValue();
        log.info("id:{}, code:{}", id,status);

        String data = doc2.selectSingleNode("//response/result").getStringValue();
        log.info("数据:{}",data);

        // 读取下一层真正有用的数据
        Document doc3 = DocumentHelper.parseText(data);
        String[] items={"XM", "SFZH", "CSRQ", "XB", "MZ", "HYZK",
                "WHCD", "JGSSX", "ZZXZ", "CSD", "CSDXZ", "FWCS",
                "CYM", "BYQK", "XP"};
        Map<String, Object> result = new HashMap<>();
        for(String item:items) {
            result.put(item, doc3.selectSingleNode(String.format("//row/%s",item)).getStringValue());
        }

        log.info("{}",result.keySet().toArray());

    }
}
