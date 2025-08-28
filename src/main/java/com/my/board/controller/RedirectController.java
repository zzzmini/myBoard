package com.my.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("mapping")
public class RedirectController {
    @GetMapping({"","/"})
    public String testMain() {
        return "/test/testMain";
    }

    @GetMapping("page")
    public String pageView(Model model) {
        model.addAttribute("msg", "Model로 보낸 값");
        return "/test/page";
    }

    @RequestMapping(value = "requestMapping",
        method = RequestMethod.GET)
    // Spring 5.0 아래 버전에서 사용
    // Spring 5.0 이상은  @GetMapping 사용 가능
    public String requestMapping(Model model) {
        model.addAttribute("msg", "RequestMapping");
        return "/test/page";
    }

    @GetMapping("modelAndView")
    public ModelAndView modelAndView(Model model) {
        String msg = "ModelAndView";
        model.addAttribute("msg", msg);
        return new ModelAndView("/test/page");
    }

    @GetMapping("redirectView")
    public String redirectView(RedirectAttributes redirectAttributes) {
        String msg = "RedirectView";
        redirectAttributes.addFlashAttribute("state", msg);
        return "redirect:page";
    }

    @GetMapping("delete")
    public RedirectView delete(RedirectAttributes redirectAttributes) {
        String msg = "RedirectView";
        redirectAttributes.addFlashAttribute("state", msg);
        return new RedirectView("/mapping");
    }
    @GetMapping("naver")
    public String naver() {
        return "redirect:https://naver.com";
    }

    @GetMapping("local")
    public String local() {
        return "redirect:http://localhost:3000/main";
    }
}
