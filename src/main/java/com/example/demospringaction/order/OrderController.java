package com.example.demospringaction.order;

import com.example.demospringaction.order.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/current")
    public String orderForm(Model model) {
        return "orderform";
    }
    @PostMapping
    public String processOrder(Order order) {
        return "redirect:/";
    }
}
