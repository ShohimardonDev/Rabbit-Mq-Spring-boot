package uz.ssh.rabbitmqspringboot.controller.publish;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.ssh.rabbitmqspringboot.service.publish.PublishService;

@RestController
@RequestMapping("/publish")
@RequiredArgsConstructor
public class PublishController {

    private final PublishService publishService;


    @PostMapping
    public void publish(@RequestParam("message") String message) {
        publishService.publish(message);
    }
}
