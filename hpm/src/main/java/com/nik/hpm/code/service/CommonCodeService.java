package com.nik.hpm.code.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nik.hpm.code.entity.CommonCode;
import com.nik.hpm.code.repository.CommonCodeRepository;
import com.nik.hpm.code.vo.CommonCodeSearchVO;
import com.nik.hpm.code.vo.CommonCodeVO;

@Service
@Transactional
public class CommonCodeService {
	
	@Autowired
	CommonCodeRepository commonCodeRepository;
	
	public CommonCode code(CommonCode commonCode) {
		return commonCodeRepository.findById(commonCode.getId()).get();
	}
	
	public CommonCode codeByCode(CommonCode commonCode) {
		return commonCodeRepository.findByCode(commonCode.getCode());
	}
	
	public Page<CommonCodeVO> codeList(CommonCodeSearchVO commonCodeSearchVO, Pageable pageable){
		return commonCodeRepository.codeList(commonCodeSearchVO, pageable);
	}
	
	public void codeModify(CommonCodeVO commonCodeVO) {
		Optional<CommonCode> findById = commonCodeRepository.findById(commonCodeVO.getId());

		findById.ifPresent(com->{
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
			commonCode.setCode(commonCodeVO.getCode());
			commonCode.setCodeName(commonCodeVO.getCodeName());
			commonCode.setCodeDesc(commonCodeVO.getCodeDesc());
			commonCode.setPid(pcode);
			commonCodeRepository.save(commonCode);
		});
		
		return pcode;
	}
	
	public void codeDelete(CommonCode commonCode) {
		Optional<CommonCode> findById = commonCodeRepository.findById(commonCode.getId());
		findById.ifPresent(com->{
			commonCodeRepository.delete(com);
		});
		
	}
	
	public boolean existsCode(CommonCodeVO commonCodeVO) {
		return commonCodeRepository.countByCodeAndPid(commonCodeVO.getCode(), null) > 0;
	}

}
