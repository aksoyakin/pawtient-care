package com.aksoyakin.pawtientcarebe.dto.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityConverter<T, D> {

    private final ModelMapper modelMapper;

    public D mapEntityToDto(T entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public T mapDtoToEntity(D dto, Class<T> entityClass) {
        return modelMapper.map(dto, entityClass);
    }
}
