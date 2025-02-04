package com.jihodaddy.community.util.websocket;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WebSorketHandler extends TextWebSocketHandler{
  private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
  
  /**
   *  클라이언트로부터 메세지가 도착했을 떄 호출되는 메소드
   */
  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    // 클라이언트로부터 받은 메세지
    String payload = message.getPayload();
    log.info("클라이언트에게 온 메세지 : {}",payload);

    // 모든 클라이언트에게 메세지 전송
    for(WebSocketSession webSocketSession: sessions) {
      if (session.isOpen()) {
        webSocketSession.sendMessage(new TextMessage("서버에서 보내는 메세지 : " + payload ));
      }
    }
  }

  /** 
   * 클라이언트와 연결이 성공했을 떄 호출되는 메소드
   */
  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    sessions.add(session);
    log.info("클라이언트와 연결 성공 : " + session.getId() + ", 접속 IP : " + session.getRemoteAddress()
      + ", 접속URI : " + session.getUri() + ", 접속ID : " + session.getAttributes().get("userId"));
    // 모든 클라이언트에게 메세지 전송
    for(WebSocketSession webSocketSession: sessions) {
      if (session.isOpen()) {
        webSocketSession.sendMessage(new TextMessage("서버에서 보내는 메세지" + session.getId() + "님이 접속하셨습니다."));
      }
    }
  }

  /**
   * 클라이언트와 연결이 끊겼을 때 호출되는 메소드
   */
  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    sessions.remove(session);
    log.info("클라이언트와 연결 끊김, 세션 ID : " + session.getId() + ", CloseStatus : " + status);
  
    // 모든 클라이언트에게 메시지 전송
    for (WebSocketSession webSocketSession : sessions) {
        if (session.isOpen()) {
            webSocketSession.sendMessage(new TextMessage("서버에서 보내는 메시지 : " + session.getId() + "님이 퇴장하셨습니다."));
        }
    }
  }


  
}
