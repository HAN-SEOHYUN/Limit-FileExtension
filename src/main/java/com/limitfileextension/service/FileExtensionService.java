package com.limitfileextension.service;
import com.limitfileextension.domain.ExtensionType;
import com.limitfileextension.domain.FileExtension;
import com.limitfileextension.domain.FileExtensionRepository;
import com.limitfileextension.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FileExtensionService {

    private final FileExtensionRepository fileExtensionRepository;

    //고정확정자 차단 상태 변경
    @Transactional
    public void update_Fix_Extension(Long id, boolean isChecked){
        FileExtension fileExtension = fileExtensionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no such data"));
        fileExtension.updateisChecked(isChecked);
        fileExtensionRepository.save(fileExtension);
    }

    //커스텀 확장자 추가
    @Transactional
    public void save_Custom_Extension(String name){
        FileExtension fileExtension = FileExtension.builder()
                .name(name)
                .type(ExtensionType.CUSTOM)
                .build();
        new ResponseDto(fileExtensionRepository.save(fileExtension));
    }

    //조회
    @Transactional
    public HashMap<ExtensionType, List<ResponseDto>> limited_Extensions(){
        List<FileExtension> fileExtensionList = fileExtensionRepository.findAll();
        List<ResponseDto> customList = new ArrayList<>();
        List<ResponseDto> fixedList = new ArrayList<>();

        for(FileExtension fileExtension : fileExtensionList){
            ResponseDto responseDto = new ResponseDto(fileExtension); //entity to DTO
            if(fileExtension.getType()==ExtensionType.CUSTOM){
                customList.add(responseDto);
            }else{
                fixedList.add(responseDto);
            }
        }

        HashMap<ExtensionType,List<ResponseDto>> map = new HashMap<>();
        map.put(ExtensionType.FIX,fixedList);
        map.put(ExtensionType.CUSTOM,customList);
        return map;
    }
}
