package uz.ssh.rabbitmqspringboot.controller.hello;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.ssh.rabbitmqspringboot.service.hello.HelloWorldService;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HelloWorldController {
    private final HelloWorldService helloWorldService;

    @PostMapping
    public String sayHello(@RequestParam("name") String name) {
        helloWorldService.sayHello(name);
        return "Hello " + name;
    }
}
