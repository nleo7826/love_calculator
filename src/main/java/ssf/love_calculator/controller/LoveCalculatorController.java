package ssf.love_calculator.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ssf.love_calculator.model.Compatability;
import ssf.love_calculator.service.LoveService;

@Controller
@RequestMapping(path="/calculate")
public class LoveCalculatorController {

    @Autowired
    private LoveService lsvc;

    @GetMapping
    public String getCompatability(@RequestParam(required = true) String fname,
        @RequestParam(required = true) String sname, Model model) throws IOException, InterruptedException {
            Optional<Compatability> c = lsvc.getCompatability(sname, fname);
            model.addAttribute("compatability", c.get());
            return "compatability";
    }

    @GetMapping(path = "/list")
    public String showForm(Model model) {
        model.addAttribute("compatability", new Compatability());
        return "results";
    }
}
