package com.limitfileextension.service;

import com.limitfileextension.domain.FileExtension;
import com.limitfileextension.domain.FileExtensionRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class FileExtensionServiceTest {
    @Autowired
    private FileExtensionService fileExtensionService;

    @Autowired
    private FileExtensionRepository fileExtensionRepository;

    @Test
    @DisplayName("고정확장자 차단 상태 변경")
    void test1(){
        //given
        Long id = 1L;

        //when
        fileExtensionService.update_Fix_Extension(id, Boolean.TRUE);

        //then
        FileExtension updated =  fileExtensionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no such data"));
        assertEquals(Boolean.TRUE,updated.isChecked());
    }

    @Test
    @DisplayName("커스텀 확장자 신규 추가")
    void test2(){
        //given
        String name = "sh";

        //when
        fileExtensionService.save_Custom_Extension(name);

        //then
        FileExtension fileExtension = fileExtensionRepository.findByName(name);
        assertEquals(name,fileExtension.getName());

    }



}