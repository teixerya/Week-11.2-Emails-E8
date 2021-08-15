package ca.sheridancollege.teixerya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.teixerya.bean.Drink;
import ca.sheridancollege.teixerya.repository.DrinkRepository;

public class DrinkController {

    @Autowired
    private DrinkRepository  drinkRepo;

//    @GetMapping("/")
//    public String goRoot() {
//        return "rootpage.html";
//    }

    @GetMapping("/addDrink")
    public String goToAddDrink(Model model) {
        model.addAttribute("drink", new Drink());
        return "addDrink.html";
    }

    @PostMapping("/addDrink")
    public String addDrinkToDatabase(@ModelAttribute Drink drink, Model model) {
        // Connect to my database and store the drink
        // return back to my add drink page to be able to add another drink


        drinkRepo.addDrink(drink);

//		model.addAttribute("drink", new Drink());
//		return "addDrink.html";

        return "redirect:/addDrink";
    }

    @GetMapping("/viewDrinks")
    public String viewDrinks(Model model) {

        model.addAttribute("drinks",drinkRepo.getDrinks());
        return "viewDrinks.html";
    }

    @GetMapping("/edit/{id}")
    public String editDrink(@PathVariable int id, Model model) {
        Drink drink = drinkRepo.getDrinkById(id);
        model.addAttribute("drink", drink);
        return "editDrink.html";
    }

    @PostMapping("/editDrink")
    public String modifyDrink(@ModelAttribute Drink drink) {
        drinkRepo.editDrink(drink);
        return "redirect:/viewDrinks";
    }

    @GetMapping("/delete/{id}")
    public String deleteDrink(@PathVariable int id, Model model) {
        drinkRepo.deleteById(id);
        return "redirect:/viewDrinks";
    }

}

