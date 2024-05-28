package com.rgarcia.w2m.utils;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertDto {
    
    private ModelMapper modelMapper;
    private ConvertDto(){
        modelMapper = new ModelMapper();
    }
    
    private static class Impl {
        private static final ConvertDto INSTANCE = new ConvertDto();
    }
    
    public static ConvertDto getInstance() {
        return Impl.INSTANCE;
    }
    
    public List get(List dtos, Class p)
    {
        if (null==dtos) return new ArrayList();
        
        return (List) dtos
            .stream()
            .map(item->{
                return create(item,p);
            })
            .collect(Collectors.toList());
    }
    
    public Object create(Object o, Class p){
        if (null==o) return null;
        return p.cast(getInstance().modelMapper.map(o, p));
    }

}