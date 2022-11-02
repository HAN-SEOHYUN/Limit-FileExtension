package com.limitfileextension.service;
import com.limitfileextension.domain.ExtensionType;
import com.limitfileextension.domain.FileExtension;
import com.limitfileextension.domain.FileExtensionRepository;
import com.limitfileextension.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FileExtensionService {

    private final FileExtensionRepository fileExtensionRepository;

    //고정확장자를 찾아서 체크여부를 요청된 값으로 변경해주는 메서드
    @Transactional
    public void update_Fix_Extension(Long id, boolean isChecked){
        FileExtension fileExtension = fileExtensionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no such data"));
        fileExtension.updateisChecked(isChecked);
        fileExtensionRepository.save(fileExtension);
    }


    //요청한 name의 커스텀 확장자를 등록해주는 메서드
    @Transactional
    public void save_Custom_Extension(String name){
        FileExtension fileExtension = FileExtension.builder()
                .name(name)
                .type(ExtensionType.CUSTOM)
                .build();
        new ResponseDto(fileExtensionRepository.save(fileExtension));
    }

    //요청한 커스텀확장자 이름이
    //이미 등록된 커스텀 확장자이거나,
    //고정확장자 목록에 존재하는 경우 false를 반환하는 메서드
    @Transactional
    public boolean exist_check_inFixList(String name){
        List<FileExtension> fileExtensionList = fileExtensionRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
        for(FileExtension fileExtension : fileExtensionList){
            if(fileExtension.getName().equals(name)){
                return false;
            }
        }
        return true;
    }


    //확장자 제한 정보를 조회하는 메서드
    //고정, 커스텀 확장자 데이터를 각 List에 담아 map으로 리턴합니다 (오름차순)
    @Transactional
    public HashMap<ExtensionType, List<ResponseDto>> limited_Extensions(){
        List<FileExtension> fileExtensionList = fileExtensionRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
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


    @Transactional
    public void delete_custom_extension(Long id){
        fileExtensionRepository.deleteById(id);
    }
}
