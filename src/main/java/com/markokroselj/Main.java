package com.markokroselj;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

public class Main {
    public static void main(final String[] args) {
        final String token = "ODA3MDIxODQ1OTcwODEyOTYx.YBx7qQ.bgooU6UjlEVrB1SCuYDpINsUydc";
        final DiscordClient client = DiscordClient.create(token);
        final GatewayDiscordClient gateway = client.login().block();

        assert gateway != null;
        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            if ("starship".equalsIgnoreCase(message.getContent())) {
                final MessageChannel channel = message.getChannel().block();
                assert channel != null;
                channel.createMessage(":rocket:").block();
            }
        });

        gateway.onDisconnect().block();
    }
}
