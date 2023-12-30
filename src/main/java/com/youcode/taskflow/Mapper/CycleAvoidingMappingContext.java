package com.youcode.taskflow.Mapper;

import java.util.IdentityHashMap;
import org.mapstruct.Context;

public class CycleAvoidingMappingContext {

    private IdentityHashMap<Object, Object> knownInstances = new IdentityHashMap<>();

    public <T> T getMappedInstance(Object source, @Context CycleAvoidingMappingContext context) {
        return (T) knownInstances.get(source);
    }

    public void storeMappedInstance(Object source, Object destination, @Context CycleAvoidingMappingContext context) {
        knownInstances.put(source, destination);
    }
}