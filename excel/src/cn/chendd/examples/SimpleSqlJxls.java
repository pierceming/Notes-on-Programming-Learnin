package cn.chendd.examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;

import org.jxls.common.Context;
import org.jxls.jdbc.JdbcHelper;
import org.jxls.util.JxlsHelper;

import cn.chendd.examples.jdbc.DBManage;

/**
 * sql查询报表
 */
public class SimpleSqlJxls {

	public static void main(String[] args) throws Exception {
		Connection conn = DBManage.getConnection();
		JdbcHelper jdbcHelper = new JdbcHelper(conn);
		//模板文件
		InputStream is = SimpleSqlJxls.class.getClass().getResourceAsStream("/cn/chendd/examples/templates/simpleSql.xls");
		Context context = new Context();
		//设置参数变量
		//context.putVar("conn", conn);
		context.putVar("jdbc", jdbcHelper);
		OutputStream os = new FileOutputStream(new File("d:\\test\\out_simpleSql.xls"));
		//载入模板、处理导出
		JxlsHelper.getInstance().processTemplate(is , os , context);
		//释放资源
		os.flush();
		os.close();
		is.close();
		DBManage.Close(conn);
	}
	
}
