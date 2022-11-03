package com.limitfileextension.dto;

import com.limitfileextension.domain.ExtensionType;
import com.limitfileextension.domain.FileExtension;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseDto {

    private Long id;
    private String name; //확장자명
    private ExtensionType type; //고정 or 커스텀 확장자
    private boolean isChecked; //고정확장자 체크여부 (커스텀확장자 = false)

    //entity to DTO
    public ResponseDto(FileExtension entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.type = entity.getType();
        this.isChecked = entity.isChecked();
    }
}
