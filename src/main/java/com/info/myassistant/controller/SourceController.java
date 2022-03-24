package com.info.myassistant.controller;

import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.dto.SourceDto;
import com.info.myassistant.serviceimpl.SourceServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String getAllSource(Model model) {
        model.addAttribute("sourceList", sourceService.findAllSource());
        return "source/viewSource";
    }

    @GetMapping("/create")
    public String getCreateSource(Model model) {
        model.addAttribute("SourceDetails", new SourceDto());
        return "source/createSource";
    }

    @PostMapping("/create")
    public String postCreateSource(@Valid @ModelAttribute("SourceDetails") SourceDto sourceDto,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "source/createSource";
        }
        ResponseDto responseDto = sourceService.create(sourceDto);
        if (responseDto.isStatus()) {
            return "redirect:/source/get-all";
        } else {
            model.addAttribute("errorMessage", responseDto.getMessage());
            return "source/createSource";
        }


    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        ResponseDto responseDto = sourceService.findByID(id);
        if (responseDto.isStatus()) {
            model.addAttribute("SourceDetails", responseDto.getData());
            return "source/createSource";
        } else {
            model.addAttribute("errorMessage", responseDto.getMessage());
            return "source/viewSource";
        }
    }
}
