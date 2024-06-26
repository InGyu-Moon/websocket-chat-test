package data.chat.user;

import data.mapper.UserMapperInter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapperInter userMapperInter;

    public void saveUser(UserDto user) {
        if(userMapperInter.findById(user.getNickName()) != null) {
            user.setStatus("ONLINE");
            userMapperInter.updateUser(user);
        }else{
            user.setStatus("ONLINE");
            userMapperInter.insertUser(user);
        }
    }

    public void disconnect(UserDto user) {
        var storedUser = userMapperInter.findById(user.getNickName());
//        storedUser.setStatus(Status.OFFLINE);
        storedUser.setStatus("OFFLINE");
        userMapperInter.updateUser(storedUser);
    }

    public List<UserDto> findConnectedUsers() {
//        return userMapperInter.findAllByStatus(Status.ONLINE);
        return userMapperInter.findAllByStatus("ONLINE");
    }
}