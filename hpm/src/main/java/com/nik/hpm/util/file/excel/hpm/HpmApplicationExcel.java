package com.nik.hpm.util.file.excel.hpm;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nik.hpm.application.vo.ApplicationLogVO;
import com.nik.hpm.application.vo.ApplicationVO;
import com.nik.hpm.attachfile.entity.AttachFile;
import com.nik.hpm.enumcode.Yn;

@Component
public class HpmApplicationExcel {

	@Value("${uploads.filePath}")
    private String uploadFilePath;
	
	public File writeExcel(ApplicationVO application) throws IOException {
		
		File createTempFile = File.createTempFile(uploadFilePath+"/document/hpm/temp", ".xlsm");
		
		File copyFile = new File(uploadFilePath+"/document/hpm/신청서.xlsm");
		try (
				FileInputStream file = new FileInputStream(copyFile);
				XSSFWorkbook workbook = new XSSFWorkbook(file);
				FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
			) {
			workbook.setForceFormulaRecalculation(true);
			XSSFSheet sheetAt = workbook.getSheetAt(0);
			
			XSSFRow row1 = sheetAt.getRow(1);
			
			if(!application.getApplicationLogList().isEmpty()) {
				
				row1.getCell(10).setCellValue(application.getApplicationLogList().get(0).getRegNumber());
			}
			
			XSSFRow row3 = sheetAt.getRow(3);
			row3.getCell(3).setCellValue(application.getCustomer().getName());
			row3.getCell(7).setCellValue(application.getCustomer().getRepName());
			
			XSSFRow row4 = sheetAt.getRow(4);
			row4.getCell(3).setCellValue(application.getCustomer().getCompanyRegNumber());
			row4.getCell(7).setCellValue(application.getCustomer().getBizCondition());
			row4.getCell(9).setCellValue(application.getCustomer().getItem());
			
			XSSFRow row5 = sheetAt.getRow(5);
			row5.getCell(3).setCellValue(application.getCustomer().getAdress());
			
			XSSFRow row6 = sheetAt.getRow(6);
			row6.getCell(3).setCellValue(application.getCustomer().getTel());
			row6.getCell(7).setCellValue(application.getCustomer().getFax());
			
			// 성적서 발행 업체명
			XSSFRow row8 = sheetAt.getRow(8);
			if(application.getCustomerSameYn() == Yn.Y) {
				row8.getCell(2).setCellValue("동일");
			}else {
				row8.getCell(2).setCellValue("다름");
				row8.getCell(4).setCellValue(application.getRequestCustomerName());
				XSSFRow row9 = sheetAt.getRow(9);
				row9.getCell(4).setCellValue(application.getRequestCustomerAddress());
			}
			
			
			//현장교정
			XSSFRow row10 = sheetAt.getRow(10);
			if(application.getFieldCorrectionNeedYn() == Yn.Y) {
				row10.getCell(2).setCellValue("필요");
			}else {
				row10.getCell(2).setCellValue("해당사항없음");
			}
			
			//필증상에 권장교정일 주기 기입여부
			if(application.getRecCalibrationDayYn() == Yn.Y) {
				row10.getCell(9).setCellValue("필요");
			}else {
				row10.getCell(9).setCellValue("불필요");
			}
			
			//언어
			if(application.getReportLanguage() != null) {
				row10.getCell(4).setCellValue(application.getReportLanguage().getValue());
			}
			
			XSSFRow row11 = sheetAt.getRow(11);
			if(application.getAppliRegDateType() != null) {
				row11.getCell(0).setCellValue(application.getAppliRegDateType().getName());
			}
			// 신청일
			row11.getCell(3).setCellValue(application.getAppliRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			// 신청인
			row11.getCell(7).setCellValue(application.getApplicant());
			
			//사인 이미지
			if(application.getCustomerSignImg() != null) {
				AttachFile customerSignImg = application.getCustomerSignImg();
				// 이미지 파일 로드
				String signImgPath = customerSignImg.getFullPath()+File.separator+customerSignImg.getVirtualName();
				File signImgFile = new File(signImgPath);
	            BufferedImage read = ImageIO.read(signImgFile);
	            BufferedImage resize = Scalr.resize(read, 80, 50);
	            
	            String extension = FileNameUtils.getExtension(signImgFile.getName());
	            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	            ImageIO.write(resize,extension,byteArrayOutputStream);
	            byteArrayOutputStream.close();
	            int pictureIdx = workbook.addPicture(byteArrayOutputStream.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG);
	            XSSFCreationHelper creationHelper = workbook.getCreationHelper();
        		XSSFDrawing createDrawingPatriarch = sheetAt.createDrawingPatriarch();
	            XSSFClientAnchor createClientAnchor = creationHelper.createClientAnchor();
				
				createClientAnchor.setCol1(9);
				createClientAnchor.setRow1(11);
				
				XSSFPicture createPicture = createDrawingPatriarch.createPicture(createClientAnchor, pictureIdx);
				createPicture.resize();
			}
			
			XSSFRow row14 = sheetAt.getRow(14);
			row14.getCell(7).setCellValue(application.getApplicantTel());
			
			XSSFRow row15 = sheetAt.getRow(15);
			row15.getCell(7).setCellValue(application.getApplicantEmail());
			
			List<ApplicationLogVO> applicationLogList = application.getApplicationLogList();
			
			int rowNum = 17;
			int pageCount = 0;
			for(ApplicationLogVO applog : applicationLogList) {
				XSSFRow applogRow = sheetAt.getRow(rowNum);
				applogRow.getCell(1).setCellValue(applog.getDeviceName());
				applogRow.getCell(3).setCellValue(applog.getProductionCompany());
				if(applog.getStandard() == null && applog.getModel() != null) {
					applogRow.getCell(4).setCellValue(applog.getModel());
				}else if(applog.getStandard() != null && applog.getModel() == null) {
					applogRow.getCell(4).setCellValue(applog.getStandard());
				}else if(applog.getStandard() != null && applog.getModel() != null) {
					applogRow.getCell(4).setCellValue(applog.getStandard() + " / " +applog.getStandard());
				}
				
				applogRow.getCell(6).setCellValue(applog.getDeviceNumber());
				applogRow.getCell(7).setCellType(CellType.NUMERIC);
				applogRow.getCell(7).setCellValue(applog.getQuantity());
				applogRow.getCell(8).setCellValue(applog.getEtc());
				rowNum++;
				
				if(rowNum == 29) {
					rowNum = 52;
				}
				
				if(pageCount == 35) {
					pageCount = 0;
					rowNum += 6;
				}
				
				if(rowNum >= 52) {
					pageCount++;
				}
				
				
			}
			
			XSSFRow row40 = sheetAt.getRow(40);
			if(application.getAppliRegDate() != null) {
				row40.getCell(6).setCellValue(application.getAppliRegDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
			}
			
			XSSFRow row41 = sheetAt.getRow(41);
			if(application.getRegMember() != null) {
				row41.getCell(7).setCellValue(application.getRegMember().getName());
			}
			
			XSSFRow row42 = sheetAt.getRow(42);
			if(application.getTakeOverDate() != null) {
				row42.getCell(7).setCellValue(application.getTakeOverDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
			}
			
			XSSFRow row43 = sheetAt.getRow(43);
			if(application.getTakeOverType() != null) {
				row43.getCell(2).setCellValue(application.getTakeOverType().getValue());
			}
			if(application.getConsignee() != null) {
				row43.getCell(7).setCellValue(application.getConsignee());
			}
			
			XSSFRow row46 = sheetAt.getRow(46);
			row46.getCell(8).setCellValue(application.getTechnicalManager());
			
			XSSFRow row88 = sheetAt.getRow(88);
			if(application.getTakeOverType() != null) {
				row88.getCell(2).setCellValue(application.getTakeOverType().getValue());
			}
			row88.getCell(7).setCellValue(application.getConsignee());
			
			workbook.write(fileOutputStream);
			fileOutputStream.close();
		}
		
		return createTempFile;
		
	}
}
