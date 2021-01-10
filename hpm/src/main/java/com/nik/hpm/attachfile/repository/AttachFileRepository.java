package com.nik.hpm.attachfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.hpm.attachfile.entity.AttachFile;

/**
 * AttachFileRepository
 */
public interface AttachFileRepository extends JpaRepository<AttachFile, Long>{

    
}