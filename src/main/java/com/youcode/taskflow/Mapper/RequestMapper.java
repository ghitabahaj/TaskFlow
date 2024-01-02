package com.youcode.taskflow.Mapper;


import com.youcode.taskflow.dto.RequestDto;
import com.youcode.taskflow.dto.TagDto;
import com.youcode.taskflow.entities.Request;
import com.youcode.taskflow.entities.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    RequestDto entityToDto(Request request);

    Request dtoToEntity(RequestDto requestDto);
}
