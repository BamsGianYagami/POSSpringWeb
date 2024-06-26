package com.github.BamsGianYagami.POSSpringWeb.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.github.BamsGianYagami.POSSpringWeb.Entity.Stock;
import com.github.BamsGianYagami.POSSpringWeb.Entity.UserInfo;
import com.github.BamsGianYagami.POSSpringWeb.dto.ItemCheckoutDTO;
import com.github.BamsGianYagami.POSSpringWeb.services.StockService;
import com.github.BamsGianYagami.POSSpringWeb.services.UserInfoService;

@Controller
public class ViewController {

    @Autowired
    StockService stockService;

    @Autowired
    UserInfoService userInfoService;

    private static Logger log = LoggerFactory.getLogger(ViewController.class);
    
    @GetMapping("/")
    public String login(Model model, String error, String logout){
        model.addAttribute("loginFailed", error!=null);
        model.addAttribute("logout", logout!=null);
        return "index";
    }

    @GetMapping("dashboard")
    public String dashboard(){
        return "dashboard";
    }


    //#region user
    @GetMapping("users")
    public String user(Model model){
        model.addAttribute("users", userInfoService.getAllUser());
        return "view-user";
    }

    @GetMapping("addUser")
    public String addUser(Model model){
        model.addAttribute("user", new UserInfo());
        return "add-user";
    }

    @PostMapping("addUser")
    public RedirectView addUser(@ModelAttribute UserInfo user, RedirectAttributes redirectAttributes){
        final RedirectView redirectView = new RedirectView("/users", true);
        String message = userInfoService.addUser(user);
        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("isSaving", true);
        return redirectView;
    }

    @GetMapping("editUser/{username}")
    public String editUser(Model model, @PathVariable String username){
        UserInfo user = userInfoService.getUser(username).get(0);
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("editUser/{username}")
    public RedirectView editUser(@PathVariable String username, @ModelAttribute("user") UserInfo user, RedirectAttributes redirectAttributes){
        log.info("masuk editUser {}", username);
        final RedirectView redirectView = new RedirectView("/users", true);
        user.setPassword(null);
        String message = userInfoService.updateUser(user, username);
        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("isEditing", true);
        return redirectView;
    }

    @GetMapping("deleteUser/{username}")
    public RedirectView deleteUser(@PathVariable String username, RedirectAttributes redirectAttributes){
        final RedirectView redirectView = new RedirectView("/users", true);
        String message = userInfoService.deleteUser(username);
        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("isEditing", true);
        return redirectView;
    }
    //#endregion user


    //#region Stock
    @GetMapping("addStock")
    public String addStock(Model model){
        model.addAttribute("stock", new Stock());
        return "add-stock";
    }
    
    @PostMapping("addStock")
    public RedirectView addStock(@ModelAttribute("stock") Stock stock,RedirectAttributes redirectAttributes){
        final RedirectView redirectView = new RedirectView("/stocks", true);
        Stock savedStock = stockService.addStock(stock);
        redirectAttributes.addFlashAttribute("savedStock", savedStock);
        redirectAttributes.addFlashAttribute("isSaving", true);
        return redirectView;
    }

    @GetMapping("editStock/{id}")
    public String editStock(Model model, @PathVariable Integer id){
        Stock savedStock = stockService.getStock(id);
        model.addAttribute("stock", savedStock);
        return "edit-stock";
    }

    @PostMapping("editStock/{id}")
    public RedirectView editStock(@ModelAttribute("stock") Stock stock,RedirectAttributes redirectAttributes){
        final RedirectView redirectView = new RedirectView("/stocks", true);
        Stock savedStock = stockService.updateStock(stock);
        redirectAttributes.addFlashAttribute("savedStock", savedStock);
        redirectAttributes.addFlashAttribute("isEditing", true);
        return redirectView;
    }

    @GetMapping("deleteStock/{id}")
    public RedirectView deleteStock(Model model, @PathVariable Integer id, RedirectAttributes redirectAttributes){
        final RedirectView redirectView = new RedirectView("/stocks", true);
        String message = stockService.deleteStock(id);
        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("isDeleting", true);
        return redirectView;
    }

    @GetMapping("stocks")
    public String viewStock(Model model) {
        log.info("stock count: {} ", stockService.getAllStock().size());
        model.addAttribute("stocks", stockService.getAllStock());
        return "view-stock";
    }
    //#endregion stock


    //#region checkout
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
    //#endregion checkout
}
