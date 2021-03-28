package com.example.demospringaction.ingredients;

import com.example.demospringaction.taco.Taco;
import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
public class IngredientsController {
    private static final Logger log = LoggerFactory.getLogger(IngredientsController.class);

    @GetMapping
    public String showDesign(Model model){
        List<Ingredients> ingredients = List.of(
                new Ingredients(1L, "Flour", Ingredients.Type.WRAP),
                new Ingredients(2L, "Mozzarella", Ingredients.Type.WRAP),
                new Ingredients(3L, "Meet", Ingredients.Type.PROTEIN),
                new Ingredients(4L, "Barbeque", Ingredients.Type.SAUCE)
        );
        Ingredients.Type[] types = Ingredients.Type.values();
        for(var type: types){
            model.addAttribute(type.toString().toLowerCase(),filterByType(type, ingredients));
        }
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors){
        if (errors.hasErrors()) {
            return "design";
        }
        log.info(""+taco);
        return "redirect:/orders/current";
    }

    private List<Ingredients> filterByType(Ingredients.Type type, List<Ingredients> ingredients){
        return ingredients.stream()
                .filter( ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }

}
