package uz.ssh.rabbitmqspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.ssh.rabbitmqspringboot.service.rpc.RpcClient;

/**
 * @author Shokhimardon
 * @since 9/16/25
 */

@RestController
@RequestMapping("/rpc")
@RequiredArgsConstructor
public class RpcController {
    private final RpcClient rpcClient;

    @PostMapping
    public String directSend(@RequestParam String message) {
        return  rpcClient.call(message);
    }

}
