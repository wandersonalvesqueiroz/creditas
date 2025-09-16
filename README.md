# API de Simulação de Crédito

API RESTful desenvolvida com Spring Boot para calcular os valores de uma 
simulação de crédito para um cliente da creditas.

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot 3.5.4
- Swagger/OpenAPI
- Maven
- Docker

---

# 💰 Simulação de crédito

Este projeto implementa a lógica de cálculo de valor de crédito disponível para o cliente com base na sua idade. 
Nessa simulação são passados os dados de data de nascimento, valor solicitado e quantidade de parcelas.
Como resultado é apresentado o valor total com Juros acrescido, o total de juros que será pago e o valor das parcelas.

---

## 🧠 Padrão Strategy

https://refactoring.guru/design-patterns/strategy

O padrão Strategy foi adotado para encapsular diferentes formas de cálculo de crédito
de acordo com as especificidades do cliente, permitindo que o comportamento seja alterado 
dinamicamente conforme as regras de negócios possam serem alteradas ou mesmo adicionadas
novas regras.

---

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/creditas/credit_simulator/
│   │       ├── config/             # Configurações gerais da aplicação.
│   │       ├── controller/         # Endpoint da API REST.
│   │       ├── exception/          # Exceções customizadas.
│   │       ├── factory/            # Factory crentralizar a lógica.
│   │       ├── messaging/          # Publicação e consumo de mensagens.
│   │       ├── model/              # Entidades e DTOs.
│   │       ├── repository/         # Repositório banco.
│   │       ├── service/            # Lógica principal.
│   │       ├── strategy/           # Strategy para determinar o cálculo.
│   │       └── util/               # Utilitários diversos.
│   └── resources/                  # Arquivos de configuração e recursos.
│       ├── application.properties
│       ├── application-test.properties
│       ├── config.properties
│       ├── messages.properties     # Mensagens utilizadas centralizadas.
│       └── swagger.yaml
└── test/                           # Testes unitários e de integração
```

---

## ✅ Testes

Os testes foram escritos com JUnit 5, cobrindo os principais fluxos.
Teste intergrado utilizando banco local H2.
Teste de Carga utiizando JMeter.

## 📌 Regras de Negócio

1. Cálculo de Taxa de Juros Anual:

- Até 25 anos: 5% a.a.
- 26 a 40 anos: 3% a.a.
- 41 a 60 anos: 2% a.a.
- Acima de 60 anos: 4% a.a.

2. Fórmula de Cálculo de Parcelas Fixas (PMT):

(PV * r) / (1 - Math.pow(1 + r, -n))

- PMT = Pagamento mensal
- PV = Valor presente (valor do empréstimo)
- r = Taxa de juros mensal (taxa anual/12)
- n = Número total de pagamentos (prazo em meses)

---

### Passos

```bash
# Clone o projeto
git clone https://github.com/wandersonalvesqueiroz/creditas.git

# Compile e execute o projeto
./mvnw spring-boot:run
```

> A aplicação será executada em: `http://localhost:8080`


---

## 🔄 Endpoints Disponíveis

| Método | Endpoint               | Descrição                                            |
|--------|------------------------|------------------------------------------------------|
| GET    | `/simulate-loan`       | Calcula o valor de crédito disponível para o cliente |

---


Desenvolvido por **Wanderson Alves**  
🔗 [LinkedIn](https://www.linkedin.com/in/wandersonalvesqueiroz/)  
📧 wandersonmg18@gmail.com