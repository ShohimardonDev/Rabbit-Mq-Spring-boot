package uz.ssh.rabbitmqspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.ssh.rabbitmqspringboot.constant.DirectSend;
import uz.ssh.rabbitmqspringboot.service.direct.DirectService;

@RestController
@RequestMapping("/direct")
@RequiredArgsConstructor
public class DirectController {
    private final DirectService directService;

    @PostMapping
    public void directSend(@RequestParam String message, @RequestParam DirectSend send) {
        directService.send(message, send);
    }
}

