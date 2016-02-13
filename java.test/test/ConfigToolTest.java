package test;

import generatejavabean.tools.ConfigTools;

public class ConfigToolTest {

    // @Test
    public void testConvertToPackageName() {
        String src = "F:\\workspaces\\sts\\multiJavaPrj\\base\\main\\abc\\dbToJavaBean\\aaa";
        String str = ConfigTools.getPackageName(src);
        System.out.println(str);
    }

    // @Test
    public void testPackageName() {
        String src = "1";
        String str = ConfigTools.replaceLastPackageName(src, "ddd");
        System.out.println(str);
    }

}
