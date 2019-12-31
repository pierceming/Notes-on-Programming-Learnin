package cn.chendd.examples;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

/**
 * 简单引用图片的示例
 * 感觉设置的格式没啥用
 * @author chendd
 */
public class SimpleImageJxls {

	public static void main(String[] args) throws Exception {
		//构造图片数据
		byte imageBytes[] = getImageBytes("/cn/chendd/examples/images/splash.jpg");
		//载入模板
		InputStream is = SimpleImageJxls.class.getClass().getResourceAsStream("/cn/chendd/examples/templates/simpleImage.xls");
		Context context = new Context();
		context.putVar("image", imageBytes);//单一图片
		byte prop3Bytes[] = getImageBytes("/cn/chendd/examples/images/wn_nav.png");
		byte prop4Bytes[] = getImageBytes("/cn/chendd/examples/images/wr_nav.png");
		//构造列表数据，包括两个图片
		List<Map<String , Object>> dataList = new ArrayList<Map<String , Object>>();
		Map<String , Object> m1 = new HashMap<String , Object>();
		m1.put("prop1" , "p1-1");m1.put("prop2" , "p1-2");
		m1.put("prop3" , prop3Bytes);m1.put("prop4" , prop4Bytes);
		dataList.add(m1);
		Map<String , Object> m2 = new HashMap<String , Object>();
		m2.put("prop1" , "p2-1");m2.put("prop2" , "p2-2");
		m2.put("prop3" , prop3Bytes);m2.put("prop4" , prop4Bytes);
		dataList.add(m2);
		Map<String , Object> m3 = new HashMap<String , Object>();
		m3.put("prop1" , "p3-1");m3.put("prop2" , "p3-2");
		m3.put("prop3" , prop3Bytes);m3.put("prop4" , prop4Bytes);
		dataList.add(m3);
		context.putVar("dataList", dataList);
		OutputStream os = new FileOutputStream(new File("d:\\test\\out_simpleImage.xls"));
		//指定Sheet文件解析
		JxlsHelper.getInstance().processTemplate(is, os, context);
		os.flush();
		os.close();
		is.close();
	}

	//获取图片的字节码，临时使用
	private static byte[] getImageBytes(String imagePath) throws IOException {
		byte imageBytes[] = null;
		InputStream is = SimpleImageJxls.class.getClass().getResourceAsStream(imagePath);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte bytes[] = new byte[1024];
		int lens = -1;
		try {
			while ((lens = is.read(bytes)) != -1) {
				baos.write(bytes, 0, lens);
			}
			imageBytes = baos.toByteArray();
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			baos.flush();
			baos.close();
			is.close();
		}
		return imageBytes;
	}

}
