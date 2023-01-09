package com.nik.hpm.util.file.excel.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nik.hpm.application.vo.LogAllExcelVO;
import com.nik.hpm.consignmentcompany.service.ConsignmentCompanyService;
import com.nik.hpm.customer.enitity.Customer;

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
			
			try (FileOutputStream fileOutputStream = new FileOutputStream(createTempFile)) {
				workbook.write(fileOutputStream);
			}
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
			if(StringUtils.isNotBlank(firstRow.getRequestCustomerName())) {
				
				sheetAt1.getRow(1).getCell(1).setCellValue(firstRow.getRequestCustomerName());
				
				String addr = firstRow.getRequestCustomerAddress();
				if(firstRow.getRequestCustomerAddressDetail() != null) {
					addr += firstRow.getRequestCustomerAddressDetail();
				}
				sheetAt1.getRow(2).getCell(1).setCellValue(addr);
				if(firstRow.getRequestCustomerTel() != null) sheetAt1.getRow(3).getCell(1).setCellValue(firstRow.getRequestCustomerTel());
				if(firstRow.getRequestCustomerFax() != null) sheetAt1.getRow(4).getCell(1).setCellValue(firstRow.getRequestCustomerFax());
				if(firstRow.getRequestCustomerPicName() != null) sheetAt1.getRow(5).getCell(1).setCellValue(firstRow.getRequestCustomerPicName());
				if(firstRow.getRequestCustomerMobile() != null) sheetAt1.getRow(6).getCell(1).setCellValue(firstRow.getRequestCustomerMobile());
				//if(firstRow.getPublishedDate() != null) sheetAt1.getRow(7).getCell(1).setCellValue(firstRow.getPublishedDate());
				sheetAt1.getRow(7).getCell(1).setCellValue(LocalDate.now());
				if(firstRow.getRequestCustomerEmail() != null) sheetAt1.getRow(8).getCell(1).setCellValue(firstRow.getRequestCustomerEmail());
				if(firstRow.getRequestCustomerRepName() != null) sheetAt1.getRow(9).getCell(1).setCellValue(firstRow.getRequestCustomerRepName());
				if(firstRow.getRequestCustomerCompanyRegNumber() != null) sheetAt1.getRow(10).getCell(1).setCellValue(firstRow.getRequestCustomerCompanyRegNumber());
				if(firstRow.getRequestCustomerBizCondition() != null) sheetAt1.getRow(11).getCell(1).setCellValue(firstRow.getRequestCustomerBizCondition());
				if(firstRow.getRequestCustomerItem() != null) sheetAt1.getRow(12).getCell(1).setCellValue(firstRow.getRequestCustomerItem());
				if(firstRow.getRequestCustomerPostNumber() != null) sheetAt1.getRow(13).getCell(1).setCellValue(firstRow.getRequestCustomerPostNumber());
				if(firstRow.getRequestCustomerBillPicName() != null) sheetAt1.getRow(14).getCell(1).setCellValue(firstRow.getRequestCustomerBillPicName());
				if(firstRow.getRequestCustomerBillPicTel() != null) sheetAt1.getRow(15).getCell(1).setCellValue(firstRow.getRequestCustomerBillPicTel());
				
			}else {
				sheetAt1.getRow(1).getCell(1).setCellValue(firstRow.getCustomerName());
				
				String addr = firstRow.getCustomerAddress();
				if(firstRow.getCustomerAddressDetail() != null) {
					addr += firstRow.getCustomerAddressDetail();
				}
				sheetAt1.getRow(2).getCell(1).setCellValue(addr);
				if(firstRow.getCustomerTel() != null) sheetAt1.getRow(3).getCell(1).setCellValue(firstRow.getCustomerTel());
				if(firstRow.getCustomerFax() != null) sheetAt1.getRow(4).getCell(1).setCellValue(firstRow.getCustomerFax());
				if(firstRow.getCustomerPicName() != null) sheetAt1.getRow(5).getCell(1).setCellValue(firstRow.getCustomerPicName());
				if(firstRow.getCustomerMobile() != null) sheetAt1.getRow(6).getCell(1).setCellValue(firstRow.getCustomerMobile());
				//if(firstRow.getPublishedDate() != null) sheetAt1.getRow(7).getCell(1).setCellValue(firstRow.getPublishedDate());
				sheetAt1.getRow(7).getCell(1).setCellValue(LocalDate.now());
				if(firstRow.getCustomerEmail() != null) sheetAt1.getRow(8).getCell(1).setCellValue(firstRow.getCustomerEmail());
				if(firstRow.getCustomerRepName() != null) sheetAt1.getRow(9).getCell(1).setCellValue(firstRow.getCustomerRepName());
				if(firstRow.getCustomerCompanyRegNumber() != null) sheetAt1.getRow(10).getCell(1).setCellValue(firstRow.getCustomerCompanyRegNumber());
				if(firstRow.getCustomerBizCondition() != null) sheetAt1.getRow(11).getCell(1).setCellValue(firstRow.getCustomerBizCondition());
				if(firstRow.getCustomerItem() != null) sheetAt1.getRow(12).getCell(1).setCellValue(firstRow.getCustomerItem());
				if(firstRow.getCustomerPostNumber() != null) sheetAt1.getRow(13).getCell(1).setCellValue(firstRow.getCustomerPostNumber());
				if(firstRow.getCustomerBillPicName() != null) sheetAt1.getRow(14).getCell(1).setCellValue(firstRow.getCustomerBillPicName());
				if(firstRow.getCustomerBillPicTel() != null) sheetAt1.getRow(15).getCell(1).setCellValue(firstRow.getCustomerBillPicTel());
			}
			
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
			
			try(FileOutputStream fileOutputStream = new FileOutputStream(createTempFile)){
				workbook.write(fileOutputStream);
			}
		}
		
		return createTempFile;
		
	}
	
	public File writeExcelDelivery(Customer customer) throws IOException {
		
		
		File createTempFile = File.createTempFile(uploadFilePath+"/document/hpm/temp", ".xlsm");
		
		File copyFile = new File(uploadFilePath+"/document/hpm/estimate/estimate.xlsm");
		FileInputStream file = new FileInputStream(copyFile);
		try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {
			XSSFSheet sheetAt1 = workbook.getSheetAt(1);
			workbook.setForceFormulaRecalculation(true);
			
			sheetAt1.getRow(1).getCell(1).setCellValue(customer.getName());
			
			String addr = customer.getAdress();
			if(customer.getAdressDetail() != null) {
				addr += customer.getAdressDetail();
			}
			sheetAt1.getRow(2).getCell(1).setCellValue(addr);
			sheetAt1.getRow(3).getCell(1).setCellValue(customer.getTel());
			sheetAt1.getRow(4).getCell(1).setCellValue(customer.getFax());
			sheetAt1.getRow(5).getCell(1).setCellValue(customer.getPicName());
			sheetAt1.getRow(6).getCell(1).setCellValue(customer.getPicTel());
			sheetAt1.getRow(7).getCell(1).setCellValue(LocalDate.now());
			sheetAt1.getRow(8).getCell(1).setCellValue(customer.getEmail());
			
			try(FileOutputStream fileOutputStream = new FileOutputStream(createTempFile)){
				workbook.write(fileOutputStream);
			}
			
		}
		
		return createTempFile;
		
	}
	
//	public File writeExcelConsignEstimate(List<LogAllExcelVO> applicationLogList, LogAllExcelVO firstRow, List<LogAllExcelVO> applicationLogGroupList) throws IOException {
//		
//		
//		File createTempFile = File.createTempFile(uploadFilePath+"/document/hpm/temp", ".xlsm");
//		
//		File copyFile = new File(uploadFilePath+"/document/hpm/estimate/consignEstimate.xlsm");
//		FileInputStream file = new FileInputStream(copyFile);
//		try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {
//			XSSFSheet sheetAt = workbook.getSheetAt(0);
//			XSSFSheet sheetAt1 = workbook.getSheetAt(1);
//			workbook.setForceFormulaRecalculation(true);
//			
//			int rowNum = 1;
//			for(LogAllExcelVO applog : applicationLogList) {
//				XSSFRow applogRow = sheetAt.getRow(rowNum);
//				if(applogRow == null) {
//					applogRow = sheetAt.createRow(rowNum);
//				}
//				
//				applogRow.createCell(0).setCellValue(applog.getDeviceName());
//				applogRow.createCell(1).setCellValue(applog.getQuantity());
//				if(!"N".equals(applog.getConsignmentCompanyNm()) && !"HPM".equals(applog.getConsignmentCompanyNm()) && !"자체".equals(applog.getConsignmentCompanyNm())) {
//					applogRow.createCell(3).setCellValue(applog.getConsignmentCompanyNm());
//				}
//				applogRow.createCell(4).setCellValue(applog.getCorrectionFee());
//				applogRow.createCell(5).setCellValue(applog.getRequestCustomerName());
//				applogRow.createCell(6).setCellValue(applog.getCustomerName());
//				
//				rowNum++;
//			}
//			if(StringUtils.isNotBlank(firstRow.getRequestCustomerName())) {
//				
//				sheetAt1.getRow(1).getCell(1).setCellValue(firstRow.getRequestCustomerName());
//				
//				String addr = firstRow.getRequestCustomerAddress();
//				if(firstRow.getRequestCustomerAddressDetail() != null) {
//					addr += firstRow.getRequestCustomerAddressDetail();
//				}
//				sheetAt1.getRow(2).getCell(1).setCellValue(addr);
//				if(firstRow.getRequestCustomerTel() != null) sheetAt1.getRow(3).getCell(1).setCellValue(firstRow.getRequestCustomerTel());
//				if(firstRow.getRequestCustomerFax() != null) sheetAt1.getRow(4).getCell(1).setCellValue(firstRow.getRequestCustomerFax());
//				if(firstRow.getRequestCustomerPicName() != null) sheetAt1.getRow(5).getCell(1).setCellValue(firstRow.getRequestCustomerPicName());
//				if(firstRow.getRequestCustomerMobile() != null) sheetAt1.getRow(6).getCell(1).setCellValue(firstRow.getRequestCustomerMobile());
//				//if(firstRow.getPublishedDate() != null) sheetAt1.getRow(7).getCell(1).setCellValue(firstRow.getPublishedDate());
//				sheetAt1.getRow(7).getCell(1).setCellValue(LocalDate.now());
//				if(firstRow.getRequestCustomerEmail() != null) sheetAt1.getRow(8).getCell(1).setCellValue(firstRow.getRequestCustomerEmail());
//				if(firstRow.getRequestCustomerRepName() != null) sheetAt1.getRow(9).getCell(1).setCellValue(firstRow.getRequestCustomerRepName());
//				if(firstRow.getRequestCustomerCompanyRegNumber() != null) sheetAt1.getRow(10).getCell(1).setCellValue(firstRow.getRequestCustomerCompanyRegNumber());
//				if(firstRow.getRequestCustomerBizCondition() != null) sheetAt1.getRow(11).getCell(1).setCellValue(firstRow.getRequestCustomerBizCondition());
//				if(firstRow.getRequestCustomerItem() != null) sheetAt1.getRow(12).getCell(1).setCellValue(firstRow.getRequestCustomerItem());
//				if(firstRow.getRequestCustomerPostNumber() != null) sheetAt1.getRow(13).getCell(1).setCellValue(firstRow.getRequestCustomerPostNumber());
//				if(firstRow.getRequestCustomerBillPicName() != null) sheetAt1.getRow(14).getCell(1).setCellValue(firstRow.getRequestCustomerBillPicName());
//				if(firstRow.getRequestCustomerBillPicTel() != null) sheetAt1.getRow(15).getCell(1).setCellValue(firstRow.getRequestCustomerBillPicTel());
//				
//			}else {
//				sheetAt1.getRow(1).getCell(1).setCellValue(firstRow.getCustomerName());
//				
//				String addr = firstRow.getCustomerAddress();
//				if(firstRow.getCustomerAddressDetail() != null) {
//					addr += firstRow.getCustomerAddressDetail();
//				}
//				sheetAt1.getRow(2).getCell(1).setCellValue(addr);
//				if(firstRow.getCustomerTel() != null) sheetAt1.getRow(3).getCell(1).setCellValue(firstRow.getCustomerTel());
//				if(firstRow.getCustomerFax() != null) sheetAt1.getRow(4).getCell(1).setCellValue(firstRow.getCustomerFax());
//				if(firstRow.getCustomerPicName() != null) sheetAt1.getRow(5).getCell(1).setCellValue(firstRow.getCustomerPicName());
//				if(firstRow.getCustomerMobile() != null) sheetAt1.getRow(6).getCell(1).setCellValue(firstRow.getCustomerMobile());
//				//if(firstRow.getPublishedDate() != null) sheetAt1.getRow(7).getCell(1).setCellValue(firstRow.getPublishedDate());
//				sheetAt1.getRow(7).getCell(1).setCellValue(LocalDate.now());
//				if(firstRow.getCustomerEmail() != null) sheetAt1.getRow(8).getCell(1).setCellValue(firstRow.getCustomerEmail());
//				if(firstRow.getCustomerRepName() != null) sheetAt1.getRow(9).getCell(1).setCellValue(firstRow.getCustomerRepName());
//				if(firstRow.getCustomerCompanyRegNumber() != null) sheetAt1.getRow(10).getCell(1).setCellValue(firstRow.getCustomerCompanyRegNumber());
//				if(firstRow.getCustomerBizCondition() != null) sheetAt1.getRow(11).getCell(1).setCellValue(firstRow.getCustomerBizCondition());
//				if(firstRow.getCustomerItem() != null) sheetAt1.getRow(12).getCell(1).setCellValue(firstRow.getCustomerItem());
//				if(firstRow.getCustomerPostNumber() != null) sheetAt1.getRow(13).getCell(1).setCellValue(firstRow.getCustomerPostNumber());
//				if(firstRow.getCustomerBillPicName() != null) sheetAt1.getRow(14).getCell(1).setCellValue(firstRow.getCustomerBillPicName());
//				if(firstRow.getCustomerBillPicTel() != null) sheetAt1.getRow(15).getCell(1).setCellValue(firstRow.getCustomerBillPicTel());
//			}
//			
//			rowNum = 21;
//			for(LogAllExcelVO applog : applicationLogGroupList) {
//				XSSFRow applogRow = sheetAt1.getRow(rowNum);
//				if(applogRow == null) {
//					applogRow = sheetAt.createRow(rowNum);
//				}
//				
//				applogRow.getCell(1).setCellValue(applog.getDeviceName());
//				
//				if(!"N".equals(applog.getConsignmentCompanyNm()) && !"HPM".equals(applog.getConsignmentCompanyNm()) && !"자체".equals(applog.getConsignmentCompanyNm())) {
//					applogRow.getCell(3).setCellValue(applog.getConsignmentCompanyNm());
//				}
//				applogRow.getCell(4).setCellValue(applog.getQuantity());
//				applogRow.getCell(5).setCellValue(applog.getCorrectionFee());
//				
//				rowNum++;
//			}
//			
//			FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
//			
//			workbook.write(fileOutputStream);
//			fileOutputStream.close();
//		}
//		
//		return createTempFile;
//		
//	}
	
	private String typeToString(HSSFCell cell) {
		
		if(cell == null) {
			return null;
		}
		
		if(cell.getCellType() == CellType.NUMERIC) {
			return String.valueOf((long)cell.getNumericCellValue());
		}
		
		return cell.getStringCellValue();
	}
}
