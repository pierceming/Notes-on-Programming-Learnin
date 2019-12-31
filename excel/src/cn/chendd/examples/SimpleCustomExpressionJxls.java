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
 * 自定义表达式解析
 */
public class SimpleCustomExpressionJxls {

	public static void main(String[] args) throws Exception {
		
		//模板文件
		InputStream is = SimpleCustomExpressionJxls.class.getClass().getResourceAsStream("/cn/chendd/examples/templates/simpleCustomExpression.xls");
		Context context = new Context();
		//设置参数变量
		context.putVar("x", 10);
		context.putVar("y", 5);
		//设置绑定集合
		List<User> dataList = new ArrayList<User>();
		dataList.add(new User("zhangchunhua" , "女" , 120D));
		dataList.add(new User("zhouyu" , "男" , 100D));
		dataList.add(new User("sunce" , "男" , 110D));
		dataList.add(new User("machao" , "男" , 130D));
		dataList.add(new User("caifuren" , "女" , 120D));
		dataList.add(new User("zuoci" , "保密" , 150D));
		context.putVar("dataList", dataList);

		OutputStream os = new FileOutputStream(new File("d:\\test\\out_simpleCustomExpression.xls"));
		JxlsHelper.getInstance().buildExpressionNotation("[{", "}]").processTemplate(is, os, context);
		//释放资源
		os.flush();
		os.close();
		is.close();
		
	}
	
}
