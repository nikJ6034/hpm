package com.nik.hpm.web.commonCodeController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nik.hpm.code.entity.CommonCode;
import com.nik.hpm.code.service.CommonCodeService;
import com.nik.hpm.code.vo.CommonCodeSearchVO;
import com.nik.hpm.code.vo.CommonCodeVO;

@RestController
public class CommonCodeController {
	
	@Autowired
	CommonCodeService commonCodeService;
	
	@GetMapping(value= "/api/code/{id}")
	public CommonCode code(CommonCode commonCode) {
		return commonCodeService.code(commonCode);
	}
	
	@GetMapping(value= "/api/code/code/{code}")
	public CommonCode code2(CommonCode commonCode) {
		return commonCodeService.codeByCode(commonCode);
	}
	
	@GetMapping(value= "/api/code")
	public 	Page<CommonCodeVO> codeList(CommonCodeSearchVO commonCodeSearchVO, @PageableDefault(size = 10)Pageable pageable){
		Page<CommonCodeVO> codeList = commonCodeService.codeList(commonCodeSearchVO, pageable);
		return codeList;
	}
	
	@PostMapping(value= "/api/code")
	public Map<String, Object> codeCreate(@RequestBody CommonCodeVO commonCodeVO){
		Map<String, Object> map = new HashMap<>();
		
		if(commonCodeService.existsCode(commonCodeVO)) {
			map.put("result", "fail");
			map.put("msg", "이미존재하는 코드입니다.");
		}else {
			CommonCode codeCreate = commonCodeService.codeCreate(commonCodeVO);
			map.put("result", "success");
			map.put("msg", "등록 성공");
			map.put("code", codeCreate);
		}
		return map;
	}
	
	@DeleteMapping(value= "/api/code")
	public Map<String, Object> companyDelete(@RequestBody CommonCode commonCode){
		Map<String, Object> map = new HashMap<>();
		commonCodeService.codeDelete(commonCode);
		
		map.put("result", "success");
		map.put("msg", "삭제 성공");
		
		return map;
	}
	
	@PutMapping(value= "/api/code")
	public Map<String, Object> companyModify(@RequestBody CommonCodeVO commonCodeVO){
		Map<String, Object> map = new HashMap<>();
		
		commonCodeService.codeModify(commonCodeVO);
		map.put("result", "success");
		map.put("msg", "수정 성공");
		
		return map;
	}

}
