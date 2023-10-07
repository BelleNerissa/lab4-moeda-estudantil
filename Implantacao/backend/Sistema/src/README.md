# mapeamento do CRUD de entidades

## Aluno "/api/aluno"

### Get by Id

requisição: /api/aluno/mostrar/id/{id}

### Get all

requisição: /api/aluno/listar

### Insert

requisição: /api/aluno/cadastrar

exemplo de corpo:{

    "nome": "Pedro",

    "email":"pedro@gmail.com",

    "senha": "12332",

    "rg": "31278378192312",

    "cpf": "32897137812",

    "endereco": "sla 123",

    "saldo": 59.0

}

### Delete by id

requisição /api/aluno/deletar/id/{id}

___________________________________________

## Empresa

### Get by Id

requisição: /api/Empresa/mostrar/id/{id}

### Get all

requisição: /api/Empresa/listar

### Insert

requisição: /api/Empresa/cadastrar

exemplo de corpo:{

"cnpj": "38213879",

"saldo": 139082812

}
}