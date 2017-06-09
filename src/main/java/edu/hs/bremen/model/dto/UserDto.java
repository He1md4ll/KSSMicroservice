package edu.hs.bremen.model.dto;

import com.google.common.base.Preconditions;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class UserDto {

    @NotNull
    @NotEmpty
    private String userUuid;

    public UserDto(String userUuid) {
        Preconditions.checkNotNull(userUuid);
        this.userUuid = userUuid;
    }

    public String getUserUuid() {
        return userUuid;
    }
}