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

    //고정확장자 업데이트 API
    @RequestMapping(value = "/fix/update", method = {RequestMethod.POST})
    public String fix_Extension_save(@RequestParam Map<String, Object> param,Model model){
        boolean isSelected = (param.get("isChecked").toString().equals("true")) ;
        fileExtensionService.update_Fix_Extension((long) Integer.parseInt(param.get("id").toString()), isSelected);
        HashMap<ExtensionType, List<ResponseDto>> map = fileExtensionService.limited_Extensions();
        model.addAttribute("fixList",map.get(ExtensionType.FIX));
        return "index :: #fix-list";
    }

    //커스텀확장자 SAVE API
    @RequestMapping(value = "/custom/save", method = {RequestMethod.POST})
    public String custom_Extension_save(Model model, @RequestParam Map<String, Object> param){
        fileExtensionService.save_Custom_Extension(param.get("name").toString());
        HashMap<ExtensionType, List<ResponseDto>> map = fileExtensionService.limited_Extensions();
        model.addAttribute("customList",map.get(ExtensionType.CUSTOM));
        return "index :: #custom-list";
    }
}