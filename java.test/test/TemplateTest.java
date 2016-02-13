package test;

import java.io.File;
import java.io.IOException;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.core.resource.FileResourceLoader;

public class TemplateTest {

    // public static void main(String[] args) {
    // VelocityEngine engine = TemplateTool.getVelocityEngine();
    // VelocityContext context = new VelocityContext();
    // StringWriter writer = new StringWriter();
    // engine.mergeTemplate(TemplateTool.TEMPLATE_PATH_BEAN, TemplateTool.ENCODING, context, writer);
    // System.out.println(writer.toString());
    //
    //
    // }

    // @Test
    public void beetlTemplateFileResourceLoaderTest() throws IOException {
        String root = System.getProperty("user.dir") + File.separator + "src" + File.separator + "template";
        System.out.println(root);
        FileResourceLoader resourceLoader = new FileResourceLoader(root, "utf-8");
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        Template t = gt.getTemplate("/template-javaBean.vm");
        String str = t.render();
        System.out.println(str);
    }

    // @Test
    public void beetlTemplateClasspathResourceLoaderTest() throws IOException {
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
        String root = resourceLoader.getRoot();
        System.out.println(root);
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        Template t = gt.getTemplate("/template/template-javaBean.vm");
        String str = t.render();
        System.out.println(str);
    }

}
