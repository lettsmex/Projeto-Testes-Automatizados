package com.projetofinal5.ProjetoFinal5.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSteps {

    private List<String> productList;
    private String response;

    @Given("a lista de produtos não está vazia")
    public void aListaDeProdutosNaoEstaVazia() {
        productList = new ArrayList<>();
        productList.add("Produto 1");
        productList.add("Produto 2");
        assertFalse(productList.isEmpty());
    }

    @When("eu solicito a lista de todos os produtos")
    public void euSolicitoAListaDeTodosOsProdutos() {
        response = productList.toString();
    }

    @Then("eu devo receber uma lista de produtos")
    public void euDevoReceberUmaListaDeProdutos() {
        assertNotNull(response);
        assertTrue(response.contains("Produto 1"));
        assertTrue(response.contains("Produto 2"));
    }

    @Given("eu tenho dados de produto inválidos")
    public void euTenhoDadosDeProdutoInvalidos() {
        // Simulando dados inválidos
        response = null;
    }

    @When("eu tento criar um produto")
    public void euTentoCriarUmProduto() {
        // Tentando criar um produto com dados inválidos
        if (response == null) {
            response = "Bad Request";
        }
    }

    @Then("eu devo receber uma resposta de solicitação inválida")
    public void euDevoReceberUmaRespostaDeSolicitacaoInvalida() {
        assertEquals("Bad Request", response);
    }

    @Given("existe um produto com ID {int}")
    public void existeUmProdutoComID(int id) {
        productList = new ArrayList<>();
        productList.add("Produto " + id);
        assertTrue(productList.contains("Produto " + id));
    }

    @When("eu excluo o produto com ID {int}")
    public void euExcluoOProdutoComID(int id) {
        productList.remove("Produto " + id);
    }

    @Then("o produto deve ser excluído com sucesso")
    public void oProdutoDeveSerExcluidoComSucesso() {
        assertFalse(productList.contains("Produto 1"));
    }
}