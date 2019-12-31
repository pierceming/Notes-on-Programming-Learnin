package cn.chendd.examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
import org.jxls.transform.poi.WritableCellValue;
import org.jxls.util.TransformerFactory;

import cn.chendd.examples.custom.functions.MergeCellValue;
import cn.chendd.examples.vo.User;

/**
 * 简单横向单元格合并
 */
public class SimpleMergeJxls {

	public static void main(String[] args) throws Exception {
		
		//模板文件
		InputStream is = SimpleCustomFunctionJxls.class.getClass().getResourceAsStream("/cn/chendd/examples/templates/simpleMergeCell.xls");
		Context context = new Context();
		//设置绑定集合
		List<User> dataList = new ArrayList<User>();
		dataList.add(new User("yuji" , "保密" , 120D , 0));
		dataList.add(new User("zhangchunhua" , "女" , 120D , 0));
		dataList.add(new User("caifuren" , "女" , 120D , 1));
		dataList.add(new User("zhouyu" , "男" , 100D , 0));
		dataList.add(new User("sunce" , "男" , 110D , 0));
		dataList.add(new User("machao" , "男" , 130D , 2));
		dataList.add(new User("zuoci" , "保密" , 150D , 0));
		context.putVar("dataList", dataList);
		
		Map<String , Object> myFunction = new HashMap<String , Object>();
		myFunction.put("mg", new SimpleMergeJxls());
		
		OutputStream os = new FileOutputStream(new File("d:\\test\\out_simpleMergeCell.xls"));
		Transformer trans = TransformerFactory.createTransformer(is, os);
		JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator) trans.getTransformationConfig().getExpressionEvaluator();
		evaluator.getJexlEngine().setFunctions(myFunction);
		//载入模板、处理导出
		AreaBuilder areaBuilder = new XlsCommentAreaBuilder(trans);
		List<Area> areaList = areaBuilder.build();
		areaList.get(0).applyAt(new CellRef("简单单元格合并!A1"), context);
		trans.write();
//		JxlsHelper.getInstance().processTemplate(is, os, context);
		//释放资源
		os.flush();
		os.close();
		is.close();
		
	}
	
	//单元格合并
	public WritableCellValue mergeCell(String value , Integer mergerRows) {
		return new MergeCellValue(value , mergerRows);
	}

}
