package com.budiluhur.alreza.POSSpringWeb.controller;

import java.util.List;

import org.hibernate.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.budiluhur.alreza.POSSpringWeb.Entity.Stock;
import com.budiluhur.alreza.POSSpringWeb.Entity.UserInfo;
import com.budiluhur.alreza.POSSpringWeb.dto.cartDTO;
import com.budiluhur.alreza.POSSpringWeb.repository.ShoppingCartRepository;
import com.budiluhur.alreza.POSSpringWeb.services.CheckoutService;
import com.budiluhur.alreza.POSSpringWeb.services.StockService;
import com.budiluhur.alreza.POSSpringWeb.services.TransactionService;
import com.budiluhur.alreza.POSSpringWeb.services.UserInfoService;


@Controller
public class ViewController {

    @Autowired
    StockService stockService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    CheckoutService checkoutService;

    @Autowired
    TransactionService transactionService;

    private static Logger log = LoggerFactory.getLogger(ViewController.class);
    
    @GetMapping("/")
    public String login(Model model, String error, String logout){
        model.addAttribute("loginFailed", error!=null);
        model.addAttribute("logout", logout!=null);
        return "index";
    }

    @GetMapping("home")
    public String home(){
        return "home";
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
        List<Stock> stocks = stockService.getAllStock();
        model.addAttribute("stocks", stocks);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<ShoppingCartRepository.ListCart> carts = checkoutService.getListCarts(username);
        model.addAttribute("carts", carts);
        Integer grandTotal = checkoutService.calculateGrandTotal(carts);
        model.addAttribute("cart", new cartDTO());
        model.addAttribute("grandTotal", grandTotal);
        return "checkout";
    }

    //enchance dengan post karena bisa langsung diisi quantity nya
    @PostMapping("addtoCart/{id}")
    public RedirectView addToCart(@PathVariable Integer id, @ModelAttribute("addCart") cartDTO itemCart, RedirectAttributes redirectAttributes ){
        itemCart.setItemId(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        checkoutService.addToCart(username, id, itemCart.getQty());
        final RedirectView redirectView = new RedirectView("/checkout", true);
        return redirectView;
    }

    @PostMapping("removeFromCart/{id}")
    public RedirectView removeFromCart(@PathVariable Integer id, @ModelAttribute("cart") cartDTO itemCart, RedirectAttributes redirectAttributes ){
        itemCart.setItemId(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        checkoutService.removeFromCart(username, id, itemCart.getQty());
        final RedirectView redirectView = new RedirectView("/checkout", true);
        return redirectView;
    }

    @GetMapping("confirmCheckout")
    public String confirmCheckout(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        var listCheckout = checkoutService.getListCarts(username);
        Boolean isSaved = transactionService.SaveTransactionFromCart(listCheckout, username);
        model.addAttribute("checkout", listCheckout);
        if(isSaved){
            Integer grandTotal = checkoutService.calculateGrandTotal(listCheckout);
            model.addAttribute("grandTotal", grandTotal);

            try{
                checkoutService.removeAllFromCart(username);
            }catch(TransactionException e){
                log.error("can not delete shopping char in user id {} : {}", username, e.getMessage());
            }
            stockService.updateStockAfterCommitTransaction(listCheckout);
        }
        return "confirm-checkout";
    }
    //#endregion checkout

    //#region transaction history
    @GetMapping("transaction")
    public String transaction(Model model){
        var summaries = transactionService.getTransactionSummary();
        model.addAttribute("summaries", summaries);
        return "transaction-summary";
    }

    @GetMapping("transaction/{id}")
    public String transaction(@PathVariable Integer id, Model model){
        var transactionDetail = transactionService.getTransactionDetail(id);
        model.addAttribute("transactionDetail", transactionDetail);
        var grandTotal = transactionService.calculateGrandTotal(transactionDetail);
        model.addAttribute("grandTotal", grandTotal);
        return "transaction-detail";
    }
    //#endregion transaction history
}
