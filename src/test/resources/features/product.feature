Feature: Gerenciamento de Produtos

  Scenario: Listar todos os produtos
    Given a lista de produtos não está vazia
    When eu solicito a lista de todos os produtos
    Then eu devo receber uma lista de produtos

  Scenario: Criar um produto com dados inválidos
    Given eu tenho dados de produto inválidos
    When eu tento criar um produto
    Then eu devo receber uma resposta de solicitação inválida

  Scenario: Excluir um produto com sucesso
    Given existe um produto com ID 1
    When eu excluo o produto com ID 1
    Then o produto deve ser excluído com sucesso