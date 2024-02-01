package epicenergy_backend_buildweek.team5_buildweek2_backend.security;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.User;
import epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class JWTTools {

	@Value("${spring.jwt.secret}")
	private String secret;

	public String createToken(User user) {
		return Jwts.builder().subject(String.valueOf(user.getId()))
				.subject(String.valueOf(user.getId()))
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes()))
				.compact();
	}

	public void verifyToken(String token) {
		try {
			Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
		} catch (Exception ex) {
			throw new UnauthorizedException("Problemi col token! Per favore effettua di nuovo il login!");
		}
	}

	public String extractIdFromToken(String token) {
		return Jwts.parser()
				.verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
				.build()
				.parseSignedClaims(token).getPayload().getSubject();
	}
}
