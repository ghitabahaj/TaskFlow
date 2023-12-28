package com.youcode.taskflow.Mapper;


import com.youcode.taskflow.dto.TagDto;
import com.youcode.taskflow.entities.Tag;
import com.youcode.taskflow.entities.Task;import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagDto entityToDto(Tag tag);

    Tag dtoToEntity(TagDto userDTO);
}
