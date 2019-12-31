package cn.chendd.examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.jxls.common.Context;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.JxlsHelper;

/**
 * Link 设置单元格
 * util.hyperlink的调用类为：org.jxls.transform.poi.PoiUtil
 */
public class SimpleLinkJxls {

	public static void main(String[] args) throws Exception {
		//模板文件
		InputStream is = SimpleLinkJxls.class.getClass().getResourceAsStream("/cn/chendd/examples/templates/simpleLink.xls");
		Context context = PoiTransformer.createInitialContext();
		//设置参数变量
		context.putVar("name", "chendd");
		context.putVar("link", "http://www.chendd.cn");
		OutputStream os = new FileOutputStream(new File("d:\\test\\out_simpleLink.xls"));
		//载入模板、处理导出
		JxlsHelper.getInstance().setUseFastFormulaProcessor(false).processTemplate(is , os , context);
		//释放资源
		os.flush();
		os.close();
		is.close();
		
	}
	
}
