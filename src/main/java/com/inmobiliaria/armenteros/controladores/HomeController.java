
package com.inmobiliaria.armenteros.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Tonna/SA
 */

@Controller
public class HomeController {
    
    @GetMapping(value = {"/home"})
    public String showHomePage(){
        return "index "; // jsp page name
    }

}
