package id1212.currency_converter.presentation;

import id1212.currency_converter.application.ExchangeService;
import id1212.currency_converter.domain.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
@Scope("session")
public class HomeController {
    @Autowired
    private ExchangeService service;
    private boolean instantiated = false;

    @GetMapping("/")
    public String home(Model model) {
        if (!instantiated) {
            instantiated = true;
            service.createDefaultRates();
        }
        List<Rate> allRates = service.getAllRates();
        model.addAttribute("rates", allRates);
        model.addAttribute("convertForm", new ConvertForm());
        return "home";
    }

    @PostMapping("/")
    public String convert(@Valid ConvertForm convertForm, BindingResult bindingResult, Model model) {
        List<Rate> allRates = service.getAllRates();
        if (!instantiated) {
            instantiated = true;
            service.createDefaultRates();
        }
        model.addAttribute("rates", allRates);
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "The entered amount must be a number!");
            System.out.println("Error binding result: " + bindingResult);
        }
        double exchange = service.exchange(
                convertForm.getExchangeFrom().toUpperCase(),
                convertForm.getExchangeTo().toUpperCase(),
                convertForm.getExchangeAmount());
        if (exchange == -1.0) {
            model.addAttribute("error", "Sorry, we don't have that currency!");
        } else {
            model.addAttribute("result", exchange);
        }
        return "home";
    }


    @GetMapping("/admin")
    public String admin(Model model) {
        if (!instantiated) {
            instantiated = true;
            service.createDefaultRates();
        }
        model.addAttribute("transCount", service.getTransactionCount());
        List<Rate> allRates = service.getAllRates();
        model.addAttribute("rates", allRates);
        model.addAttribute("convertForm", new ConvertForm());
        return "admin";
    }


    @PostMapping("/admin")
    public String add(@Valid ConvertForm convertForm, BindingResult bindingResult, Model model) {
        model.addAttribute("transCount", service.getTransactionCount());
        if (!instantiated) {
            instantiated = true;
            service.createDefaultRates();
        }
        if (bindingResult.hasErrors()) {
            System.out.println("Error binding result: " + bindingResult);
        }
        boolean res = service.createRate(
                convertForm.getExchangeFrom().toUpperCase(),
                convertForm.getExchangeTo().toUpperCase(),
                convertForm.getExchangeAmount());
        List<Rate> allRates = service.getAllRates();
        model.addAttribute("rates", allRates);
        if (res) {
            model.addAttribute("result", "Success!");
        } else {
            model.addAttribute("error", "Values have already been added!");
        }
        return "admin";
    }



}
