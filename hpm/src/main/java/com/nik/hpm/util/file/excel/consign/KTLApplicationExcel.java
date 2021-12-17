package com.nik.hpm.util.file.excel.consign;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.nik.hpm.application.vo.ApplicationLogVO;
import com.nik.hpm.consignmentcompany.entity.ConsignmentCompanyApplication;

public class KTLApplicationExcel {
	
	public File writeExcel(ConsignmentCompanyApplication consignmentCompanyApplication, String uploadFilePath) throws IOException {
		
		File createTempFile = File.createTempFile(uploadFilePath+"/document/consignment/temp/KTL", ".xls");
		
		File copyFile = new File(uploadFilePath+"/document/consignment/KTL.xls");
		FileInputStream file = new FileInputStream(copyFile);
		try (HSSFWorkbook workbook = new HSSFWorkbook(file)) {
			HSSFSheet sheetAt = workbook.getSheet("KTL");
			
			List<ApplicationLogVO> applicationLogList = consignmentCompanyApplication.getApplicationLogList();
			
			int rowNum = 9;
			int pageCount = 0;
			for(ApplicationLogVO applog : applicationLogList) {
				pageCount++;
				HSSFRow applogRow = sheetAt.getRow(rowNum);
				applogRow.getCell(3).setCellValue(applog.getDeviceName());
				applogRow.getCell(4).setCellValue(applog.getDeviceNumber());
				applogRow.getCell(5).setCellValue(applog.getCustomerName());
				applogRow.getCell(6).setCellValue(applog.getCustomerAdress()+" "+applog.getCustomerAdressDetail());
				
				rowNum++;
				HSSFRow applogRow2 = sheetAt.getRow(rowNum);
				applogRow2.getCell(3).setCellValue(applog.getEtc());
				
				rowNum++;
				if(pageCount == 12) {
					pageCount = 0;
					rowNum += 9;
				}
			}
			
			FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
			
			workbook.write(fileOutputStream);
			fileOutputStream.close();
		}
		
		return createTempFile;
		
	}
}
