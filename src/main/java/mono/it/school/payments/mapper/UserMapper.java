package mono.it.school.payments.mapper;

import mono.it.school.payments.domain.User;
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
}
