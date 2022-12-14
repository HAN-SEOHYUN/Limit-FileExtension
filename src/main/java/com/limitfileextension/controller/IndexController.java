package com.limitfileextension.controller;

import com.limitfileextension.domain.ExtensionType;
import com.limitfileextension.dto.ResponseDto;
import com.limitfileextension.service.FileExtensionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final FileExtensionService fileExtensionService;

    //INDEX : 저장된 확장자정보 조회 API
    @GetMapping("/")
    public String index(Model model){
       HashMap<ExtensionType, List<ResponseDto>> map = fileExtensionService.limited_Extensions();
       model.addAttribute("fixList",map.get(ExtensionType.FIX));
       model.addAttribute("customList",map.get(ExtensionType.CUSTOM));
       model.addAttribute("total", map.get(ExtensionType.CUSTOM).size());
        return "index";
    }
}
