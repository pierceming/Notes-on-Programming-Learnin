package cn.chendd.examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import cn.chendd.examples.vo.Employee;

/**
 * 简单列表导出--带统计汇总
 * 循环List&lt;Bean&gt;与List&lt;Map&gt;结构的集合
 * @author chendd
 */
public class SimpleEachSumJxls {

	public static void main(String[] args) throws Exception {
		//构造集合数据
		List<Employee> employees = new ArrayList<Employee>();
		Calendar time = Calendar.getInstance();
		time.set(2015, 5, 20);
		Date date = time.getTime();
		//------设置数据源一
		employees.add(new Employee("lishengle" , date , new BigDecimal(15000) , new BigDecimal(800)));
		employees.add(new Employee("jiajitao" , date , new BigDecimal(16000) , new BigDecimal(1000)));
		employees.add(new Employee("sunming" , date , new BigDecimal(17000) , new BigDecimal(1500)));
		//------设置数据源二
		List<Map<String , Object>> dataList = new ArrayList<Map<String , Object>>();
		Map<String , Object> map1 = new HashMap<String , Object>();
		map1.put("prop1", "曹孟德");map1.put("prop2", "男");map1.put("prop3", 1800);map1.put("prop4", 180);
		Map<String , Object> map2 = new HashMap<String , Object>();
		map2.put("prop1", "吴国太");map2.put("prop2", "女");map2.put("prop3", 2800);map2.put("prop4", 280);
		Map<String , Object> map3 = new HashMap<String , Object>();
		map3.put("prop1", "大嘴");map3.put("prop2", "男");map3.put("prop3", 3800);map3.put("prop4", 380);
		dataList.add(map1);
		dataList.add(map2);
		dataList.add(map3);
		//载入模板
		InputStream is = SimpleEachSumJxls.class.getClass().getResourceAsStream("/cn/chendd/examples/templates/simpleEachSum.xls");
		Context context = new Context();
		context.putVar("employees", employees);
		context.putVar("title", "员工信息列表");
		context.putVar("dataList", dataList);
		OutputStream os = new FileOutputStream(new File("d:\\test\\out_simpleEachSum.xls"));
		//指定Sheet文件解析
		JxlsHelper.getInstance().processTemplate(is, os, context);
		os.flush();
		os.close();
		is.close();
	}

}
