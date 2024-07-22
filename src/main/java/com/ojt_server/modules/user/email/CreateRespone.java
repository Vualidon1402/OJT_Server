package com.ojt_server.modules.user.email;

import com.ojt_server.modules.user.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateRespone {
    private String message;
    private UserModel data;

    @Override
    public String toString() {
        return "CreateRespone{" +
                "message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
