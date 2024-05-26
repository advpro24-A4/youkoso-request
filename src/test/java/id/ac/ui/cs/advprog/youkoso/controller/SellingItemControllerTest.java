package id.ac.ui.cs.advprog.youkoso.controller;


import id.ac.ui.cs.advprog.youkoso.model.Request;
import id.ac.ui.cs.advprog.youkoso.model.SellingItem;
import id.ac.ui.cs.advprog.youkoso.model.builder.RequestBuilder;
import id.ac.ui.cs.advprog.youkoso.model.builder.SellingItemBuilder;
import id.ac.ui.cs.advprog.youkoso.service.SellingItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;


@SpringBootTest(properties = {"spring.profiles.active=test"})
@AutoConfigureMockMvc
public class SellingItemControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SellingItemService sellingItemService;

    private Request request;

    private SellingItem sellingItem;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        this.request = new RequestBuilder()
                .requestId(UUID.fromString("a5c376a3-4817-44da-b8cf-cdd117f5e731"))
                .requestQuantity(1)
                .requestPrice(1000)
                .requestProduct("Product")
                .requestCurrency("IDR")
                .build();

        this.sellingItem = new SellingItemBuilder()
                .sellingItemId(UUID.fromString("a5c376a3-4817-44da-b8cf-cdd117f5e731"))
                .sellingItemQuantity(1)
                .sellingItemRequest(this.request)
                .build();
    }

    @Test
    void testCreateSellingItem() throws Exception {
        when(sellingItemService.createSellingItem(any(SellingItem.class))).thenReturn(sellingItem);

        mockMvc.perform(post("/sellingItem/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(sellingItem)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(sellingItem.getId().toString())));
    }

    @WithMockUser
    @Test
    void testCreateSellingItemFailure() throws Exception {
        when(sellingItemService.createSellingItem(any(SellingItem.class))).thenReturn(null);

        mockMvc.perform(post("/sellingItem/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(sellingItem)))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser
    @Test
    void testGetAllSellingItems() throws Exception {
        List<SellingItem> sellingItems = Arrays.asList(sellingItem);
        when(sellingItemService.findAllSellingItems()).thenReturn(sellingItems);

        mockMvc.perform(get("/sellingItem/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(sellingItem.getId().toString())));
    }

    @WithMockUser
    @Test
    void testGetAllSellingItemsFailure() throws Exception {
        when(sellingItemService.findAllSellingItems()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/sellingItem/list"))
                .andExpect(status().isNotFound());
    }

    @WithMockUser
    @Test
    void testGetSellingItemById() throws Exception {
        UUID itemId = sellingItem.getId();
        when(sellingItemService.findSellingItemById(itemId)).thenReturn(Optional.of(sellingItem));

        mockMvc.perform(get("/sellingItem/" + itemId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(sellingItem.getId().toString())));
    }

    @WithMockUser
    @Test
    void testGetSellingItemByIdFailure() throws Exception {
        UUID itemId = UUID.randomUUID();
        when(sellingItemService.findSellingItemById(itemId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/sellingItem/" + itemId.toString()))
                .andExpect(status().isNotFound());
    }

    @WithMockUser
    @Test
    void testUpdateSellingItem() throws Exception {
        UUID itemId = sellingItem.getId();
        SellingItem updatedSellingItem = new SellingItemBuilder()
                .sellingItemId(itemId)
                .sellingItemQuantity(2)
                .sellingItemRequest(this.request)
                .build();

        when(sellingItemService.updateSellingItem(eq(itemId), any(SellingItem.class))).thenReturn(updatedSellingItem);

        mockMvc.perform(put("/sellingItem/" + itemId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedSellingItem)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(updatedSellingItem.getId().toString())))
                .andExpect(jsonPath("$.quantity", is(updatedSellingItem.getQuantity())));
    }

    @WithMockUser
    @Test
    void testUpdateSellingItemFailure() throws Exception {
        UUID itemId = UUID.randomUUID();
        SellingItem updatedSellingItem = new SellingItemBuilder()
                .sellingItemId(itemId)
                .sellingItemQuantity(2)
                .sellingItemRequest(this.request)
                .build();

        when(sellingItemService.updateSellingItem(eq(itemId), any(SellingItem.class))).thenReturn(null);

        mockMvc.perform(put("/sellingItem/" + itemId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedSellingItem)))
                .andExpect(status().isNotFound());
    }

    @WithMockUser
    @Test
    void testDeleteSellingItem() throws Exception {
        UUID itemId = sellingItem.getId();
        when(sellingItemService.deleteSellingItem(itemId)).thenReturn(sellingItem);

        mockMvc.perform(delete("/sellingItem/delete/" + itemId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(sellingItem.getId().toString())));
    }

    @WithMockUser
    @Test
    void testDeleteSellingItemFailure() throws Exception {
        UUID itemId = UUID.randomUUID();
        when(sellingItemService.deleteSellingItem(itemId)).thenReturn(null);

        mockMvc.perform(delete("/sellingItem/delete/" + itemId.toString()))
                .andExpect(status().isNotFound());
    }

}
