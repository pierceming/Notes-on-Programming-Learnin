package cn.chendd.examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import cn.chendd.examples.vo.Employee;

/**
 * 简单列表导出--将绩效大于2000的显示绿色，大于1000小于2000的显示黄色，小于1000的显示红色
 */
public class SimpleIfJxls {

	public static void main(String[] args) throws Exception {
		//构造集合数据
		List<Employee> employees = new ArrayList<Employee>();
		Calendar time = Calendar.getInstance();
		time.set(2015, 5, 20);
		Date date = time.getTime();
		employees.add(new Employee("lishengle" , date , new BigDecimal(5000) , new BigDecimal(800)));
		employees.add(new Employee("jiajitao" , date , new BigDecimal(6000) , new BigDecimal(1200)));
		employees.add(new Employee("jiangjunjian" , date , new BigDecimal(8500) , new BigDecimal(2500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("chendd" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("zenxianrong" , date , new BigDecimal(7500) , new BigDecimal(3500)));
		//载入模板
		InputStream is = SimpleIfJxls.class.getClass().getResourceAsStream("/cn/chendd/examples/templates/simpleIf.xls");
		Context context = new Context();
		context.putVar("employees", employees);
		context.putVar("title", "员工信息列表");
		OutputStream os = new FileOutputStream(new File("d:\\test\\out_simpleIf.xls"));
		//指定Sheet文件解析
		JxlsHelper.getInstance().processTemplate(is, os, context);
		os.flush();
		os.close();
		is.close();
	}

}
