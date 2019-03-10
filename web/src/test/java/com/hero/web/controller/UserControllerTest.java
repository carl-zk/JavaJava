package com.hero.web.controller;

import com.alibaba.fastjson.JSON;
import com.hero.web.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

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
    public void createUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/user").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(new UserVO(null, null, null))))
                .andExpect(status().isBadRequest())
                .andExpect(mvcResult -> {
                    System.out.println(mvcResult.getResolvedException().getMessage());
                });
    }
}
