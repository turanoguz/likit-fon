package com.kogus.listeners;

import com.kogus.events.EventHandler;
import com.kogus.events.EventListener;
import com.kogus.events.eventmodels.users.UserLoginEvent;

public class UserLoginListener implements EventListener {

    @EventHandler
    public void onUserLogin(UserLoginEvent userLoginEvent) {
        System.out.println("Giriş yapan kullanıcı: " + userLoginEvent.getUserDto().getUsername());
        System.out.println("Giriş yaptığı saat: " + userLoginEvent.getDate());
    }

}
