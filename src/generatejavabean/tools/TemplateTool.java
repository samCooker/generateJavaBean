package generatejavabean.tools;

import java.io.IOException;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

public class TemplateTool {

    public static final String      ENCODING                  = "utf-8";
    public static final String      DEFAULT_BEAN_FOLDER       = "domain";
    public static final String      DEFAULT_REPOSITORY_FOLDER = "repository";
    public static final String      DEFAULT_SERVICE_FOLDER    = "service";
    public static final String      TEMPLATE_PATH_BEAN        = "/template/template-javaBean.js";
    public static final String      TEMPLATE_PATH_REPOSITORY  = "/template/template-repository.js";
    public static final String      TEMPLATE_PATH_SERVICE     = "/template/template-service.js";
    public static final String      TEMPLATE_PATH_SERVICEIMPL = "/template/template-serviceImpl.js";

    private ClasspathResourceLoader resourceLoader            = null;
    private Configuration           cfg                       = null;
    private GroupTemplate           gt                        = null;

    public TemplateTool() {
        super();
        initTemplate();
    }

    /**
     * 初始化模板(可自定义模板基础配置等信息)
     */
    public void initTemplate() {
        this.resourceLoader = new ClasspathResourceLoader();
        try {
            this.cfg = Configuration.defaultConfiguration();
            this.cfg.setCharset(ENCODING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.gt = new GroupTemplate(resourceLoader, cfg);

    }

    /**
     * 获取javabean模板
     * 
     * @return
     */
    public Template getTemplate4Bean() {
        return this.gt.getTemplate(TEMPLATE_PATH_BEAN);
    }

    /**
     * 获取repository模板
     * 
     * @return
     */
    public Template getTemplate4Repository() {
        return this.gt.getTemplate(TEMPLATE_PATH_REPOSITORY);
    }

    /**
     * 获取service模板
     * 
     * @return
     */
    public Template getTemplate4Service() {
        return this.gt.getTemplate(TEMPLATE_PATH_SERVICE);
    }

    /**
     * 获取serviceImpl模板
     * 
     * @return
     */
    public Template getTemplate4ServiceImpl() {
        return this.gt.getTemplate(TEMPLATE_PATH_SERVICEIMPL);
    }
}
