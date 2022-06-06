package mono.it.school.payments.mapper;

import mono.it.school.payments.api.dto.RequestUserDto;
import mono.it.school.payments.domain.User;
import mono.it.school.payments.api.dto.ResponseUserDto;
import mono.it.school.payments.entity.UserEntity;

public class UserMapper {

    public static UserEntity userToEntity(User user) {
        UserEntity userEntity;
        if (user == null) {
            userEntity = null;
        } else {
            userEntity = new UserEntity(user.getFullName(), user.getEmail(), user.getPhoneNumber());
        }

        return userEntity;
    }

    public static User entityToUser(UserEntity userEntity) {
        User user;
        if (userEntity == null) {
            user = null;
        } else {
            user = new User(userEntity.getFullName(), userEntity.getEmail(), userEntity.getPhoneNumber());
        }

        return user;
    }

    public static User requestDtoToUser(RequestUserDto requestUserDto) {
        User user;
        if (requestUserDto == null) {
            user = null;
        } else {
            user = new User(requestUserDto.getFullName(), requestUserDto.getEmail(), requestUserDto.getPhoneNumber());
        }

        return user;
    }

    public static ResponseUserDto userToResponseDto(User user) {
        ResponseUserDto responseUserDto;
        if (user == null) {
            responseUserDto = null;
        } else {
            responseUserDto = new ResponseUserDto(user.getFullName(), user.getEmail(), user.getPhoneNumber());
        }

        return responseUserDto;
    }
}
