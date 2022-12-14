package com.limitfileextension.dto;

import com.limitfileextension.domain.ExtensionType;
import com.limitfileextension.domain.FileExtension;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDto {

    String name;
    ExtensionType type;

    public FileExtension toEntity() {
        return FileExtension.builder()
                .name(name)
                .type(type)
                .build();
    }
}
