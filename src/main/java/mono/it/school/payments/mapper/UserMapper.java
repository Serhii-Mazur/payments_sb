package mono.it.school.payments.mapper;

import mono.it.school.payments.domain.User;
import mono.it.school.payments.entity.UserEntity;

public class UserMapper {

    public static UserEntity userToEntity(User user) {

        return new UserEntity(user.getFullName(), user.getEMail(), user.getPhoneNumber());
    }

    public static User entityToUser(UserEntity userEntity) {

        return new User(userEntity.getFullName(), userEntity.getEMail(), userEntity.getPhoneNumber());
    }
}
