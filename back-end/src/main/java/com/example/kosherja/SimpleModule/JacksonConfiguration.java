//package com.example.kosherja.SimpleModule;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//@Configuration
//public class JacksonConfiguration {
//
//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        // Create a SimpleModule for registering serializers and deserializers
//        SimpleModule module = new SimpleModule();
//
//        // Register a serializer for LocalDate
//        module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE));
//
//        // Register a deserializer for LocalDate
//        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE));
//
//        // Register the module with the ObjectMapper
//        objectMapper.registerModule(module);
//
//        return objectMapper;
//    }
//}
