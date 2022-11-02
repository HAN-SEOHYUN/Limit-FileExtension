package com.limitfileextension.dto;

import com.limitfileextension.domain.ExtensionType;
import com.limitfileextension.domain.FileExtension;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDto {

    private Long id;
    private String name; //확장자명
    private ExtensionType type;
    private boolean isChecked;

    //entity to DTO
    public ResponseDto(FileExtension entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.type = entity.getType();
        this.isChecked = entity.isChecked();
    }
}
