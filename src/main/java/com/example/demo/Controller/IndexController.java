package com.example.demo.Controller;

import com.example.demo.Model.CurrencyModel;
import com.example.demo.Service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.HashMap;

@Controller
public class IndexController {
    @Autowired
    //injects service file
    private IndexService indexService;
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String index (Model model){
//        indexService.testHTTP(" https://api.exchangeratesapi.io/latest?base=EUR");

        //calls function get get all the currencies
        model.addAttribute("currency_model",new CurrencyModel());
        model.addAttribute("currency",indexService.allCurrency());
        return "index";
    }
    @RequestMapping(value = "",method = RequestMethod.POST)
    public String convertCurrency(@Valid @ModelAttribute("currency_model") CurrencyModel currencyform, Errors err, Model model)
    {

        String conversion_rate="";
        String conversion_amount="";
        if (err.hasErrors())
        {
            model.addAttribute("currency_model",currencyform);
            System.out.println("Errors");
            model.addAttribute("currency",indexService.allCurrency());
            return "index";
        }
        HashMap<String,String> dataMap =indexService.conversionRequest(currencyform);

        if (dataMap.get("success").equals("false")) {
            model.addAttribute("errorMessage", dataMap.get("conversion_rate"));
            return "index";
        }
        conversion_rate=dataMap.get("conversion_rate");
        conversion_amount=dataMap.get("conversion_amount");

        model.addAttribute("conversion_rate",conversion_rate);
        model.addAttribute("conversion_amount",conversion_amount);
        return "conversion";
    }
}
