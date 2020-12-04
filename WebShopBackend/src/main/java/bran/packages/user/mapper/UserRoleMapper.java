package bran.packages.user.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import bran.packages.user.dto.UserRoleDTO;
import bran.packages.user.entity.UserRole;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {

	UserRoleDTO toDTO(UserRole userRole);

    UserRole fromDTO(UserRoleDTO userRoleDTO);

    List<UserRoleDTO> toDTOList(List<UserRole> userRoles);
	
}
