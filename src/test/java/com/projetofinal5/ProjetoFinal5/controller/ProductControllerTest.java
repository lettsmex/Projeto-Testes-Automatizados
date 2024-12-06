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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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


	// Nesse teste será feito a exclusão do produto pelo seu ID
	@Test
	public void deletarProdutoComSucessoTest() throws Exception {

		doNothing().when(productService).deletarProduto(1L);

		mockMvc.perform(delete("/produto/{id}", 1L))
				.andExpect(status().isNoContent())
				.andDo(print());
	}


	// Nesse teste será feito a tentativa de exclusão do produto por um ID inexistente
	@Test
	public void deletarProdutoNaoEncontradoTest() throws Exception {
		doThrow(new EntityNotFoundException("Produto não encontrado com ID 99"))
				.when(productService).deletarProduto(99L);

		mockMvc.perform(delete("/produto/{id}", 99L))
				.andExpect(status().isNotFound())
				.andDo(print());
	}

	// Teste para listar todos os produtos
	@Test
	public void listarTodosProdutosTest() throws Exception {
		List<ProductDTO> produtosMock = Arrays.asList(
				new ProductDTO(1L, "Produto A", "Descrição A", 100.0, 2),
				new ProductDTO(2L, "Produto B", "Descrição B", 200.0, 3)
		);

		when(productService.listarTodosProdutos()).thenReturn(produtosMock);

		mockMvc.perform(get("/produto/todos"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].nome").value("Produto A"))
				.andExpect(jsonPath("$[1].id").value(2L))
				.andExpect(jsonPath("$[1].nome").value("Produto B"));
	}

	// Teste para atualizar um produto com sucesso
	@Test
	public void atualizarProdutoComSucessoTest() throws Exception {
		ProductDTO produtoAtualizado = new ProductDTO(1L, "Produto Atualizado", "Descrição Atualizada", 150.0, 5);

		when(productService.atualizarProduto(anyLong(), any(ProductDTO.class))).thenReturn(produtoAtualizado);

		mockMvc.perform(put("/produto/atualizar-produto/{id}", 1L)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(produtoAtualizado)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.nome").value("Produto Atualizado"))
				.andExpect(jsonPath("$.descricao").value("Descrição Atualizada"))
				.andExpect(jsonPath("$.preco").value(150.0))
				.andExpect(jsonPath("$.quantidade").value(5));
	}

	// Teste para atualizar um produto não encontrado
	@Test
	public void atualizarProdutoNaoEncontradoTest() throws Exception {
		ProductDTO produtoDTO = new ProductDTO(99L, "Produto Inexistente", "Descrição", 100.0, 1);

		when(productService.atualizarProduto(anyLong(), any(ProductDTO.class)))
				.thenThrow(new EntityNotFoundException("Produto não encontrado com ID 99"));

		mockMvc.perform(put("/produto/atualizar-produto/{id}", 99L)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(produtoDTO)))
				.andExpect(status().isNotFound());
	}

}
