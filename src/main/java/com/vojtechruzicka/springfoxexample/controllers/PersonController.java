package com.vojtechruzicka.springfoxexample.controllers;

import com.vojtechruzicka.springfoxexample.domain.Person;
import com.vojtechruzicka.springfoxexample.services.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v2/persons/")
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Persons.")
public class PersonController {

    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ApiOperation("${personcontroller.getallpersons}")
    public List<Person> getAllPersons(@RequestHeader("x-api-key") String apiKey, @RequestHeader(value = "Cache-Control", defaultValue="no-cache") String cacheControl) {
        return personService.getAllPersons();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = "application/json")
    @ApiOperation("${personcontroller.getpersonbyid}")
    public Person getPersonById(@ApiParam(value = "Id of the person to be obtained. Cannot be empty.", required = true)
                                    @PathVariable int id,
                                    @RequestHeader("x-api-key") String apiKey, @RequestHeader(value = "Cache-Control", defaultValue="no-cache") String cacheControl) {
        return personService.getPersonById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @ApiOperation("${personcontroller.deleteperson}")
    public void deletePerson(@ApiParam(value = "Id of the person to be deleted. Cannot be empty.", required = true)
                                 @PathVariable int id,
                                 @RequestHeader("x-api-key") String apiKey, @RequestHeader(value = "Cache-Control", defaultValue="no-cache") String cacheControl) {
        personService.deletePerson(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ApiOperation("${personcontroller.createperson}")
    public Person createPerson(@ApiParam("Person information for a new person to be created.")
                                   @RequestBody Person person,
                                   @RequestHeader("x-api-key") String apiKey, @RequestHeader(value = "Cache-Control", defaultValue="no-cache") String cacheControl) {
        return personService.createPerson(person);
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}
