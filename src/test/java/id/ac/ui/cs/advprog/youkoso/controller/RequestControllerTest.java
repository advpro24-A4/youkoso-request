package id.ac.ui.cs.advprog.youkoso.controller;

import id.ac.ui.cs.advprog.youkoso.model.Request;
import id.ac.ui.cs.advprog.youkoso.model.builder.RequestBuilder;
import id.ac.ui.cs.advprog.youkoso.service.RequestService;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest(properties = {"spring.profiles.active=test"})
@AutoConfigureMockMvc
public class RequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestService requestService;

    private Request request;


    @BeforeEach
    void setUp() {

        this.request = new RequestBuilder()
                .requestId(UUID.fromString("a5c376a3-4817-44da-b8cf-cdd117f5e731"))
                .requestQuantity(1)
                .requestPrice(1000)
                .requestProduct("Product")
                .requestCurrency("IDR")
                .build();
    }

    @Test
    void testCreateRequest() throws Exception {
        when(requestService.createRequest(any(Request.class))).thenReturn(request);

        mockMvc.perform(post("/request/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"requestId\": \"a5c376a3-4817-44da-b8cf-cdd117f5e731\", \"requestQuantity\": 1, \"requestPrice\": 1000, \"requestProduct\": \"Product\", \"requestCurrency\": \"IDR\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(request.getId().toString())));
    }

    @Test
    void testCreateRequestBadRequest() throws Exception {
        // Arrange
        Request badRequest = new RequestBuilder()
                .requestId(UUID.fromString("a5c376a3-4817-44da-b8cf-cdd117f5e731"))
                .requestQuantity(1)
                .requestPrice(1000)
                .requestProduct("Bad Product")
                .requestCurrency("IDR")
                .build();

        when(requestService.createRequest(badRequest)).thenReturn(null);

        // Act and Assert
        mockMvc.perform(post("/request/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"requestId\": \"a5c376a3-4817-44da-b8cf-cdd117f5e731\", \"requestQuantity\": 1, \"requestPrice\": 1000, \"requestProduct\": \"Bad Product\", \"requestCurrency\": \"IDR\" }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetAllRequests() throws Exception {
        List<Request> requests = new ArrayList<>();
        requests.add(request);

        when(requestService.findAllRequest()).thenReturn(requests);

        mockMvc.perform(get("/request/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(request.getId().toString())));
    }

    @Test
    void testGetAllRequestsNotFound() throws Exception {
        // Arrange
        when(requestService.findAllRequest()).thenReturn(new ArrayList<>());

        // Act and Assert
        mockMvc.perform(get("/request/list"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetRequestById() throws Exception {
        UUID requestId = UUID.fromString("a5c376a3-4817-44da-b8cf-cdd117f5e731");

        when(requestService.findRequestById(requestId)).thenReturn(request);

        mockMvc.perform(get("/request/" + requestId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(request.getId().toString())));
    }

    @Test
    void testGetRequestByIdNotFound() throws Exception {
        UUID requestId = UUID.fromString("a5c376a3-4817-44da-b8cf-cdd117f5e731");

        when(requestService.findRequestById(requestId)).thenReturn(null);

        mockMvc.perform(get("/request/" + requestId.toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateRequest() throws Exception {
        UUID requestId = UUID.fromString("a5c376a3-4817-44da-b8cf-cdd117f5e731");
        Request updatedRequest = new RequestBuilder()
                .requestId(requestId)
                .requestQuantity(2)
                .requestPrice(2000)
                .requestProduct("Updated Product")
                .requestCurrency("IDR")
                .build();

        when(requestService.updateRequest(any(UUID.class), any(Request.class))).thenReturn(updatedRequest);

        mockMvc.perform(put("/request/update/" + requestId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"requestId\": \"" + requestId.toString() + "\", \"requestQuantity\": 2, \"requestPrice\": 2000, \"requestProduct\": \"Updated Product\", \"requestCurrency\": \"IDR\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(updatedRequest.getId().toString())))
                .andExpect(jsonPath("$.quantity", is(updatedRequest.getQuantity())))
                .andExpect(jsonPath("$.price", is(updatedRequest.getPrice())))
                .andExpect(jsonPath("$.product", is(updatedRequest.getProduct())));
    }

    @Test
    void testUpdateRequestNotFound() throws Exception {
        UUID requestId = UUID.fromString("a5c376a3-4817-44da-b8cf-cdd117f5e731");
        Request updatedRequest = new RequestBuilder()
                .requestId(requestId)
                .requestQuantity(2)
                .requestPrice(2000)
                .requestProduct("Updated Product")
                .requestCurrency("IDR")
                .build();

        when(requestService.updateRequest(any(UUID.class), any(Request.class))).thenReturn(null);

        mockMvc.perform(put("/request/update/" + requestId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"requestId\": \"" + requestId.toString() + "\", \"requestQuantity\": 2, \"requestPrice\": 2000, \"requestProduct\": \"Updated Product\", \"requestCurrency\": \"IDR\" }"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteRequest() throws Exception {
        UUID requestId = UUID.fromString("a5c376a3-4817-44da-b8cf-cdd117f5e731");

        when(requestService.deleteRequest(requestId)).thenReturn(request);

        mockMvc.perform(delete("/request/delete/" + requestId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(request.getId().toString())));
    }

    @Test
    void testDeleteRequestNotFound() throws Exception {
        UUID requestId = UUID.fromString("a5c376a3-4817-44da-b8cf-cdd117f5e731");

        when(requestService.deleteRequest(requestId)).thenReturn(null);

        mockMvc.perform(delete("/request/delete/" + requestId.toString()))
                .andExpect(status().isNotFound());
    }

}