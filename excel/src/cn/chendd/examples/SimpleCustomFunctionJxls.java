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
import org.jxls.transform.poi.WritableHyperlink;
import org.jxls.util.TransformerFactory;

import cn.chendd.examples.custom.functions.ColorCellValue;
import cn.chendd.examples.custom.functions.DropdownCellValue;
import cn.chendd.examples.vo.User;

/**
 * 自定义function
 */
public class SimpleCustomFunctionJxls {

	public static void main(String[] args) throws Exception {
		
		//模板文件
		InputStream is = SimpleCustomFunctionJxls.class.getClass().getResourceAsStream("/cn/chendd/examples/templates/simpleCustomFunction.xls");
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
		
		Map<String , Object> myFunction = new HashMap<String , Object>();
		myFunction.put("my", new SimpleCustomFunctionJxls());
		
		OutputStream os = new FileOutputStream(new File("d:\\test\\out_simpleCustomFunction.xls"));
		Transformer trans = TransformerFactory.createTransformer(is, os);
		JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator) trans.getTransformationConfig().getExpressionEvaluator();
		evaluator.getJexlEngine().setFunctions(myFunction);
		//载入模板、处理导出
		AreaBuilder areaBuilder = new XlsCommentAreaBuilder(trans);
		List<Area> areaList = areaBuilder.build();
		areaList.get(0).applyAt(new CellRef("Hello!A1"), context);
		trans.write();
		//释放资源
		os.flush();
		os.close();
		is.close();
		
	}
	
	//返回大的数
	public Integer max(Integer x , Integer y){
		return x > y ? x : y;
	}
	
	//给金额前面显示个货币符号
	public Object formatMoney(Object a){
		Object result = null;
		if(a != null){
			return "￥" + a;
		}
		return result;
	}
	
	//超链接
	public WritableCellValue myHyperlink(String address, String title) {
		return new WritableHyperlink(address, title);
	}
	
	//按值显示背景色
	public WritableCellValue showColor(Integer value) {
		return new ColorCellValue(value);
	}
	
	//生成下拉菜单
	public WritableCellValue downlist(String splitItem , String value){
		return new DropdownCellValue(splitItem, value);
	}
	
}
