package com.carl.web;

import com.carl.web.domain.dto.AddressDTO;
import com.carl.web.domain.dto.CompanyDTO;
import com.carl.web.domain.dto.UserDTO;
import com.carl.web.masker.AddressMasker;
import com.carl.web.masker.NameMasker;
import com.example.mask.core.MaskerHolder;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author carl
 */
public class FreemarkerTest {

    @Test
    public void test1() throws JsonProcessingException {
        MaskerHolder.register(new AddressMasker(), "addressMasker");
        MaskerHolder.register(new NameMasker(), "nameMasker");
        CompanyDTO companyDTO = CompanyDTO.builder()
                .name("AAA")
                .address(AddressDTO.builder().street("SSSS").build())
                .employees(new ArrayList<>())
                .build();
        UserDTO userDTO = UserDTO.builder()
                .id(2L)
                .name("aa")
                .company(companyDTO)
                .build();
        UserSerializer userSerializer = new UserSerializer();
        AddressDTOSerializer70 addressDTOSerializer70 = new AddressDTOSerializer70();
        UserDTOSerializer3 userDTOSerializer3 = new UserDTOSerializer3();
        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(UserDTO.class, userSerializer);
        simpleModule.addSerializer(AddressDTO.class, addressDTOSerializer70);
        simpleModule.addSerializer(UserDTO.class, userDTOSerializer3);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(simpleModule);
        System.out.println(objectMapper.writeValueAsString(userDTO));
    }

    static class UserSerializer extends StdSerializer<UserDTO> {

        public UserSerializer() {
            this(null);
        }

        protected UserSerializer(Class<UserDTO> t) {
            super(t);
        }

        @Override
        public void serialize(UserDTO value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeStartObject();
            gen.writeObjectField("company", value.getCompany());
            gen.writeEndObject();
        }
    }

    public class AddressDTOSerializer70 extends StdSerializer<AddressDTO> {
        public AddressDTOSerializer70() {
            this((Class)null);
        }

        public AddressDTOSerializer70(Class<AddressDTO> var1) {
            super(var1);
        }

        public void serialize(AddressDTO var1, JsonGenerator var2, SerializerProvider var3) throws IOException {
            var2.writeStartObject();
            var2.writeStringField("building", var1.getBuilding());
            var2.writeStringField("street", MaskerHolder.getMasker("addressMasker").mask(var1.getStreet()));
            var2.writeStringField("city", var1.getCity());
            var2.writeStringField("state", var1.getState());
            var2.writeStringField("country", var1.getCountry());
            var2.writeEndObject();
        }
    }

    public class UserDTOSerializer3 extends StdSerializer<UserDTO> {
        public UserDTOSerializer3() {
            this((Class)null);
        }

        public UserDTOSerializer3(Class<UserDTO> var1) {
            super(var1);
        }

        public void serialize(UserDTO var1, JsonGenerator var2, SerializerProvider var3) throws IOException {
            var2.writeStartObject();
            var2.writeObjectField("id", var1.getId());
            var2.writeStringField("uuid", var1.getUuid());
            var2.writeStringField("name", MaskerHolder.getMasker("nameMasker").mask(var1.getName()));
            var2.writeObjectField("mobile", var1.getMobile());
            var2.writeObjectField("company", var1.getCompany());
            var2.writeEndObject();
        }
    }
}
