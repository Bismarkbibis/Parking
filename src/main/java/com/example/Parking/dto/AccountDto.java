package com.example.Parking.dto;

import com.example.Parking.emu.AcountStatu;
import com.example.Parking.emu.Role;
import com.example.Parking.model.Account;
import com.example.Parking.outils.MappClass;
import lombok.Data;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * A DTO for the {@link com.example.Parking.model.Account} entity
 */
@Data
public class AccountDto implements Serializable {

    private static final ModelMapper mapper = new ModelMapper();

    private final String password;

    private final String password02;

    private final String email;

    private final String username;
    // mapAccountToDTO
    public static AccountDto mapAcountToDTO(Account account){
        AccountDto accountDto = MappClass.mapper.map(account,AccountDto.class);
        return accountDto;
    }

    //mapAccountToeEntity
    public static Account mapAcountToEntity(AccountDto accountDto){
        Account account=MappClass.mapper.map(accountDto,Account.class);
        return account;
    }

}