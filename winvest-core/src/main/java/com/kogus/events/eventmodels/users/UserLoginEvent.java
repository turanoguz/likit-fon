package com.kogus.events.eventmodels.users;

import com.kogus.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class UserLoginEvent {
    private UserDto userDto;
    private Date date;
}
