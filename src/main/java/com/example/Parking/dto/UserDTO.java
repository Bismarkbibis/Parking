package com.example.Parking.dto;

import com.example.Parking.model.Account;
import com.example.Parking.model.User;
import com.example.Parking.outils.MappClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
public class UserDTO implements Serializable {

    private static final ModelMapper mapper = new ModelMapper();

    private final String  name;
    private final String firstName;
    private final String number;
    private final Date dateIncription;


    static UserDTO mapUserToDTO(User user){
        UserDTO userDTO = MappClass.mapper.map(user,UserDTO.class);
        return userDTO;
    }

    //mapAccountToeEntity
     static User mapUserToEntity(UserDTO userDTO){
        User user=MappClass.mapper.map(userDTO,User.class);
        return user;
    }

}
