package com.example.Parking.dto;

import com.example.Parking.model.Adress;
import com.example.Parking.outils.MappClass;
import lombok.Data;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.Parking.model.Adress} entity
 */
@Data
public class AdressDto implements Serializable {


    private final String numVoie;

    private final String nameVoie;

    private final String typeVoie;

    private final String postalCode;

    private final String city;

    // mapAccountToDTO
    public static AdressDto mapAdressToDTO(Adress adress){
        AdressDto adressDto= MappClass.mapper.map(adress,AdressDto.class);
        return adressDto;
    }

    //mapAccountToeEntity
    public static Adress mapAdressToEntity(AdressDto adressDto){
        Adress adress=MappClass.mapper.map(adressDto,Adress.class);
        return adress;
    }

}