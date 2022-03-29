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

    /**
     * @param model
     * @return view all sources  page
     */
    @GetMapping("/get-all")
    public String getAllSource(Model model) {
        //return list of source current user have
        model.addAttribute("sourceList", sourceService.findAllSource());
        return "source/viewSource";
    }

    /**
     * @param model
     * @return create source page
     */
    @GetMapping("/create")
    public String getCreateSource(Model model) {
        model.addAttribute("SourceDetails", new SourceDto());
        return "source/createSource";
    }

    /**
     * @param sourceDto
     * @param bindingResult to validate input
     * @return
     */
    @PostMapping("/create")
    public String postCreateSource(@Valid @ModelAttribute("SourceDetails") SourceDto sourceDto, BindingResult bindingResult, Model model) {
        //check if there is any binding error if present return create source page with error
        if (bindingResult.hasErrors()) {
            return "source/createSource";
        }
        //call service to create  source
        ResponseDto responseDto = sourceService.create(sourceDto);
        //check responseDto status is true and redirect to view all sources
        if (responseDto.isStatus()) {
            return "redirect:/source/get-all";

        }
        //if responseDto status is false return create source form with error message
        model.addAttribute("errorMessage", responseDto.getMessage());
        return "source/createSource";


    }

    /**
     * @param id    source id
     * @param model
     * @return view all source's page
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        //call service to find source by id
        ResponseDto responseDto = sourceService.findByID(id);
        //check responseDto status is true and redirect to view all sources
        if (responseDto.isStatus()) {
            model.addAttribute("SourceDetails", responseDto.getData());
            return "source/createSource";

            //if responseDto status is false return create source form with error message
        } else {
            model.addAttribute("errorMessage", responseDto.getMessage());
            return "source/viewSource";
        }
    }

    /**
     * @param id    source id
     * @param model
     * @return view all source page
     */
    @GetMapping("/remove/{id}")
    public String removeSource(@PathVariable("id") Integer id, Model model) {
        //call service to remove source by id
        ResponseDto responseDto = sourceService.removeSource(id);
        //check responseDto status is true and redirect to view all sources with success param
        if (responseDto.isStatus()) {
            model.addAttribute("SourceDetails", responseDto.getData());
            return "redirect:/source/get-all?success";
            //if responseDto status is false return create source form with error message
        } else {
            model.addAttribute("errorMessage", responseDto.getMessage());
            return "source/viewSource";
        }
    }
}
