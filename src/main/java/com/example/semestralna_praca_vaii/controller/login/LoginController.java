package com.example.semestralna_praca_vaii.controller.login;


import com.example.semestralna_praca_vaii.facade.dto.PersonDto;
import com.example.semestralna_praca_vaii.facade.person.PersonFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/login")
public class LoginController {

    private PersonFacade personFacade;

    @Autowired
    public LoginController(PersonFacade personFacade) {
        this.personFacade = personFacade;
    }

    @PostMapping
    public ResponseEntity<PersonDto> login(Principal principal){

        PersonDto personDto = this.personFacade.getPersonByEmail(principal.getName());

        return new ResponseEntity<PersonDto>(personDto,HttpStatus.OK);
    }
}
