package Spring5MvcRestApplication.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AbstractRestControllerTest {

    public static String asJsonString(final Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
