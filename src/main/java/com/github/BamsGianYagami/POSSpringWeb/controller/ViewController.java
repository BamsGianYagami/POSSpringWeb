package com.github.BamsGianYagami.POSSpringWeb.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.BamsGianYagami.POSSpringWeb.Entity.Stock;
import com.github.BamsGianYagami.POSSpringWeb.Entity.UserInfo;
import com.github.BamsGianYagami.POSSpringWeb.dto.cartDTO;
import com.github.BamsGianYagami.POSSpringWeb.repository.ShoppingCartRepository;
import com.github.BamsGianYagami.POSSpringWeb.services.CheckoutService;
import com.github.BamsGianYagami.POSSpringWeb.services.StockService;
import com.github.BamsGianYagami.POSSpringWeb.services.UserInfoService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ViewController {

    @Autowired
    StockService stockService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    CheckoutService checkoutService;

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

    @GetMapping("editUser/{id}")
    public String editUser(Model model, @PathVariable Integer id){
        UserInfo user = userInfoService.getUser(id).get();
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("editUser/{id}")
    public RedirectView editUser(@ModelAttribute("user") UserInfo user, RedirectAttributes redirectAttributes){
        log.info("masuk editUser {}", user.getUsername());
        final RedirectView redirectView = new RedirectView("/users", true);
        user.setPassword(null);
        String message = userInfoService.updateUser(user);
        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("isEditing", true);
        return redirectView;
    }

    @GetMapping("deleteUser/{id}")
    public RedirectView deleteUser(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        final RedirectView redirectView = new RedirectView("/users", true);
        String message = userInfoService.deleteUser(id);
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
        log.info("masuk checkout");
        List<Stock> stocks = stockService.getAllStock();
        model.addAttribute("stocks", stocks);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        List<ShoppingCartRepository.ListCart> cart = checkoutService.getListCarts(currentPrincipalName);
        model.addAttribute("carts", cart);
        log.info("ada {} cart", cart.size());
        model.addAttribute("cart", new cartDTO());
        System.out.println("mau return checkout");
        return "checkout";
    }

    //enchance dengan post karena bisa langsung diisi quantity nya
    @PostMapping("addtoCart/{id}")
    public RedirectView addToCart(@PathVariable Integer id, @ModelAttribute("addCart") cartDTO itemCart, RedirectAttributes redirectAttributes ){
        itemCart.setItemId(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        log.info("User {} masuk ke addtoCart", currentPrincipalName);
        checkoutService.addToCart(currentPrincipalName, id, itemCart.getQty());
        final RedirectView redirectView = new RedirectView("/checkout", true);
        return redirectView;
    }

    @PostMapping("removeFromCart/{id}")
    public RedirectView removeFromCart(@PathVariable Integer id, @ModelAttribute("cart") cartDTO itemCart, RedirectAttributes redirectAttributes ){
        itemCart.setItemId(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        log.info("User {} masuk ke removeFromCart", currentPrincipalName);
        checkoutService.removeFromCart(currentPrincipalName, id, itemCart.getQty());
        final RedirectView redirectView = new RedirectView("/checkout", true);
        return redirectView;
    }
    //#endregion checkout
}
