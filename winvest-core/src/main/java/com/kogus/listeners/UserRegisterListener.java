package com.kogus.listeners;

import com.kogus.events.EventHandler;
import com.kogus.events.EventListener;
import com.kogus.events.EventPriority;
import com.kogus.events.eventmodels.users.UserRegisterEvent;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterListener implements EventListener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onUserRegister(UserRegisterEvent userRegisterEvent) {
        System.out.println("Kayıt olan kullanıcı: " + userRegisterEvent.getUserDto().getUsername());
        System.out.println("Kayıt olduğu tarih: " + userRegisterEvent.getDate());
    }


}
