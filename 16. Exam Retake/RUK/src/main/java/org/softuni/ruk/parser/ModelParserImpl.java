package org.softuni.ruk.parser;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.springframework.stereotype.Component;


@Component
public final class ModelParserImpl implements ModelParser {

    private final ModelMapper modelMapper;

    public ModelParserImpl() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass) {
        return this.modelMapper.map(source, destinationClass);
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass, PropertyMap<S, D> propertyMap) {
        this.modelMapper.addMappings(propertyMap);
        return this.convert(source, destinationClass);
    }
}
