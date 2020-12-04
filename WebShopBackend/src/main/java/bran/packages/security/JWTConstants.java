package bran.packages.security;

public class JWTConstants {

	public static final String SECRET_KEY = "bmw e39";

	public static final String AUTHORIZATION_HEADER = "Authorization";

	public static final String TOKEN_PREFIX = "Bearer ";

	public static final long EXPIRATION_TIME = 86400000; // One day, but it should be 15 minutes for security

    private JWTConstants() {}
	
}
