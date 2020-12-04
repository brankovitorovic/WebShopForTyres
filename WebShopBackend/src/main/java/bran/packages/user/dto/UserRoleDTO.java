package bran.packages.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserRoleDTO {

	private Long id;

    @NotNull
    @NotBlank
    private String name;

	
}
