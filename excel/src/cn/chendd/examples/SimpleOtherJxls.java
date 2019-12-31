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

public class SimpleOtherJxls {

	public static void main(String[] args) throws Exception {
		//构造集合数据
		List<Employee> employees = new ArrayList<Employee>();
		Calendar time = Calendar.getInstance();
		time.set(2015, 5, 20);
		Date date = time.getTime();
		employees.add(new Employee("lishengle" , date , new BigDecimal(15000) , new BigDecimal(800)));
		employees.add(new Employee("jiajitao" , date , new BigDecimal(16000) , new BigDecimal(1000)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		//载入模板
		InputStream is = SimpleOtherJxls.class.getClass().getResourceAsStream("/cn/chendd/examples/templates/simpleOther.xls");
		Context context = new Context();
		context.putVar("employees", employees);
		OutputStream os = new FileOutputStream(new File("d:\\test\\out_simpleOther.xls"));
		//指定Sheet文件解析
		JxlsHelper.getInstance().processTemplate(is, os, context);
		os.flush();
		os.close();
		is.close();
	}
	
}
