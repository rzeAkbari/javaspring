package com.example.demospringaction.taco;

import com.example.demospringaction.ingredients.IngredientRepository;
import com.example.demospringaction.ingredients.Ingredients;
import com.example.demospringaction.order.Order;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
//The class-level @SessionAttributes annotation specifies any model objects like the order attribute that should be kept in session and available across multiple requests
@SessionAttributes("order")
public class DesignTacoController {
    private static final Logger log = LoggerFactory.getLogger(DesignTacoController.class);
    private IngredientRepository ingredientRepository;
    private TacoRepository tacoRepository;
    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesign(Model model){
        List<Ingredients> ingredients = List.of();
        ingredientRepository.findAll().forEach(ingredients::add);
        Ingredients.Type[] types = Ingredients.Type.values();
        for(var type: types){
            model.addAttribute(type.toString().toLowerCase(),filterByType(type, ingredients));
        }
        return "design";
    }
//The Order parameter is annotated with @ModelAttribute to indicate that its value should come from the model and that Spring MVC shouldn’t attempt to bind request parameters to it
    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order){
        if (errors.hasErrors()) {
            return "design";
        }
        Taco savedTaco =  tacoRepository.save(taco);
        order.addDesign(savedTaco);

        return "redirect:/orders/current";
    }

    private List<Ingredients> filterByType(Ingredients.Type type, List<Ingredients> ingredients){
        return ingredients.stream()
                .filter( ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }

}
