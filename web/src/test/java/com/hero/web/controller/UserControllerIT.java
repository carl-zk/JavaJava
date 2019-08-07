package com.hero.web.controller;

import com.alibaba.fastjson.JSON;
import com.hero.web.IntegrationTest;
import com.hero.web.domain.dto.UserDTO;
import com.hero.web.domain.vo.UserVO;
import com.hero.web.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@TestPropertySource(properties = {"logging.level.root=info"})
public class UserControllerIT {
    @Autowired
    private MockMvc mvc;
    @Autowired
    UserService userService;

    @Test
    public void getUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/user/1").accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void listPartUsersTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/users").accept(MediaType.APPLICATION_JSON_UTF8)
                .param("uuids", "1,2,3"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateUserWhenIdNotNull() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/user").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(new UserVO(1L, "小红", null))))
                .andExpect(status().isBadRequest())
                .andExpect(mvcResult -> {
                    System.out.println(mvcResult.getResolvedException().getMessage());
                });
    }

    @Test
    public void testCreateUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/user").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(new UserVO(null, "小红", null))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUser2() {
        UserDTO userDTO = userService.register(UserVO.builder().name("小米").build());
        assertNotNull(userDTO.getId());
        System.out.println(userDTO.getId());
    }
}
