package cn.chendd.examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

/**
 * 简单表格--动态列的数据以及列的字段类型
 * 感觉设置的格式没啥用
 * @author chendd
 */
public class SimpleGridJxls {

	public static void main(String[] args) throws Exception {
		//构造集合数据
		List<List<Object>> dataList = new ArrayList<List<Object>>();
		List<Object> data1 = new ArrayList<Object>();
		data1.add("chendd");data1.add("男");data1.add(25);
		dataList.add(data1);
		List<Object> data2 = new ArrayList<Object>();
		data2.add("jiangjj");data2.add("男");data2.add(26);
		dataList.add(data2);
		List<Object> data3 = new ArrayList<Object>();
		data3.add("zengxr");data3.add("男");data3.add(27);
		dataList.add(data3);
		//载入模板
		InputStream is = SimpleGridJxls .class.getClass().getResourceAsStream("/cn/chendd/examples/templates/simpleGrid.xls");
		Context context = new Context();
		context.putVar("headers", Arrays.asList("姓名" , "性别" , "年龄"));
		context.putVar("dataList", dataList);
		OutputStream os = new FileOutputStream(new File("d:\\test\\out_simpleGrid.xls"));
		//指定Sheet文件解析
		JxlsHelper.getInstance().processTemplate(is, os, context);
		os.flush();
		os.close();
		is.close();
	}

}
