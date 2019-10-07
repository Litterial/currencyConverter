package com.example.demo.Controller;

import com.example.demo.Service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class IndexController {
    @Autowired
    private IndexService indexService;
    @RequestMapping(value = "")
    public String index (Model model){
        indexService.testHTTP(" https://api.exchangeratesapi.io/latest?base=EUR");
        return "index";
    }
}
