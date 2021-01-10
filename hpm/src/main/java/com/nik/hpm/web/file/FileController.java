package com.nik.hpm.web.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.nik.hpm.attachfile.entity.AttachFile;
import com.nik.hpm.attachfile.service.FileService;
import com.nik.hpm.util.file.image.UploadResourceImageFileUtil;

/**
 * FileDownloadController
 */
@RestController
public class FileController {

    @Value("${uploads.filePath}")
    private String filePath;
    @Autowired
    FileService fileService;
    @Autowired
    UploadResourceImageFileUtil uploadResourceImageFileUtil;
    

    @GetMapping(value = "/api/file/{id}")
    public ResponseEntity<InputStreamResource> download(@PathVariable Long id) {
        Optional<AttachFile> one = fileService.one(id);

        return one.map(attachFile -> {
            File file = new File(attachFile.getFullPath() +File.separator+ attachFile.getVirtualName());
            try {
                HttpHeaders header = new HttpHeaders();
                header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + attachFile.getRealName());
                header.add("Cache-Control", "no-cache, no-store, must-revalidate");
                header.add("Pragma", "no-cache");
                header.add("Expires", "0");

                InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
                return ResponseEntity.ok().headers(header).contentLength(file.length())
                        .contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }).get();
    }
    
    @PostMapping(value = "/api/img", consumes = "multipart/form-data")
	public List<AttachFile> insert(MultipartHttpServletRequest mtfRequest) {
		List<MultipartFile> files = mtfRequest.getFiles("files");
		List<AttachFile> list = new ArrayList<AttachFile>();
		
		files.forEach(f->{
			try {
				AttachFile imageUploadWithThumbnail = uploadResourceImageFileUtil.imageUpload(f.getOriginalFilename(), f.getBytes(),500);
				imageUploadWithThumbnail.setFullPath(""); //전체 경로는 보안상 이유로 삭제함.
				list.add(imageUploadWithThumbnail);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		return list;
	}
    
}