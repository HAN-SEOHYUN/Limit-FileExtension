package com.limitfileextension.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FileExtension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //PK

    private String name; //확장자명

    @Enumerated(EnumType.STRING)
    private ExtensionType type; //고정 or 커스텀확장자

    private boolean isChecked; //고정확장자 CHECK 여부

    @Builder
    public FileExtension(Long id, String name, ExtensionType type, boolean isChecked) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isChecked = isChecked;
    }

    public void updateisChecked(boolean isChecked){
        this.isChecked = isChecked;
    }
}
