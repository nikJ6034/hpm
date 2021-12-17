package com.nik.hpm.util.file.excel.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nik.hpm.application.vo.LogAllExcelVO;
import com.nik.hpm.consignmentcompany.service.ConsignmentCompanyService;

@Component
public class DataAppliLogExcel {

	@Value("${uploads.filePath}")
    private String uploadFilePath;
	@Autowired
	ConsignmentCompanyService consignmentCompanyService;
	
	public File writeExcel(List<LogAllExcelVO> applicationLogList) throws IOException {
		
		
		File createTempFile = File.createTempFile(uploadFilePath+"/document/dataAppliLog/temp", ".xls");
		
		File copyFile = new File(uploadFilePath+"/document/dataAppliLog/dataAppliLog.xls");
		FileInputStream file = new FileInputStream(copyFile);
		try (HSSFWorkbook workbook = new HSSFWorkbook(file)) {
			HSSFSheet sheetAt = workbook.getSheet("DATA");
			workbook.setForceFormulaRecalculation(true);
			
			LogAllExcelVO logAllExcelVO = applicationLogList.get(0);
			HSSFRow applogRow1 = sheetAt.getRow(1);
			applogRow1.getCell(2).setCellValue(logAllExcelVO.getCustomerName());
			
			if(logAllExcelVO.getPublishedDate() != null) {
				HSSFRow applogRow2 = sheetAt.getRow(2);
				applogRow2.getCell(2).setCellValue(logAllExcelVO.getPublishedDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
			}
			
			sheetAt.getRow(3).getCell(2).setCellValue(logAllExcelVO.getCustomerTel());
			sheetAt.getRow(4).getCell(2).setCellValue(logAllExcelVO.getCustomerFax());
			sheetAt.getRow(5).getCell(2).setCellValue(logAllExcelVO.getCustomerPicName());
			sheetAt.getRow(6).getCell(2).setCellValue(logAllExcelVO.getCustomerAddress()+" "+logAllExcelVO.getCustomerAddressDetail());
			
			int rowNum = 9;
			for(LogAllExcelVO applog : applicationLogList) {
				HSSFRow applogRow = sheetAt.getRow(rowNum);
				applogRow.getCell(1).setCellValue(applog.getDeviceName());
				applogRow.getCell(6).setCellValue(applog.getQuantity());	
				applogRow.getCell(8).setCellValue(applog.getCorrectionFee());
				rowNum++;
			}
			
			FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
			
			workbook.write(fileOutputStream);
			fileOutputStream.close();
		}
		
		return createTempFile;
		
	}
	
	public File writeExcelEstimate(List<LogAllExcelVO> applicationLogList, LogAllExcelVO firstRow, List<LogAllExcelVO> applicationLogGroupList) throws IOException {
		
		
		File createTempFile = File.createTempFile(uploadFilePath+"/document/hpm/temp", ".xlsm");
		
		File copyFile = new File(uploadFilePath+"/document/hpm/estimate/estimate.xlsm");
		FileInputStream file = new FileInputStream(copyFile);
		try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {
			XSSFSheet sheetAt = workbook.getSheetAt(0);
			XSSFSheet sheetAt1 = workbook.getSheetAt(1);
			workbook.setForceFormulaRecalculation(true);
			
			int rowNum = 1;
			for(LogAllExcelVO applog : applicationLogList) {
				XSSFRow applogRow = sheetAt.getRow(rowNum);
				if(applogRow == null) {
					applogRow = sheetAt.createRow(rowNum);
				}
				
				applogRow.createCell(0).setCellValue(applog.getDeviceName());
				applogRow.createCell(1).setCellValue(applog.getQuantity());
				if(!"N".equals(applog.getConsignmentCompanyNm()) && !"HPM".equals(applog.getConsignmentCompanyNm()) && !"자체".equals(applog.getConsignmentCompanyNm())) {
					applogRow.createCell(3).setCellValue(applog.getConsignmentCompanyNm());
				}
				applogRow.createCell(4).setCellValue(applog.getCorrectionFee());
				applogRow.createCell(5).setCellValue(applog.getRequestCustomerName());
				applogRow.createCell(6).setCellValue(applog.getCustomerName());
				
				rowNum++;
			}
			
			sheetAt1.getRow(1).getCell(1).setCellValue(firstRow.getCustomerName());
			
			String addr = firstRow.getCustomerAddress();
			if(firstRow.getCustomerAddressDetail() != null) {
				addr += firstRow.getCustomerAddressDetail();
			}
			sheetAt1.getRow(2).getCell(1).setCellValue(addr);
			sheetAt1.getRow(3).getCell(1).setCellValue(firstRow.getCustomerTel());
			sheetAt1.getRow(4).getCell(1).setCellValue(firstRow.getCustomerFax());
			sheetAt1.getRow(5).getCell(1).setCellValue(firstRow.getCustomerPicName());
			sheetAt1.getRow(6).getCell(1).setCellValue(firstRow.getCustomerMobile());
			sheetAt1.getRow(7).getCell(1).setCellValue(firstRow.getPublishedDate());
			sheetAt1.getRow(8).getCell(1).setCellValue(firstRow.getCustomerEmail());
			sheetAt1.getRow(9).getCell(1).setCellValue(firstRow.getCustomerRepName());
			sheetAt1.getRow(10).getCell(1).setCellValue(firstRow.getCustomerCompanyRegNumber());
			sheetAt1.getRow(11).getCell(1).setCellValue(firstRow.getCustomerBizCondition());
			sheetAt1.getRow(12).getCell(1).setCellValue(firstRow.getCustomerItem());
			sheetAt1.getRow(13).getCell(1).setCellValue(firstRow.getCustomerPostNumber());
			sheetAt1.getRow(14).getCell(1).setCellValue(firstRow.getCustomerBillPicName());
			sheetAt1.getRow(15).getCell(1).setCellValue(firstRow.getCustomerBillPicTel());
			
			rowNum = 21;
			for(LogAllExcelVO applog : applicationLogGroupList) {
				XSSFRow applogRow = sheetAt1.getRow(rowNum);
				if(applogRow == null) {
					applogRow = sheetAt.createRow(rowNum);
				}
				
				applogRow.getCell(1).setCellValue(applog.getDeviceName());
				
				if(!"N".equals(applog.getConsignmentCompanyNm()) && !"HPM".equals(applog.getConsignmentCompanyNm()) && !"자체".equals(applog.getConsignmentCompanyNm())) {
					applogRow.getCell(3).setCellValue(applog.getConsignmentCompanyNm());
				}
				applogRow.getCell(4).setCellValue(applog.getQuantity());
				applogRow.getCell(5).setCellValue(applog.getCorrectionFee());
				
				rowNum++;
			}
			
			FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
			
			workbook.write(fileOutputStream);
			fileOutputStream.close();
		}
		
		return createTempFile;
		
	}
}
