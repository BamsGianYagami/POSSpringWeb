package com.github.BamsGianYagami.POSSpringWeb.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;
import org.springframework.context.annotation.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.github.BamsGianYagami.POSSpringWeb.Entity.Stock;
import com.github.BamsGianYagami.POSSpringWeb.dto.ItemCheckoutDTO;
import com.github.BamsGianYagami.POSSpringWeb.dto.LoginDTO;
import com.github.BamsGianYagami.POSSpringWeb.services.JwtService;
import com.github.BamsGianYagami.POSSpringWeb.services.StockService;
import com.github.BamsGianYagami.POSSpringWeb.services.UserInfoService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationManager; 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; 
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException; 

@Controller
// @RestController
@RequestMapping(path="main")
public class MainController {

    @Autowired
    JwtService jwtService;

    @Autowired
    StockService stockService;
  
    @Autowired
    private AuthenticationManager authenticationManager; 

    private static final Logger log = LoggerFactory.getLogger(MainController.class);


    @PostMapping("login")
    public RedirectView login(@ModelAttribute("login") LoginDTO login, HttpServletRequest request , RedirectAttributes redirectAttributes, HttpServletResponse response) throws UsernameNotFoundException{
        log.info("login as {}", login.getUsername());
        RedirectView redirectView;

        String token = "";

        try{
            log.info("start authenticating...");
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword())); 
            log.info("authentication.isAuthenticated() {}", authentication.isAuthenticated());
        if (authentication.isAuthenticated()) { 
            token = "Bearer "+jwtService.generateToken(login.getUsername());
        } else { 
            throw new UsernameNotFoundException("invalid user request !");
        }
        }catch(AuthenticationException e){
            log.error(e.getMessage());
        }

        log.info("token.isEmpty(): {} length: {}",token.isEmpty(), token.length());
        if(token.isEmpty()){
            log.info("failed to login");
            redirectView = new RedirectView("/", true);
            redirectAttributes.addFlashAttribute("loginFailed", true);
            return redirectView;
        }

        HttpSession session = request.getSession();
        redirectView = new RedirectView("/main/dashboard", true);
        // redirectAttributes.addAttribute("username", login.getUsername());
        session.setAttribute("Authorization", token);
        log.info("ends here {}", redirectView.getUrl());
        return redirectView;
    }

    // @RolesAllowed("USER")
    @GetMapping("dashboard")
    public String menu(Model model, HttpServletRequest request, HttpServletResponse response){
        log.info("masuk ke dashboard!!!");
        // return new ModelAndView("dashboard");
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
        model.addAttribute("inputItem", new ItemCheckoutDTO());
        return "checkout";
    }

    @GetMapping("addStock")
    public String addStock(Model model){
        log.info("User {} masuk ke addStock", model.getAttribute("username"));
        model.addAttribute("inputItem", new ItemCheckoutDTO());
        return "add-stock";
    }
    
    @PostMapping("addStock")
    public RedirectView addStock(@ModelAttribute("stock") Stock stock,RedirectAttributes redirectAttributes){
        final RedirectView redirectView = new RedirectView("/main/stocks", true);
        Stock savedStock = stockService.addStock(stock);
        redirectAttributes.addFlashAttribute("savedBook", savedStock);
        redirectAttributes.addFlashAttribute("savedBookSuccess", true);
        return redirectView;
    }

    @GetMapping("stocks")
    public String viewBooks(Model model) {
        model.addAttribute("stocks", stockService.getAllStock());
        return "view-stock";
    }
}
