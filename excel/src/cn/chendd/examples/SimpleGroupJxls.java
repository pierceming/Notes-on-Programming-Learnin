package cn.chendd.examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import cn.chendd.examples.vo.User;

/**
 * 简单的数据分组显示
 */
public class SimpleGroupJxls {

	public static void main(String[] args) throws Exception {
		//构造集合数据
		List<User> dataList = new ArrayList<User>();
		dataList.add(new User("zhangchunhua" , "女" , 120D));
		dataList.add(new User("zhouyu" , "男" , 100D));
		dataList.add(new User("sunce" , "男" , 110D));
		dataList.add(new User("machao" , "男" , 130D));
		dataList.add(new User("caifuren" , "女" , 120D));
		dataList.add(new User("zuoci" , "保密" , 150D));
		//载入模板
		InputStream is = SimpleGroupJxls.class.getClass().getResourceAsStream("/cn/chendd/examples/templates/simpleGroup.xls");
		Context context = new Context();
		context.putVar("dataList", dataList);
		OutputStream os = new FileOutputStream(new File("d:\\test\\out_simpleGroup.xls"));
		//指定Sheet文件解析
		JxlsHelper.getInstance().processTemplate(is, os, context);
		os.flush();
		os.close();
		is.close();
	}

}
