package cn.chendd.examples.custom.functions;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.jxls.common.Context;
import org.jxls.transform.poi.WritableCellValue;

public class DropdownCellValue implements WritableCellValue {

	private String splitItem , value;
	
	public DropdownCellValue(String splitItem , String value){
		this.splitItem = splitItem;
		this.value = value;
	}
	
	@Override
	public Object writeToCell(Cell cell, Context context) {
		String downs[] = splitItem.split(",");
		DVConstraint dv = DVConstraint.createExplicitListConstraint(downs);
		Sheet sheet = cell.getSheet();
		//开始行、结束行、开始列、结束列
		Row row = cell.getRow();
		int startRowIndex = row.getRowNum();
		int endRowIndex = startRowIndex;
		int cellStartIndex = cell.getColumnIndex();
		int cellEndIndex = cellStartIndex;
		CellRangeAddressList regions = new CellRangeAddressList(startRowIndex, endRowIndex, cellStartIndex, cellEndIndex);
		DataValidation v = new HSSFDataValidation(regions, dv);
		sheet.addValidationData(v);
		cell.setCellValue(value);
		return cell;
	}
	
	

}
