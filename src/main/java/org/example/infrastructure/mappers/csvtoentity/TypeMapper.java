package org.example.infrastructure.mappers.csvtoentity;

import org.example.domain.importation.entity.Pokemon;
import org.example.domain.importation.entity.Type;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TypeMapper {

    TypeMapper INSTANCE = Mappers.getMapper( TypeMapper.class );

    @Mapping(source = "name", target = "name")
    Type csvBeanToEntity(String name);
}
