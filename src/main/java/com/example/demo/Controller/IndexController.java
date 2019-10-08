package com.example.demo.Controller;

import com.example.demo.Service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    @Autowired
    //injects service file
    private IndexService indexService;
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String index (Model model){
//        indexService.testHTTP(" https://api.exchangeratesapi.io/latest?base=EUR");

        //calls function get get all the currencies
        model.addAttribute("currency",indexService.allCurrency());
        return "index";
    }
    @RequestMapping(value = "",method = RequestMethod.POST)
    public String convertCurrency(Model model)
    {
        return "conversion";
    }
}
