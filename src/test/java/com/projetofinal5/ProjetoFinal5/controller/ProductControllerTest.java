package com.projetofinal5.ProjetoFinal5.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetofinal5.ProjetoFinal5.dto.ProductDTO;
import com.projetofinal5.ProjetoFinal5.dto.ProductListDTO;
import com.projetofinal5.ProjetoFinal5.entity.ProductEntity;
import com.projetofinal5.ProjetoFinal5.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

	private final static String URL_CRIAR_PRODUTO = "/produto/criar-produto";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@Autowired
	private ObjectMapper objectMapper;

	private ProductListDTO produtoListDTO;

	@BeforeEach
	public void setUp() {
		produtoListDTO = new ProductListDTO();
		List<ProductEntity> produtos = new ArrayList<>();
		ProductEntity produto1 = new ProductEntity();
		produto1.setId(1L);
		produto1.setProductName("Produto 1");
		produtoListDTO.setProdutoList(produtos);
	}


	// Nesse teste quando o "criar produto" for chamado junto do "produtoListDTO", ele vai retornar uma lista vazia, 201
	@Test
	public void criarProdutoComSucessoTest() throws Exception {
			when(productService.criarProduto(produtoListDTO)).thenReturn(Collections.emptyList());
			mockMvc.perform(post(URL_CRIAR_PRODUTO)
							.contentType(MediaType.APPLICATION_JSON)
							.content(objectMapper.writeValueAsString(produtoListDTO)))
					.andExpect(status().isCreated());
			}


	// Nesse teste será inserido um formato errado de JSON (aspas simples no lugar de duplas) e ele vai retornar 400
	@Test
	public void criarProdutoJsonInvalidoTest() throws Exception {
		String jsonInvalido = "{ nome: 'Produto' }";

		mockMvc.perform(post(URL_CRIAR_PRODUTO)
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonInvalido))
				.andExpect(status().isBadRequest());
	}


	// Nesse teste será feito a busca do produto pelo seu ID
	@Test
	public void buscarProdutoPorIdComSucessoTest() throws Exception {
		ProductDTO produtoMock = new ProductDTO(1L, "Produto A", "Descrição A", 100.0, 2);

		when(productService.buscarProdutoPorId(1L)).thenReturn(produtoMock);

		mockMvc.perform(get("/produto/{id}", 1L))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.nome").value("Produto A"))
				.andExpect(jsonPath("$.descricao").value("Descrição A"))
				.andExpect(jsonPath("$.preco").value(100.0))
				.andExpect(jsonPath("$.quantidade").value(2));
	}

}
