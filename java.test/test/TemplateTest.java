package test;

import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import generatejavabean.tools.TemplateTool;

public class TemplateTest {

	public static void main(String[] args) {
		VelocityEngine engine = TemplateTool.getVelocityEngine();
		VelocityContext context = new VelocityContext();
		StringWriter writer = new StringWriter();
		engine.mergeTemplate(TemplateTool.TEMPLATE_PATH_BEAN, TemplateTool.ENCODING, context, writer);
		System.out.println(writer.toString());
	}
}
