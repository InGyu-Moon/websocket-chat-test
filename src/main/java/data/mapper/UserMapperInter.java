package data.mapper;

import org.apache.ibatis.annotations.Mapper;
import data.chat.user.Status;
import data.chat.user.UserDto;

import java.util.List;

@Mapper
public interface UserMapperInter {
    public void insertUser(UserDto user);
    public UserDto findById(String nickName);
    public void updateUser(UserDto user);
    public List<UserDto> findAllByStatus(String status);
}
