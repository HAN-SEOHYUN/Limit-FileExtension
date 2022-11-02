package com.limitfileextension.controller;

import com.limitfileextension.domain.ExtensionType;
import com.limitfileextension.dto.ResponseDto;
import com.limitfileextension.service.FileExtensionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        String name = param.get("name").toString();

        if(!fileExtensionService.exist_check_inFixList(name) || !fileExtensionService.check_total_custom()){
            return "redirect:/custom/badRequest";
        }
        fileExtensionService.save_Custom_Extension(name);
        HashMap<ExtensionType, List<ResponseDto>> map = fileExtensionService.limited_Extensions();
        model.addAttribute("customList",map.get(ExtensionType.CUSTOM));
        model.addAttribute("total", map.get(ExtensionType.CUSTOM).size());
        return "index :: #custom-list";
    }

    //response HttpStatus.BAD_REQUEST API
    @GetMapping(value="/custom/badRequest")
    public ResponseEntity badRequestMessage(@RequestParam Map<String,String> map){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //커스텀확장자 DELETE API
    @GetMapping(value ="/custom/delete/{id}")
    public String custom_Extension_delete(@PathVariable ("id") Long id){
        fileExtensionService.delete_custom_extension(id);
        return "redirect:/";
    }
}
