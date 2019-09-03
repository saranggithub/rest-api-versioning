package com.tomtom.versioning.controller;

import com.tomtom.versioning.beans.User;
import com.tomtom.versioning.beans.UserV1;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/app")
@Log4j2
@CacheConfig(cacheNames = {"cuser"})
@CrossOrigin(origins = "*")
public class VersioningController {
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getUser", produces = "application/vnd.tomtom.flow-v1+json")
    public ResponseEntity<String> acceptTest(){
        return ResponseEntity.ok("Firstname");
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getUser", produces = "application/vnd.tomtom.flow-v2+json")
    public User acceptTestVersion2(){
        return new User("Firstname", "Lastname");
    }


    @CrossOrigin(origins = "*")
    @PostMapping(value = "/addUser", produces = "application/vnd.tomtom.flow-v3+json")//, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)//consumes = "application/vnd.tomtom.flow-v3+json")
    public void acceptPost( User user) {
        log.info("flow-v3 {} ", user);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/addUser", produces = "application/vnd.tomtom.flow-v4+json")//, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)//consumes = "application/vnd.tomtom.flow-v3+json")
    public void acceptPost2( User user) {
        log.info("flow-4 {}", user);
    }

    @CrossOrigin
    @GetMapping(value = "/getUser", headers = "x-api-version=1")
    public ResponseEntity<User> getUser(){
        User user = new User("fname5", "nlanme5");
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60,TimeUnit.MINUTES).cachePublic().sMaxAge(60,TimeUnit.MINUTES).noTransform()).body(user);
    }

    @CrossOrigin
    @GetMapping(value = "/getUser/{id}")
    @Cacheable
    public ResponseEntity<User> getUserPlain(@PathVariable("id") int id ){
        User user = new User("fname5", ""+id);
        log.info("from plain getUser ");
        return ResponseEntity.ok(user);
    }

}
