package com.projetofinal5.ProjetoFinal5.controller;

import com.projetofinal5.ProjetoFinal5.dto.ProductDTO;
import com.projetofinal5.ProjetoFinal5.dto.ProductListDTO;
import com.projetofinal5.ProjetoFinal5.entity.ProductEntity;
import com.projetofinal5.ProjetoFinal5.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/produto")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/criar-produto")
    public ResponseEntity<List<ProductEntity>> criarProduto(@RequestBody ProductListDTO produtoListDTO) {
        List<ProductEntity> novoProduto = productService.criarProduto(produtoListDTO);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ProductDTO>> listarProduto() {
        return ResponseEntity.ok().body(productService.listarTodosProdutos());
    }

    @GetMapping("/{id}")
    public ProductDTO buscarProdutoPorId(@PathVariable Long id) {
        return productService.buscarProdutoPorId(id);
    }

    @PutMapping("/atualizar-produto/{id}")
    public ResponseEntity<ProductDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProductDTO produtoDTO) {
        return ResponseEntity.ok().body(productService.atualizarProduto(id, produtoDTO));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        try {
            productService.deletarProduto(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}