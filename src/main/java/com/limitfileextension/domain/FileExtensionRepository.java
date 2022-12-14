package com.limitfileextension.domain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileExtensionRepository extends JpaRepository<FileExtension,Long> {
    FileExtension findByName(String name);
    List<FileExtension> findByType(ExtensionType type);

    @Override
    void deleteById(Long id);
}
