package ru.company.server;


import org.springframework.web.bind.annotation.*;
import ru.company.shared.Message;

import java.util.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class HelloResource {

    Map<String, String> database;
    public HelloResource() {
        database = new HashMap<String, String>();
        database.put("1", "1");
        database.put("2", "2");
        database.put("3", "3");
        database.put("4", "4");
    }

    @RequestMapping(value = "/hellos", method = RequestMethod.GET)
    public Collection<String> get() {
        return database.values();
    }

    @RequestMapping(value = "/hellos/{id}", method = RequestMethod.GET)
    public String getHello(@PathVariable String id) {
        return id;
    }

    @RequestMapping(value = "/hellos", method = RequestMethod.POST)
    public Message getPostHello(@RequestBody Message message) {
        return message;
    }
}