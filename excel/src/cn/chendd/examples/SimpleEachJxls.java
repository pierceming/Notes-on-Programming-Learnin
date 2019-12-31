package cn.chendd.examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import cn.chendd.examples.vo.Employee;

/**
 * 简单列表模板导出
 * @author chendd
 */
public class SimpleEachJxls {

	public static void main(String[] args) throws Exception {
		//构造集合数据
		List<Employee> employees = new ArrayList<Employee>();
		Calendar time = Calendar.getInstance();
		time.set(2015, 5, 20);
		Date date = time.getTime();
		employees.add(new Employee("lishengle" , date , new BigDecimal(15000) , new BigDecimal(800)));
		employees.add(new Employee("jiajitao" , date , new BigDecimal(16000) , new BigDecimal(1000)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		//载入模板
		InputStream is = SimpleEachJxls.class.getClass().getResourceAsStream("/cn/chendd/examples/templates/simpleEach.xls");
		Context context = new Context();
		context.putVar("employees", employees);
		context.putVar("title", "员工信息列表");
		OutputStream os = new FileOutputStream(new File("d:\\test\\out_simpleEach.xls"));
		//添加A-Z的字符数据
		List<Character> dataList = new ArrayList<Character>();
		for(int i=65; i <= 90 ; i++){
			dataList.add((char)i);
		}
		context.putVar("dataList", dataList);
		//数组循环不支持？
		List<Object[]> arrays = new ArrayList<Object[]>();
		Object a1[] = new Object[]{"chendd1" , "男" , 25};
		Object a2[] = new Object[]{"chendd2" , "男" , 26};
		Object a3[] = new Object[]{"chendd3" , "男" , 27};
		arrays.add(a1);
		arrays.add(a2);
		arrays.add(a3);
		context.putVar("arrays", arrays);
		
		//集合循环
		List<List<Object>> list = new ArrayList<List<Object>>();
		list.add(Arrays.asList(a1));
		list.add(Arrays.asList(a2));
		list.add(Arrays.asList(a3));
		context.putVar("list", list);
		
		JxlsHelper.getInstance().processTemplate(is, os, context);
		os.flush();
		os.close();
		is.close();
	}

}
