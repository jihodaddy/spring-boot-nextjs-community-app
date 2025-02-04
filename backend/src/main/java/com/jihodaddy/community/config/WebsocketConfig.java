package com.jihodaddy.community.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.jihodaddy.community.util.websocket.WebSorketHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebsocketConfig implements WebSocketConfigurer{

  private final WebSorketHandler webSorketHandler;

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    // 클라이언트에서 접소고할 웹소켓 주소를 "/ws" 로 설정
    registry.addHandler(webSorketHandler, "/ws").setAllowedOrigins("*");
  }
}
