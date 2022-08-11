package org.example.infrastructure.mappers.csvtoentity;

import javax.annotation.processing.Generated;
import org.example.domain.importation.entity.Type;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-07T15:02:52-0300",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 18.0.1.1 (Oracle Corporation)"
)
public class TypeMapperImpl implements TypeMapper {

    @Override
    public Type csvBeanToEntity(String name) {
        if ( name == null ) {
            return null;
        }

        Type type = new Type();

        type.setName( name );

        return type;
    }
}
