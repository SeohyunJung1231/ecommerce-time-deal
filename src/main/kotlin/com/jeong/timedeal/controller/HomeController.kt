package com.jeong.timedeal.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HomeController {
    @RequestMapping("/swagger")
    fun swagger(): String {
        return "redirect:swagger-ui.html"
    }
}