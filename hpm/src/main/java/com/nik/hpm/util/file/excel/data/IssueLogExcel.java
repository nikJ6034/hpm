package com.nik.hpm.util.file.excel.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellBase;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nik.hpm.customer.enitity.Customer;
import com.nik.hpm.customer.repository.CustomerRepository;
import com.nik.hpm.enumcode.ReportLanguage;
import com.nik.hpm.issue.entity.IssueLog;
import com.nik.hpm.issue.repository.IssueLogRepository;

@Component
@Transactional(rollbackFor = Exception.class)
public class IssueLogExcel {
	
	@Autowired
	IssueLogRepository issueLogRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Value("${uploads.filePath}")
    private String uploadFilePath;
	
	public List<IssueLog> readExcel(File file) throws Exception {
		
		String extension = FileNameUtils.getExtension(file.getName());
		if("xls".equals(extension)) {
			return readXls(file);
		}else if("xlsx".equals(extension)) {
			return readXlsx(file);
		}
		
		return Collections.emptyList();
	}
	
	public File writeExcel(List<IssueLog> issueLogList) throws IOException {
		File createTempFile = File.createTempFile(uploadFilePath+"/document/hpm/temp", ".xls");
		File copyFile = new File(uploadFilePath+"/document/hpm/임시관리대장.xls");
		
		try (
				FileInputStream file = new FileInputStream(copyFile);
				HSSFWorkbook workbook = new HSSFWorkbook(file);
				FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
			) {
			
			HSSFSheet sheetAt = workbook.getSheetAt(0);
			
			int rowCount = 2;
			for(IssueLog issueLog : issueLogList) {
				int cellCount = 0;
				
				HSSFRow row = sheetAt.getRow(rowCount);
				if(row == null) row = sheetAt.createRow(rowCount);
				
				
				HSSFCell cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				cell.setCellValue(issueLog.getRegNumber());
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				cell.setCellValue(issueLog.getReportNumber());
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				if(issueLog.getRequestCustomer() != null) {
					cell.setCellValue(issueLog.getRequestCustomer().getName());
				}
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				cell.setCellValue(issueLog.getCustomer().getName());
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				cell.setCellValue(issueLog.getDeviceName());
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				cell.setCellValue(issueLog.getProductionCompany());
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				cell.setCellValue(issueLog.getModel());
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				cell.setCellValue(issueLog.getDeviceNumber());
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				cell.setCellValue(issueLog.getStandard());
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				cell.setCellValue(issueLog.getResolution());
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				cell.setCellValue(issueLog.getUnit());
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				cell.setCellValue(issueLog.getCorrectionDate());
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				cell.setCellValue(issueLog.getPlace());
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				cell.setCellValue(issueLog.getPractitioner());
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				cell.setCellValue(issueLog.getMiddleCategory());
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				cell.setCellValue(issueLog.getSmallCategory());
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				cell.setCellValue(issueLog.getPublishedDate());
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				cell.setCellValue(issueLog.getTechnicalManager());
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				
				if(issueLog.getReportLanguage() != null) {
					cell.setCellValue(issueLog.getReportLanguage().getValue());
				}
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				cell.setCellValue(issueLog.getCorrectionFee());
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				cell.setCellValue(issueLog.getHpmType());
				
				cellCount++;
				cell = row.getCell(cellCount);
				if(cell == null) cell = row.createCell(cellCount);
				cell.setCellValue(issueLog.getAppliRegDate());
				
				rowCount++;
			}
			workbook.write(fileOutputStream);
		}
		
		return createTempFile;
	}

	private List<IssueLog> readXls(File file) throws Exception {
		
		List<IssueLog> list = new ArrayList<>();
		LocalDate now = LocalDate.now();
		
		try (
				FileInputStream fis = new FileInputStream(file);
				HSSFWorkbook workbook = new HSSFWorkbook(fis)
			) {
			
			HSSFSheet sheetAt = workbook.getSheetAt(0);
			
			for(int i = 2; (sheetAt.getRow(i) != null && StringUtils.isNotBlank(typeToString(sheetAt.getRow(i).getCell(0)))); i++) {
				
				HSSFRow row = sheetAt.getRow(i);
				IssueLog issueLog = new IssueLog();
				
				//성적서 번호
				String reportNumber = null;
				try {
					reportNumber = typeToString(row.getCell(0));
					if(StringUtils.isBlank(reportNumber)) {
						throw new Exception((i+1)+"행 A열 성적서번호는 필수값입니다.");
					}
					issueLog.setReportNumber(reportNumber);
				}catch (Exception e) {
					throw new Exception((i+1)+"행 A열 성적서번호 형식이 맞지 않습니다.");
				}
				//성적서 번호
				
				//접수번호 생성
				try {
					String[] split = reportNumber.split("-");
					int length = split[0].length();
					
					
					if(length < 8) {
						throw new Exception((i+1)+"행 성적서번호 형식이 맞지 않습니다.");
					}
					issueLog.setRegNumber("H"+split[0].substring(0, 2)+split[0].substring(4, length)+"-"+split[1]);
				}catch (Exception e) {
					e.printStackTrace();
					throw new Exception((i+1)+"행 성적서번호 형식이 맞지 않습니다.");
				}
				//접수번호 생성
				
				//의뢰업체
				try {
					Customer requestCustomer = customerRepository.findByName(typeToString(row.getCell(1)));
					issueLog.setRequestCustomer(requestCustomer);
				}catch (Exception e) {
					throw new Exception((i+1)+"행 존재하지 않는 의뢰업체명이거나 중복된 업체명이 있습니다. 거래처를 확인해주세요");
				}
				//의뢰업체
				
				//업체명
				try {
					Customer customer = customerRepository.findByName(typeToString(row.getCell(2)));
					if(customer == null) {
						throw new Exception((i+1)+"행 존재하지 않는 업체명이거나 중복된 업체명이 있습니다. 거래처를 확인해주세요");
					}
					issueLog.setCustomer(customer);
				}catch (Exception e) {
					throw new Exception((i+1)+"행 존재하지 않는 업체명이거나 중복된 업체명이 있습니다. 거래처를 확인해주세요");
				}
				//업체명
				
				//기기명
				try {
					issueLog.setDeviceName(typeToString(row.getCell(3)));
				}catch (Exception e) {
					throw new Exception((i+1)+"행 기기명은 필수값입니다.");
				}
				//기기명
				
				//제조회사
				try {
					issueLog.setProductionCompany(typeToString(row.getCell(4)));
				}catch (Exception e) {
					throw new Exception((i+1)+"행 제조회사 타입에러");
				}
				//제조회사
				
				//모델
				try {
					issueLog.setModel(typeToString(row.getCell(5)));
				}catch (Exception e) {
					throw new Exception((i+1)+"행 모델 타입에러");
				}
				//모델
				
				//기기번호
				try {
					issueLog.setDeviceNumber(typeToString(row.getCell(6)));
				}catch (Exception e) {
					throw new Exception((i+1)+"행 기기번호 타입에러");
				}
				//기기번호
				
				//규격
				try {
					issueLog.setStandard(typeToString(row.getCell(7)));
				}catch (Exception e) {
					throw new Exception((i+1)+"행 규격 타입에러");
				}
				//규격
				
				//분해능
				try {
					HSSFCell cell8 = row.getCell(8);
					String sell8String = null;
					if(cell8 != null && cell8.getCellType() == CellType.NUMERIC) {
						sell8String = String.valueOf(cell8.getNumericCellValue());
					}else if(cell8 != null){
						sell8String = cell8.getStringCellValue();
					}
					
					issueLog.setResolution(sell8String);
				}catch (Exception e) {
					throw new Exception((i+1)+"행 분해능 타입에러");
				}	
				//분해능
				
				//단위
				try {
					issueLog.setUnit(typeToString(row.getCell(9)));
				}catch (Exception e) {
					throw new Exception((i+1)+"행 K열 타입에러");
				}
				//단위
				
				//교정일
				try {
					Date date1 = row.getCell(10).getDateCellValue();
					LocalDate correctionDate = Instant.ofEpochMilli(date1.getTime())
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate();
					issueLog.setCorrectionDate(correctionDate);
				}catch (Exception e) {
					throw new Exception((i+1)+"행 교정일 날짜 타입이 잘못되었습니다.");
				}
				//교정일
				
				//장소
				try {
					issueLog.setPlace(typeToString(row.getCell(11)));
				}catch (Exception e) {
					throw new Exception((i+1)+"행 장소 타입에러");
				}
				//장소
				
				//실무자
				try {
					issueLog.setPractitioner(typeToString(row.getCell(12)));
				}catch (Exception e) {
					throw new Exception((i+1)+"행 실무자 타입에러");
				}
				//실무자
				
				//중분류
				try {
					issueLog.setMiddleCategory(typeToString(row.getCell(13)));
				}catch (Exception e) {
					throw new Exception((i+1)+"행 중분류 타입에러");
				}
				//중분류
				
				//소분류
				try {
					issueLog.setSmallCategory(typeToString(row.getCell(14)));
				}catch (Exception e) {
					throw new Exception((i+1)+"행 소분류 타입에러");
				}
				//소분류
				
				//발행일자
				try {
					Date date2 = row.getCell(15).getDateCellValue();
					LocalDate publishedDate = Instant.ofEpochMilli(date2.getTime())
							.atZone(ZoneId.systemDefault())
							.toLocalDate();
					issueLog.setPublishedDate(publishedDate);
				}catch (Exception e) {
					throw new Exception((i+1)+"행 발행일 날짜 타입이 잘못되었습니다.");
				}
				//발행일자
				
				//기술책임자
				try {
					issueLog.setTechnicalManager(typeToString(row.getCell(16)));
					issueLog.setRegDt(now);
				}catch (Exception e) {
					throw new Exception((i+1)+"행 기술책임자 타입에러");
				}
				//기술책임자
				
				//성적서유형
				try {
					HSSFCell cell16 = row.getCell(17);
					if(cell16 != null) {
						issueLog.setReportLanguage(ReportLanguage.findEnum(typeToString(cell16)));
					}
				}catch (Exception e) {
					throw new Exception((i+1)+"행 성적서유형 타입이잘못되었습니다."+e.getMessage());
				}
				//성적서유형
				
				//교정료
				try {
					issueLog.setCorrectionFee((long)row.getCell(18).getNumericCellValue());
				}catch (Exception e) {
					throw new Exception((i+1)+"행 교정료 타입에러");
				}
				//교정료
				
				
				//HPM구분
				try {
					if(row.getCell(19) != null && StringUtils.isNotBlank(typeToString(row.getCell(19)))) {
						issueLog.setHpmType(typeToString(row.getCell(19)));
					}else {
						throw new Exception((i+1)+"행 Hpm 구분은 필수값입니다.");
					}
				}catch (Exception e) {
					throw new Exception((i+1)+"행 Hpm 구분은 필수값입니다.");
				}
				//HPM구분
				
				//접수일
				try {
					Date appliRegDate = row.getCell(20).getDateCellValue();
					if(appliRegDate != null) {
						LocalDate appliRegLocalDate = Instant.ofEpochMilli(appliRegDate.getTime())
								.atZone(ZoneId.systemDefault())
								.toLocalDate();
						issueLog.setAppliRegDate(appliRegLocalDate);
					}else {
						throw new Exception((i+1)+"행 접수일은 필수값입니다.");
					}
				}catch (Exception e) {
					throw new Exception((i+1)+"행 접수일은 필수값입니다.");
				}
				//접수일
				
				//등록일
				issueLog.setRegDt(now);
				
				issueLogRepository.save(issueLog);
				list.add(issueLog);
			}
		}
		
		return list;
	}
	
	private List<IssueLog> readXlsx(File file) throws Exception {
		
		List<IssueLog> list = new ArrayList<>();
		FileInputStream fis = new FileInputStream(file);
		LocalDate now = LocalDate.now();
		try (HSSFWorkbook workbook = new HSSFWorkbook(fis)) {
			HSSFSheet sheetAt = workbook.getSheetAt(0);
			
			for(int i = 1; StringUtils.isNotBlank(sheetAt.getRow(i).getCell(5).getStringCellValue()); i++) {
				HSSFRow row = sheetAt.getRow(i);
				IssueLog issueLog = new IssueLog();
				issueLog.setRegNumber(row.getCell(3).getStringCellValue().substring(4, 8));
				issueLog.setMiddleCategory(setLPad(typeToString(row.getCell(1)),4,"0"));
				issueLog.setReportNumber(row.getCell(3).getStringCellValue());
				
				//의뢰업체
				try {
					Customer requestCustomer = customerRepository.findByName(typeToString(row.getCell(1)));
					issueLog.setRequestCustomer(requestCustomer);
				}catch (Exception e) {
					throw new Exception((i+1)+"행 의뢰업체 형식이 맞지 않습니다.");
				}
				//의뢰업체
				String stringCellValue = row.getCell(5).getStringCellValue();
				Customer customer = customerRepository.findByName(stringCellValue);
				
				if(customer == null) {
					throw new Exception((i+1)+"행 F열 존재하지 않는 회사 명입니다. 회사명을 확인 해주세요.");
				}
				issueLog.setCustomer(customer);
				
				issueLog.setDeviceName(row.getCell(6).getStringCellValue());
				issueLog.setProductionCompany(row.getCell(7).getStringCellValue());
				issueLog.setDeviceNumber(row.getCell(5).getStringCellValue());
				issueLog.setStandard(typeToString(row.getCell(9)));
				issueLog.setUnit(row.getCell(10).getStringCellValue());
				
				try {
					Date date1 = row.getCell(11).getDateCellValue();
					LocalDate correctionDate = Instant.ofEpochMilli(date1.getTime())
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate();
					issueLog.setCorrectionDate(correctionDate);
				}catch (Exception e) {
					throw new Exception((i+1)+"행 L열 날짜 타입이 잘못되었습니다.");
				}
				
				issueLog.setPlace(row.getCell(12).getStringCellValue());
				issueLog.setPractitioner(row.getCell(13).getStringCellValue());
				issueLog.setSmallCategory(typeToString(row.getCell(14)));
				
				try {
					Date date2 = row.getCell(15).getDateCellValue();
					LocalDate publishedDate = Instant.ofEpochMilli(date2.getTime())
							.atZone(ZoneId.systemDefault())
							.toLocalDate();
					issueLog.setPublishedDate(publishedDate);
				}catch (Exception e) {
					throw new Exception((i+1)+"행 P열 날짜 타입이 잘못되었습니다.");
				}
				try {
					HSSFCell cell16 = row.getCell(16);
					if(cell16 != null) {
						issueLog.setReportLanguage(ReportLanguage.findEnum(cell16.getStringCellValue()));
					}
				}catch (Exception e) {
					throw new Exception((i+1)+"행 Q열 국문영문 타입이잘못되었습니다."+e.getMessage());
				}
				issueLog.setTechnicalManager(typeToString(row.getCell(18)));
				issueLog.setRegDt(now);
				issueLogRepository.save(issueLog);
			}
			
			
		}
		
		return list;
	}
	
	private String typeToString(CellBase cell) {
		
		if(cell == null) {
			return null;
		}
		if(cell.getCellType() == CellType.NUMERIC) {
			
			return String.valueOf((long)cell.getNumericCellValue());
		}
		
		return cell.getStringCellValue();
	}
	
	private String typeToString(HSSFCell cell) {
	
		if(cell == null) {
			return null;
		}
		
		if(cell.getCellType() == CellType.NUMERIC) {
			return String.valueOf((long)cell.getNumericCellValue());
		}
		
		return cell.getStringCellValue();
	}
	
	
	private static String setLPad( String strContext, int iLen, String strChar ) { 
		String strResult = ""; 
		StringBuilder sbAddChar = new StringBuilder(); 
		for( int i = strContext.length(); i < iLen; i++ ) {
			// iLen길이 만큼 strChar문자로 채운다. 
			sbAddChar.append( strChar ); 
		} 
		
		strResult = sbAddChar + strContext; // LPAD이므로, 채울문자열 + 원래문자열로 Concate한다. 
		return strResult; 
	}

	
}
