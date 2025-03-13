package org.isdb.StudentCRUD.controller;

import org.springframework.web.bind.annotation.*;

@RestController // REST(json), SOAP(xml), RPC,...
@RequestMapping
@CrossOrigin(origins = "http://localhost:4500") // CSRF
public class DummyController {

    @GetMapping("/hello") // API endpoint
    public String printHelloWorld() {
        return "Hello World";
    }

    @GetMapping("/bye")
    public String bye() {
        return "Chole ja";
    }

    @GetMapping("/square")
    public int square(@RequestParam int number) {
        return returnSquare(number);
    }

    @GetMapping("/info")
    public String info(@RequestParam(name = "my_name") String myName,
                       @RequestParam(required = false) String fatherName,
                       @RequestParam(value = "age") int sonAge
    ) {
        StringBuilder builder = new StringBuilder();

        if (fatherName != null) {
            builder.append("I am ")
                    .append(myName)
                    .append(" and my father's name is ")
                    .append(fatherName)
                    .append(" and age is ")
                    .append(sonAge);
        } else {
            builder.append("I am ")
                    .append(myName)
                    .append(" and I don't want to disclose my fathers name and my age is ")
                    .append(sonAge);
        }
        return builder.toString();
    }

    private int returnSquare(int num) {
        return num * num;
    }

}
