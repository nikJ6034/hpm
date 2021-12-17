package com.nik.hpm.util.file.excel.consign;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nik.hpm.application.vo.ApplicationLogVO;
import com.nik.hpm.consignmentcompany.entity.ConsignmentCompanyApplication;
import com.nik.hpm.enumcode.Yn;

@Component
public class ConsignApplicationExcel {

	@Value("${uploads.filePath}")
    private String uploadFilePath;
	
	public File writeExcel(ConsignmentCompanyApplication consignmentCompanyApplication) throws IOException {
		
		File createTempFile = File.createTempFile(uploadFilePath+"/document/consignment/temp/consignment", ".xls");
		
		File copyFile = new File(uploadFilePath+"/document/consignment/consignment.xls");
		FileInputStream file = new FileInputStream(copyFile);
		try (HSSFWorkbook workbook = new HSSFWorkbook(file)) {
			HSSFSheet sheetAt = workbook.getSheet("입력");
			workbook.setForceFormulaRecalculation(true);
			
			List<ApplicationLogVO> applicationLogList = consignmentCompanyApplication.getApplicationLogList();
			
			int rowNum = 13;
			for(ApplicationLogVO applog : applicationLogList) {
				HSSFRow applogRow = sheetAt.getRow(rowNum);
				if(applog.getConsignmentCompany() != null) {
					if(applogRow.getCell(1) == null) applogRow.createCell(1);
					applogRow.getCell(1).setCellValue(applog.getConsignmentCompany().getName());
				}
				if(applogRow.getCell(2) == null) applogRow.createCell(2);
				applogRow.getCell(2).setCellValue(applog.getDeviceName());
				if(applogRow.getCell(3) == null) applogRow.createCell(3);
				applogRow.getCell(3).setCellValue(applog.getProductionCompany());
				if(applogRow.getCell(4) == null) applogRow.createCell(4);
				applogRow.getCell(4).setCellValue(applog.getDeviceNumber());
				
				if(applog.getStandard() == null && applog.getModel() != null) {
					if(applogRow.getCell(5) == null) applogRow.createCell(5);
					applogRow.getCell(5).setCellValue(applog.getModel());
				}else if(applog.getStandard() != null && applog.getModel() == null) {
					if(applogRow.getCell(5) == null) applogRow.createCell(5);
					applogRow.getCell(5).setCellValue(applog.getStandard());
				}else if(applog.getStandard() != null && applog.getModel() != null) {
					if(applogRow.getCell(5) == null) applogRow.createCell(5);
					applogRow.getCell(5).setCellValue(applog.getStandard() + " / " +applog.getStandard());
				}
				
				if(applogRow.getCell(6) == null) applogRow.createCell(6);
				if(applog.getCustomerSameYn() == Yn.N) {
					applogRow.getCell(6).setCellValue(applog.getRequestCustomerName());
				}else {
					applogRow.getCell(6).setCellValue(applog.getCustomerName());
				}
				
				if(applogRow.getCell(7) == null) applogRow.createCell(7);
				if(applog.getCustomerSameYn() == Yn.N) {
					applogRow.getCell(7).setCellValue(applog.getRequestCustomerAddress());
				}else {
					if(applog.getCustomerAdress() != null && applog.getCustomerAdressDetail() != null) {
						applogRow.getCell(7).setCellValue(applog.getCustomerAdress()+" "+applog.getCustomerAdressDetail());
					}else {
						applogRow.getCell(7).setCellValue(applog.getCustomerAdress());
					}
				}
				
				
				
				if(applogRow.getCell(8) == null) applogRow.createCell(8);
				applogRow.getCell(8).setCellValue(applog.getEtc());
				
				rowNum++;
			}
			
			FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
			
			workbook.write(fileOutputStream);
			fileOutputStream.close();
		}
		
		return createTempFile;
	}
}
