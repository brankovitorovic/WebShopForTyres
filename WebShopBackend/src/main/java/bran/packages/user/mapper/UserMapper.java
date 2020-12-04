package bran.packages.user.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import bran.packages.user.dto.UserDTO;
import bran.packages.user.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    User fromDTO(UserDTO userDTO);

    List<UserDTO> toDTOList(List<User> users);	
	
	
	
}
