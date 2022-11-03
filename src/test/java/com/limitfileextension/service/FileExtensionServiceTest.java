package com.limitfileextension.service;

import com.limitfileextension.domain.ExtensionType;
import com.limitfileextension.domain.FileExtension;
import com.limitfileextension.domain.FileExtensionRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class FileExtensionServiceTest {

    //서버 배포를 위한 빌드과정에서 build failed 되어 테스트코드 주석처리


    @Autowired
    private FileExtensionService fileExtensionService;

    @Autowired
    private FileExtensionRepository fileExtensionRepository;

    @BeforeEach
    void clean(){
        fileExtensionRepository.deleteAll();
    }

    @Test
    @DisplayName("고정확장자 차단 상태 변경")
    void test1() {
        //given
        Long id = 1L;

        //when
        fileExtensionService.update_Fix_Extension(id, Boolean.TRUE);

        //then
        FileExtension updated = fileExtensionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no such data"));
        assertEquals(Boolean.TRUE, updated.isChecked());
    }

    @Test
    @DisplayName("커스텀 확장자 신규 추가")
    void test2() {
        //given
        String name = "sh";

        //when
        fileExtensionService.save_Custom_Extension(name);

        //then
        FileExtension fileExtension = fileExtensionRepository.findByName(name);
        assertEquals(name, fileExtension.getName());

    }

    @Test
    @DisplayName("커스텀확장자는 200개까지만 저장가능")
    void test3(){
        //given
        for(;;){
            fileExtensionService.save_Custom_Extension("test");
            if(fileExtensionService.limited_Extensions().get(ExtensionType.CUSTOM).size()==201){
                break;
            }
        }

        //when
        boolean result = fileExtensionService.check_total_custom();

        //then
        assertEquals(Boolean.FALSE,result);

    }
}