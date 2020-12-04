package bran.packages.user.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserDTO {

	@NotNull
	@NotBlank
	private String username;
	
	@NotNull
	@NotBlank
	private String password;
	
	private String name;
	
	private String email;
	
    private Set<@NotNull UserRoleDTO> roles = new HashSet<>();

    private static final transient Pattern B_CRYPT_PATTERN = Pattern.compile("^\\$2[ayb]\\$.{56}$");

    @JsonIgnore
    public boolean isPasswordPlain() {
        return !B_CRYPT_PATTERN.matcher(password).matches();
    }
    
	
}
