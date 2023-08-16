package me.shinsunyoung.springbootdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExampleController {
    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model){

        Person examplePerson = new Person();

        examplePerson.setId(1L);
        examplePerson.setName("홍길동");
        examplePerson.setAge(11);
        examplePerson.setHobbies( List.of("운동" , "독서" ));

        model.addAttribute( "person" , examplePerson);
        model.addAttribute( "today" , LocalDate.now());
        model.addAttribute( "today2" , "2023-08-12");

        String strDate ;


        return "example";
    }

    @Setter
    @Getter
    class Person{

        private long id;
        private String name;
        private int age;
        private List<String> hobbies;

    }
}
