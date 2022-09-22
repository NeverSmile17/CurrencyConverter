package com.springboot.example.currencyconverter.controllers;

import com.springboot.example.currencyconverter.model.CurrencySingleton;
import com.springboot.example.currencyconverter.model.InputData;
import com.springboot.example.currencyconverter.services.CurrencyService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/currency-converter")
public class CurrencyConverterController {

    private final CurrencyService currencyService;

    public CurrencyConverterController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(value = "/")
    public String welcomePage() {
        return "/welcome-page";
    }

    @GetMapping(value = "/select-currencies")
    public String selectCurrencies(Model model) {
        Set<String> currencies = CurrencySingleton.getInstance().getCurrencies().keySet();
        model.addAttribute("currencies", currencies);
        model.addAttribute("inputData", new InputData());
        model.addAttribute("date", CurrencySingleton.getInstance().getDate());

        return "/select-currencies";
    }

    @PostMapping(value = "/converted-currency")
    public String convertedCurrency(@ModelAttribute("inputData") @Valid InputData inputData,
                                    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/currency-converter/select-currencies";
        } else {
            model.addAttribute("cash", currencyService.converted(inputData.getCurrencyFrom(), inputData.getCurrencyTo(), inputData.getCount()));
            model.addAttribute("currency", inputData.getCurrencyTo());
        }
        return "/result";
    }
}