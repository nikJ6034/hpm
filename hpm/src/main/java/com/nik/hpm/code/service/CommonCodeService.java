package com.nik.hpm.code.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nik.hpm.code.entity.CommonCode;
import com.nik.hpm.code.repository.CommonCodeRepository;
import com.nik.hpm.code.vo.CommonCode1depthVO;
import com.nik.hpm.code.vo.CommonCodeSearchVO;
import com.nik.hpm.code.vo.CommonCodeVO;

@Service
@Transactional
public class CommonCodeService {
	
	@Autowired
	CommonCodeRepository commonCodeRepository;
	
	public CommonCodeVO code(CommonCode commonCode) {
		return new CommonCodeVO(commonCodeRepository.findById(commonCode.getId()));
	}
	
	public CommonCode codeByCode(CommonCode commonCode) {
		return commonCodeRepository.findByCode(commonCode.getCode());
	}
	
	public Page<CommonCodeVO> codeList(CommonCodeSearchVO commonCodeSearchVO, Pageable pageable){
		return commonCodeRepository.codeList(commonCodeSearchVO, pageable);
	}
	
	public CommonCode codeList(CommonCode commonCode){
		return commonCodeRepository.code(commonCode);
	}
	
	public void codeModify(CommonCodeVO commonCodeVO) {
		CommonCode com = commonCodeRepository.findById(commonCodeVO.getId());

			com.setCodeDesc(commonCodeVO.getCodeDesc());
			
			commonCodeVO.getCodeList().forEach(code->{
				
				if(code.isDel()) {
					CommonCode commonCode = new CommonCode();
					commonCode.setId(code.getId());
					commonCodeRepository.delete(commonCode);
				}
				
				if(code.isEdite()) {
					CommonCode commonCode = new CommonCode();
					commonCode.setId(code.getId());
					commonCode.setCode(code.getCode());
					commonCode.setCodeName(code.getCodeName());
					commonCode.setCodeDesc(code.getCodeDesc());
					commonCode.setPid(com);
					commonCodeRepository.save(commonCode);
				}
				
			});
			
		
	}
	
	public CommonCode codeCreate(CommonCodeVO commonCodeVO) {
		CommonCode pcommonCode = new CommonCode();
		pcommonCode.setCode(commonCodeVO.getCode());
		pcommonCode.setCodeName(commonCodeVO.getCodeName());
		pcommonCode.setCodeDesc(commonCodeVO.getCodeDesc());
		CommonCode pcode = commonCodeRepository.save(pcommonCode);
		
		commonCodeVO.getCodeList().forEach(code->{
			CommonCode commonCode = new CommonCode();
			commonCode.setCode(code.getCode());
			commonCode.setCodeName(code.getCodeName());
			commonCode.setCodeDesc(code.getCodeDesc());
			commonCode.setPid(pcode);
			commonCodeRepository.save(commonCode);
		});
		
		return pcode;
	}
	
	public void codeDelete(CommonCode commonCode) {
		commonCodeRepository.delete(commonCodeRepository.findById(commonCode.getId()));
		
	}
	
	public boolean existsCode(CommonCodeVO commonCodeVO) {
		return commonCodeRepository.countByCodeAndPid(commonCodeVO.getCode(), null) > 0;
	}

	public CommonCode1depthVO cCode(CommonCodeSearchVO commonCode) {
		return new CommonCode1depthVO(commonCodeRepository.cCode(commonCode));
	}

}
