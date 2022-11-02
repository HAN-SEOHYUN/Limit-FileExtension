package com.limitfileextension.controller;

import com.limitfileextension.domain.ExtensionType;
import com.limitfileextension.dto.ResponseDto;
import com.limitfileextension.service.FileExtensionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/extension")
public class ExtensionController {
    private final FileExtensionService fileExtensionService;

    @RequestMapping(value = "/fix/update", method = {RequestMethod.POST})
    public void fix_Extension_save(@RequestParam Map<String, Object> param){



    }

    @RequestMapping(value = "/custom/save", method = {RequestMethod.POST})
    public String custom_Extension_save(Model model, @RequestParam Map<String, Object> param){
        fileExtensionService.save_Custom_Extension(param.get("name").toString());
        HashMap<ExtensionType, List<ResponseDto>> map = fileExtensionService.limited_Extensions();
        model.addAttribute("customList",map.get(ExtensionType.CUSTOM));
        return "index :: #custom-list";
    }
}
