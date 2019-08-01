package com.wordUtil.util;

import com.wordUtil.bean.ExportVO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.*;


public class FreeMarkerHelper {

    public static final String UTF_8 = "UTF-8";
    public static volatile String TEMPLATE_DEFAULT_DOCX = "model/export_sample.docx";
    public static volatile String TEMPLATE_DEFAULT_XML = "model/document.xml";
    public static String ENTRY_NAME = "word/document.xml";
    // 缓存的Template
    private static ConcurrentHashMap<String, Template> cachedTemplateMap = new ConcurrentHashMap<>();
    /**
     * FreeMarker 的全局配置量,只应该存在1份
     * 不需要重复创建 Configuration 实例；
     * 它的代价很高，尤其是会丢失缓存(配置信息修改对多线程存在延迟)。
     * Configuration 实例就是应用级别的单例。
     */
    public static final Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
    private static final boolean hasInit = initConfiguration();

    private static boolean initConfiguration() {
        //
        cfg.setDefaultEncoding(UTF_8);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        //
        return true;
    }


    public static synchronized void parse2DocxAndClose(ExportVO bean, OutputStream outputStream){
        Map<String, Object> modelMap = new HashMap<>();
        if(null != bean){
            modelMap = BeanUtils.toMap(bean);
        }
        String templateURI = TEMPLATE_DEFAULT_XML;
        String docxTemplateURI = TEMPLATE_DEFAULT_DOCX;
        // 输出到 ByteArray(初始大小)
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16 * 1024);
        // 处理
        parse2OutputStream(modelMap, byteArrayOutputStream, templateURI);
        //
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        // 特定项目的输入流
        ByteArrayInputStream itemInputStream = new ByteArrayInputStream(byteArray);
        //
        InputStream docxInputStream = FreeMarkerHelper.class.getClassLoader().getResourceAsStream(docxTemplateURI);
        //
        ZipInputStream zipInputStream = ZipUtils.wrapZipInputStream(docxInputStream);
        ZipOutputStream zipOutputStream = ZipUtils.wrapZipOutputStream(outputStream);
        //
        String itemname = ENTRY_NAME;
        //
        try{
            ZipUtils.replaceItem(zipInputStream, zipOutputStream, itemname, itemInputStream);
        } finally {
            close(itemInputStream);
            close(zipInputStream);
            close(zipOutputStream);
        }



    }

    private static void parse2OutputStream(Map<String, Object> modelMap, OutputStream outputStream, String templateURI){
        //
        if(null == modelMap){
            close(outputStream);
            return;
        }
        try {
            if(null != modelMap){
                // Writter 必须指定编码
                Writer out = new BufferedWriter(new OutputStreamWriter(outputStream, UTF_8));
                //
                Template temp = getTemplateByURI(templateURI);
                //
                temp.process(modelMap, out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(outputStream);
        }
    }

    // 获取缓存
    private static Template getCachedTemplateByURI(String templateURI){
        //
        Template temp = cachedTemplateMap.get(templateURI);
        if(null == temp){
            temp = getTemplateByURI(templateURI);
        }
        //
        return temp;
    }
    // 获取缓存, 同步方法
    private static synchronized Template getTemplateByURI(String templateURI){
        //
        Template temp = cachedTemplateMap.get(templateURI);
        if(null != temp){
            return temp;
        }
        //
        String name = templateURI;//.substring(templateURI.lastIndexOf("/"));
        String sourceName = name;
        try {
            // reader 会自动被关闭
            InputStream inputStream = FreeMarkerHelper.class.getClassLoader().getResourceAsStream(templateURI);
            Reader reader = new InputStreamReader(inputStream, UTF_8);
            temp = new Template(name, sourceName, reader, cfg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        if(null != temp){
            cachedTemplateMap.put(templateURI, temp);
        }
        //
        return temp;
    }


    private static void close(InputStream inputStream){
        if (null != inputStream){
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static void close(OutputStream outputStream){
        if (null != outputStream){
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}