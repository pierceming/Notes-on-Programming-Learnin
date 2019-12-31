package cn.chendd.examples.custom.functions;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.jxls.common.Context;
import org.jxls.transform.poi.WritableCellValue;

/**
 * 自定义背景颜色单元格，根据单元格的值来显示
 * @author chendd
 */
public class ColorCellValue implements WritableCellValue {

	private Integer value;
	
	public ColorCellValue(Integer value){
		this.value = value;
	}
	
	@Override
	public Object writeToCell(Cell cell, Context context) {
		Workbook workbook = cell.getSheet().getWorkbook();
		CellStyle bgCell = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBold(true);
		short colorIndex = 0;
		if(value > 1000){
			colorIndex = HSSFColorPredefined.RED.getIndex();
			font.setColor(HSSFColorPredefined.WHITE.getIndex());
		} else if(value > 5000){
			colorIndex = HSSFColorPredefined.GREEN.getIndex();
			font.setColor(HSSFColorPredefined.WHITE.getIndex());
		} else if(value > 1000){
			colorIndex = HSSFColorPredefined.BLACK.getIndex();
			font.setColor(HSSFColorPredefined.WHITE.getIndex());
		} else {
			colorIndex = HSSFColorPredefined.BLUE.getIndex();
			font.setColor(HSSFColorPredefined.WHITE.getIndex());
		}
		bgCell.setFillForegroundColor(colorIndex);
		bgCell.setFont(font);
		bgCell.setAlignment(HorizontalAlignment.CENTER);
		bgCell.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		bgCell.setVerticalAlignment(VerticalAlignment.CENTER);
		cell.setCellStyle(bgCell);
		cell.setCellValue(value);
		return cell;
	}
	
	

}
