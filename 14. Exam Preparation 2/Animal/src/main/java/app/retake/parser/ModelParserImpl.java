package app.retake.parser;

import app.retake.parser.interfaces.ModelParser;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public final class ModelParserImpl implements ModelParser {

    public ModelParserImpl() {

    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass) {
        return null;
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass, PropertyMap<S, D> propertyMap) {
        return null;
    }
}
