package com.nik.hpm.attachfile.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nik.hpm.attachfile.entity.AttachFile;
import com.nik.hpm.attachfile.repository.AttachFileRepository;

/**
 * FileService
 */
@Service
public class FileService {
    
    @Autowired
    AttachFileRepository attachFileRepository;
    
    public Optional<AttachFile> one(long id){
        return attachFileRepository.findById(id);
    }
    
}