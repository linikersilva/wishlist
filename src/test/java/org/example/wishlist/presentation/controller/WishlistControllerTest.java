package org.example.wishlist.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.wishlist.application.WishlistService;
import org.example.wishlist.domain.entity.Category;
import org.example.wishlist.domain.entity.Product;
import org.example.wishlist.presentation.dto.request.WishlistRequestDto;
import org.example.wishlist.presentation.dto.response.WishlistResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class WishlistControllerTest {

    @MockBean
    private WishlistService wishlistService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("The method 'listAllProductsFromClientWishlist' should return status 200 - OK when response is set of products")
    @Test
    void listAllProductsFromClientWishlistShouldReturnOkWhenResponseIsSetOfProducts() throws Exception {
        Category category = Category.create("Jardinagem");
        category.setId("1");
        Product product1 = Product.create("produto1", "imagemUrlProduto1", BigDecimal.ONE, category);
        product1.setId("1");
        Product product2 = Product.create("produto2", "imagemUrlProduto2", BigDecimal.TWO, Category.create("Computadores"));
        product2.setId("2");
        Product product3 = Product.create("produto3", "imagemUrlProduto3", BigDecimal.TEN, category);
        product3.setId("3");
        Set<Product> expectedResult = Set.of(product1, product2, product3);

        WishlistResponseDto responseDto =
                new WishlistResponseDto("Wishlist products of client with id: 1 listed successfully", expectedResult);

        when(wishlistService.listAllProductsFromClientWishlist(anyString())).thenReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/wishlist/clientId/{clientId}", "1")
                        .contentType(MediaType.APPLICATION_JSON))

        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(responseDto)))
        .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("The method 'listAllProductsFromClientWishlist' should return status 200 - OK when response is empty set")
    @Test
    void listAllProductsFromClientWishlistShouldReturnOkWhenResponseIsEmptySet() throws Exception {
        WishlistResponseDto responseDto =
                new WishlistResponseDto("Wishlist products of client with id: 1 listed successfully", Collections.emptySet());

        when(wishlistService.listAllProductsFromClientWishlist(anyString())).thenReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/wishlist/clientId/{clientId}", "1")
                        .contentType(MediaType.APPLICATION_JSON))

        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(responseDto)))
        .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("The method 'checkIfProductIsInClientWishlist' should return status 200 - OK when response is set with the product")
    @Test
    void checkIfProductIsInClientWishlistShouldReturnOkWhenResponseIsSetWithTheProduct() throws Exception {
        Category category = Category.create("Jardinagem");
        category.setId("1");
        Product product = Product.create("produto1", "imagemUrlProduto1", BigDecimal.ONE, category);
        product.setId("2");
        Set<Product> expectedResult = Set.of(product);

        WishlistResponseDto responseDto =
                new WishlistResponseDto("Product with id: 2 is in client's wishlist", expectedResult);

        when(wishlistService.checkIfProductIsInClientWishlist(anyString(), anyString())).thenReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/wishlist/clientId/{clientId}/productId/{productId}", "1", product.getId())
                        .contentType(MediaType.APPLICATION_JSON))

        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(responseDto)))
        .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("The method 'checkIfProductIsInClientWishlist' should return status 200 - OK when response is empty set")
    @Test
    void checkIfProductIsInClientWishlistShouldReturnOkWhenResponseIsEmptySet() throws Exception {
        WishlistResponseDto responseDto =
                new WishlistResponseDto("Product with id: 2 is not in client's wishlist", Collections.emptySet());

        when(wishlistService.checkIfProductIsInClientWishlist(anyString(), anyString())).thenReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/wishlist/clientId/{clientId}/productId/{productId}", "1", "2")
                        .contentType(MediaType.APPLICATION_JSON))

        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(responseDto)))
        .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("The method 'addProductToClientWishlist' should return status 200 - OK when response is successfully message")
    @Test
    void addProductToClientWishlistShouldReturnOkWhenSuccessfullyResponseMessage() throws Exception {
        WishlistResponseDto responseDto =
                new WishlistResponseDto("Product with id: 2 was successfully added to the client's wishlist", null);

        WishlistRequestDto requestDto =
                new WishlistRequestDto("1", "2");

        when(wishlistService.addProductToClientWishlist(requestDto)).thenReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/wishlist")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON))

        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(responseDto)))
        .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("The method 'removeProductFromClientWishlist' should return status 200 - OK when response is successfully message")
    @Test
    void removeProductFromClientWishlistShouldReturnOkWhenSuccessfullyResponseMessage() throws Exception {
        WishlistResponseDto responseDto =
                new WishlistResponseDto("Product with id: 2 was successfully removed from the client's wishlist", null);

        WishlistRequestDto requestDto =
                new WishlistRequestDto("1", "2");

        when(wishlistService.removeProductFromClientWishlist(requestDto)).thenReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.delete("/wishlist")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON))

        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(responseDto)))
        .andDo(MockMvcResultHandlers.print());
    }
}