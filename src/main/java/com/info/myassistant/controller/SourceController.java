package com.info.myassistant.controller;

import com.info.myassistant.dto.SourceDto;
import com.info.myassistant.serviceimpl.SourceServiceImpl;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author rawalokes
 * Date:3/24/22
 * Time:12:18 PM
 */
@Controller
@RequestMapping("/source")
public class SourceController {
    private final SourceServiceImpl sourceService;

    public SourceController(SourceServiceImpl sourceService) {
        this.sourceService = sourceService;
    }
    @GetMapping("/get-all")
    public String getAllSource(Model model){
        model.addAttribute("sourceList",sourceService.findAllSource());
        return "source/viewSource";
    }
    @GetMapping("/create")
    public String getCreateSource(Model model){
        model.addAttribute("source",new SourceDto());
        return "source/createSource";
    }

    @PostMapping("/create")
    public String postCreateSource(@Valid @ModelAttribute("source")SourceDto sourceDto,
                                  BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            return "source/createSource";
        }
        return "redirect:/source/get-all";

    }

}
