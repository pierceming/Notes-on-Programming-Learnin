package cn.chendd.examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

/**
 * Hello 入门体验
 *
 */
public class SimpleHelloJxls {

	public static void main(String[] args) throws Exception {
		//模板文件
		InputStream is = SimpleHelloJxls.class.getClass().getResourceAsStream("/cn/chendd/examples/templates/simpleHello.xls");
		Context context = new Context();
		//设置参数变量
		context.putVar("name", "chendd");
		OutputStream os = new FileOutputStream(new File("d:\\test\\out_simpleHello.xls"));
		//载入模板、处理导出
		JxlsHelper.getInstance().processTemplate(is , os , context);
		//释放资源
		os.flush();
		os.close();
		is.close();
		//备注，如果从一个简单的示例入手，这个就再简单不过 了
		
	}
	
}
