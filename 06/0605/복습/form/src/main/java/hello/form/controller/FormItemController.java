package hello.form.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@GetMapping("/basic")
public class FormItemController {

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "form/addForm";
    }
}
