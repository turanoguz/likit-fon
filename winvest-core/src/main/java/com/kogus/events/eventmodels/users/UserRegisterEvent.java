package com.kogus.events.eventmodels.users;

import com.kogus.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class UserRegisterEvent {
    private UserDto userDto;
    private Date date;
}
