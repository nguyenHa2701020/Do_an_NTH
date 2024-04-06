package com.doan.elearning.controller;

import java.security.Principal;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.doan.elearning.entity.Result;
import com.doan.elearning.service.ResultService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;

    @GetMapping("/findResultByExam/{id}")
    public String findResultByExam(@PathVariable("id") Long id, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Manage questions");
        List<Result> lstResult = resultService.findResultByExam(id);

        model.addAttribute("result", lstResult);
        model.addAttribute("size", lstResult.size());
        return "Admin/result";
    }

    @GetMapping("/result")
    public String re(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Manage questions");
        List<Result> lstResult = resultService.findAll();

        model.addAttribute("result", lstResult);
        model.addAttribute("size", lstResult.size());
        return "Admin/result";
    }

    @RequestMapping(value = "/findResultId", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseBody
    public Result findExamId(Long id) {
        return resultService.findResult(id);
    }

    @GetMapping("/update-result/{id}")
    public String updateResult(@PathVariable("id") Long id, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        Result result = resultService.findResult(id);

        model.addAttribute("title", "Add Product");
        model.addAttribute("id", result.getId());
        model.addAttribute("speak", result.getSpeakPoint());
        model.addAttribute("write", result.getWritePoint());
        return "Admin/updateResult";
    }

    @PostMapping("/update-result")
    public String updateProduct(@RequestParam("id") Long id,
                                @RequestParam("speak") Float speakPoint, @RequestParam("write") Float writePoint,
                                RedirectAttributes redirectAttributes, Principal principal, HttpServletRequest request) {

        if (principal == null) {
            return "redirect:/login";
        }
        Result result = resultService.findResult(id);
        result.setSpeakPoint(speakPoint);
        result.setWritePoint(writePoint);
        resultService.update(result);
        redirectAttributes.addFlashAttribute("success", "Update successfully!");


        return "redirect:/findResultByExam/" + result.getExam().getId();
    }
}
