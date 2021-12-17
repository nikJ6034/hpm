package com.nik.hpm.util.file.excel.hpm;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HpmApplicationExcelTest {
	
	@Autowired
	HpmApplicationExcel hpmApplicationExcel;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testWriteExcel() throws IOException {
	}

}
