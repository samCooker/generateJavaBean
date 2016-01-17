package generatejavabean.tools;

import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class TemplateTool {

	public static final String PROPERTY_PATH_TYPE = "file.resource.loader.class";
	public static final String PROPERTY_PATH_VAL = "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader";
	public static final String ENCODING = "utf-8";
	public static final String TEMPLATE_PATH_BEAN = "template/template-javaBean.vm";
	public static final String TEMPLATE_PATH_REPOSITORY = "template/template-repository.vm";
	public static final String TEMPLATE_PATH_SERVICE = "template/template-service.vm";
	public static final String TEMPLATE_PATH_SERVICEIMPL = "template/template-serviceImpl.vm";

	/**
	 * ��ʼ��ģ�����ü����������ļ��ĸ�Ŀ¼
	 */
	public static void initVelocityProperties() {
		Properties p = new Properties();
		// ��ȡ��·��
		p.setProperty(PROPERTY_PATH_TYPE, PROPERTY_PATH_VAL);
		p.setProperty(Velocity.ENCODING_DEFAULT, ENCODING);
		p.setProperty(Velocity.INPUT_ENCODING, ENCODING);
		p.setProperty(Velocity.OUTPUT_ENCODING, ENCODING);
		Velocity.init(p);
	}

	public static VelocityEngine getVelocityEngine() {
		Properties properties = new Properties();
		// ����velocity��Դ���ط�ʽΪclass
		properties.setProperty("resource.loader", "class");
		// ����velocity��Դ���ط�ʽΪfileʱ�Ĵ�����
		properties.setProperty("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		return new VelocityEngine(properties);
	}

	/**
	 * ��ʼ��Repositoryģ�漰���ɵ��ļ�·��
	 * 
	 * @return
	 */
	public static Template initRepositoryTemplateAndFilePath() {
		initVelocityProperties();
		return Velocity.getTemplate(TEMPLATE_PATH_REPOSITORY);
	}

	/**
	 * ��ʼ��serviceģ�漰���ɵ��ļ�·��
	 * 
	 * @return
	 */
	public static Template initServiceTemplateAndFilePath() {
		initVelocityProperties();
		return Velocity.getTemplate(TEMPLATE_PATH_SERVICE);
	}

	/**
	 * ��ʼ��serviceImplģ�漰���ɵ��ļ�·��
	 * 
	 * @return
	 */
	public static Template initServiceImplTemplateAndFilePath() {
		initVelocityProperties();
		return Velocity.getTemplate(TEMPLATE_PATH_SERVICEIMPL);
	}
}
