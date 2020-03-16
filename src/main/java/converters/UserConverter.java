package converters;

import entities.Account;
import entities.User;
import services.dtos.UserDto;

public class UserConverter {

    public static UserDto toService(controllers.dtos.UserDto dto) {
        return UserDto.builder()
                .age(dto.getAge())
                .name(dto.getName())
                .accountId(dto.getAccountId())
                .build();
    }

    public static User toUser(Account account, UserDto dto) {
        return User.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .account(account)
                .build();
    }

    public static UserDto fromUser(User user) {
        return UserDto.builder()
                .accountId(user
                        .getAccount()
                        .getId())
                .age(user.getAge())
                .name(user.getName())
                .build();
    }

    public static controllers.dtos.UserDto fromService(UserDto dto) {
        return controllers.dtos.UserDto
                .builder()
                .accountId(dto.getAccountId())
                .age(dto.getAge())
                .name(dto.getName())
                .build();
    }
}