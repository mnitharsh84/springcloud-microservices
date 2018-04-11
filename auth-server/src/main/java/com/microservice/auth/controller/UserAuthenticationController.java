package com.microservice.auth.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserAuthenticationController {

    /**
     * Return the principal identifying the logged in user
     *
     * @param user
     * @return
     */
    @RequestMapping("/me")
    @ResponseBody
    public Principal getCurrentLoggedInUser(Principal user) {
        return user;
    }
}