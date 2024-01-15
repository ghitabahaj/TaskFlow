package com.youcode.taskflow.Mapper;


import com.youcode.taskflow.dto.JetonDto;
import com.youcode.taskflow.entities.Jeton;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JetonMapper {
    JetonMapper INSTANCE = Mappers.getMapper(JetonMapper.class);


    @Mapping(source = "user.id", target = "userId")
    JetonDto entityToDto(Jeton jeton);

    Jeton dtoToEntity(JetonDto jetonDto);
}
