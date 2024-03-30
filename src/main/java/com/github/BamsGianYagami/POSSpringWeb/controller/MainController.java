package com.github.BamsGianYagami.POSSpringWeb.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.github.BamsGianYagami.POSSpringWeb.dto.AddItemCheckoutDTO;
import com.github.BamsGianYagami.POSSpringWeb.dto.LoginDTO;

@Controller
// @RestController
@RequestMapping(path="main")
public class MainController {

    private static final Logger log = LoggerFactory.getLogger("mainController");
    

    @PostMapping("login")
    public RedirectView login(@ModelAttribute("login") LoginDTO login,  RedirectAttributes redirectAttributes){
        log.info("login as {}", login.getUsername());
        RedirectView redirectView;
        if(login.getUsername().isEmpty()){
            redirectView = new RedirectView("/", true);
            redirectAttributes.addFlashAttribute("loginFailed", true);
            return redirectView;
        }
        redirectView = new RedirectView("/main/dashboard", true);
        redirectAttributes.addFlashAttribute("username", login.getUsername());
        return redirectView;
    }

    @GetMapping("dashboard")
    public String menu(Model model){
        log.info("masuk ke menu sebagai {}", model.getAttribute("username"));
        return "dashboard";
    }

    @GetMapping("checkout")
    public String checkout(Model model){
        log.info("User {} masuk ke checkout", model.getAttribute("username"));
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("itemId", 1);
        map.put("itemName", "Pasir");
        map.put("itemPrice", 100000);
        map.put("qty", 20);
        Collection<Map<String, Object>> collectMap = new LinkedList<Map<String, Object>>();
        collectMap.add(map);
        map = new HashMap<String,Object>();
        map.put("itemId", 2);
        map.put("itemName", "Semen");
        map.put("itemPrice", 15000);
        map.put("qty", 20);
        collectMap.add(map);
        model.addAttribute("checkout", collectMap);
        model.addAttribute("inputItem", new AddItemCheckoutDTO());
        return "checkout";
    }
}
