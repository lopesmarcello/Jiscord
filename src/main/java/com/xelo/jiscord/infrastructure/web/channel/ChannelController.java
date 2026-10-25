package com.xelo.jiscord.infrastructure.web.channel;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {

    @PostMapping
    public String create(@RequestBody CreateChannelRequest request) {
        return "Canal criado!" + request.name();
    }
}
