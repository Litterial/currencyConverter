package com.example.demo.Controller;

import com.example.demo.Model.CurrencyModel;
import com.example.demo.Service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
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
        model.addAttribute("title","HomePage");
        model.addAttribute("currency_model",new CurrencyModel());
        model.addAttribute("currency",indexService.allCurrency());
        return "index";
    }
    @RequestMapping(value = "",method = RequestMethod.POST)
    public String convertCurrency(@Valid @ModelAttribute("currency_model") @RequestBody CurrencyModel currencyform, Errors err, Model model)
    {

        String conversion_rate="";
        String conversion_amount="";
        String inverse_conversion_rate="";

        model.addAttribute("title","Errors");
        model.addAttribute("currency_model",currencyform);
        model.addAttribute("currency",indexService.allCurrency());
        if (err.hasErrors())
        {
            System.out.println("Errors");
 ;
            return "index";
        }
        ArrayList<HashMap> dataMap =indexService.conversionRequest(currencyform);

        for (int i=0; i<2; i++)
        {
            if ((dataMap.get(i).get("success").toString()).equals("false")) {
                model.addAttribute("errorMessage", dataMap.get(i).get("conversion_rate"));
                return "index";
            }
        }

        conversion_rate=dataMap.get(0).get("conversion_rate").toString();
        conversion_amount=dataMap.get(1).get("conversion_amount").toString();
        inverse_conversion_rate=dataMap.get(1).get("conversion_rate").toString();


        model.addAttribute("title","result");
        model.addAttribute("conversion_rate",conversion_rate);
        model.addAttribute("conversion_amount",conversion_amount);
        model.addAttribute("inverse_conversion_rate",inverse_conversion_rate);
        return "conversion";
    }
}
