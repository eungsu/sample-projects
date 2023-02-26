package com.example.excel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

/**
 * 엑셀문서를 읽어서 {@code List<Map<String, Object>>} 객체로 반환한다.
 *
 */
@Component
public class ExcelParser {

	public List<Map<String, Object>> getExcelData(byte[] bytes) throws IOException {
		List<Map<String, Object>> dataList = new ArrayList<>();
		
		Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(bytes));
		Sheet sheet = workbook.getSheetAt(0);
		
		List<String> headerNames = new ArrayList<>();
		Row headerRow = sheet.getRow(0);
		for (int cellIndex = 0; cellIndex < headerRow.getLastCellNum(); cellIndex++) {
			headerNames.add(headerRow.getCell(cellIndex).getStringCellValue());
		}		
		
		for (int rowIndex = 1; rowIndex < sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			
			Map<String, Object> data = new HashMap<>();
			for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
				Cell cell = row.getCell(cellIndex);
				CellType cellType = cell.getCellType();
				if (cellType == CellType.STRING) {
					data.put(headerNames.get(cellIndex), cell.getStringCellValue());
				} else if (cellType == CellType.NUMERIC) {
					if (DateUtil.isCellDateFormatted(cell)) {
						data.put(headerNames.get(cellIndex), cell.getDateCellValue());
					} else {
						data.put(headerNames.get(cellIndex), cell.getNumericCellValue());
					}
				} else if (cellType == CellType.BOOLEAN) {
					data.put(headerNames.get(cellIndex), cell.getBooleanCellValue());
				} else if (cellType == CellType.BLANK) {
					data.put(headerNames.get(cellIndex), null);
				}
			}
			
			dataList.add(data);
		}	
		workbook.close();
		System.out.println(dataList);
		return dataList;
	}
}
