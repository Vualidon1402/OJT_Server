package com.ojt_server.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ojt_server.modules.user.model.Role;
import com.ojt_server.modules.user.model.RoleName;
import com.ojt_server.modules.user.model.UserModel;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private static final String secretKey = "thienpxc";
    //thời gian hết hạn token
    public static String createTokenUser(UserModel data) throws IllegalAccessException {
        JWTCreator.Builder builder = JWT.create().withIssuer("auth0");

        long oneHourInMillis = 3600 * 1000 * 48;
        Date expirationTime = new Date(System.currentTimeMillis() + oneHourInMillis);
        builder.withExpiresAt(expirationTime);

        Field[] fields = UserModel.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(data);
            if (value != null) {
                if (field.getName().equals("roles")) {
                    // Xử lý riêng cho trường roles
                    Set<Role> roles = (Set<Role>) value;
                    List<String> roleNames = roles.stream()
                            .map(role -> role.getRoleName().name())
                            .collect(Collectors.toList());
                    builder.withClaim("roles", String.join(",", roleNames));
                } else if (value instanceof Date) {
                    // Xử lý cho các trường kiểu Date
                    builder.withClaim(field.getName(), ((Date) value).getTime());
                } else {
                    builder.withClaim(field.getName(), value.toString());
                }
            }
        }

        return builder.sign(Algorithm.HMAC256(secretKey));
    }

    public static UserModel verifyTokenUser(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            DecodedJWT jwt = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build()
                    .verify(token);

            UserModel user = new UserModel();

            Long id = Long.valueOf(jwt.getClaim("id").asString());
            user.setId(id);
            String avatar = jwt.getClaim("avatar").asString();
            user.setAvatar(avatar);

            Long createdAtTimestamp = jwt.getClaim("createdAt").asLong();
            if (createdAtTimestamp != null) {
                user.setCreatedAt(new Date(createdAtTimestamp));
            }
            String email = jwt.getClaim("email").asString();
            user.setEmail(email);

            String fullName = jwt.getClaim("fullName").asString();
            user.setFullName(fullName);

            Boolean isDeleted = Boolean.valueOf(jwt.getClaim("isDeleted").asString());
            user.setDeleted(isDeleted);

            String phone = jwt.getClaim("phone").asString();
            user.setPhone(phone);


            Double point = Double.valueOf(jwt.getClaim("point").asString());
            user.setPoint(point);

            Boolean status = Boolean.valueOf(jwt.getClaim("status").asString());
            user.setStatus(status);

            Long updatedAtTimestamp = jwt.getClaim("updatedAt").asLong();
            if (updatedAtTimestamp != null) {
                user.setUpdatedAt(new Date(updatedAtTimestamp));
            }
            String username = jwt.getClaim("username").asString();
            user.setUsername(username);


            String rolesString = jwt.getClaim("roles").asString();
            Set<Role> roles = new HashSet<>();
            if (rolesString != null) {
                String[] rolesArray = rolesString.split(",");
                for (String role : rolesArray) {
                    try {
                        RoleName roleName = RoleName.valueOf(role.trim());
                        roles.add(new Role(roleName.name()));
                    } catch (IllegalArgumentException e) {
                        // Ignore any strings that are not valid RoleNames
                    }
                }
            }
            user.setRoles(roles);
            return user;
        } catch (JWTVerificationException exception){
            return null;
        }
    }
}
