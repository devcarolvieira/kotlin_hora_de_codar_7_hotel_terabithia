# Hotel Terabithia: Plataforma de Gestão Integrada

O **Hotel Terabithia** é um sistema de gestão interna desenvolvido em **Kotlin**, projetado sob a arquitetura de **software modular**. O sistema centraliza operações críticas do hotel, garantindo segurança, validação rigorosa de dados e relatórios operacionais consolidados.

## Arquitetura do Sistema
O software foi decomposto em módulos independentes e coesos, facilitando a manutenção e a legibilidade do código:

* **Módulo de Autenticação:** Controle de acesso com limite de 3 tentativas antes do bloqueio do sistema.
* **Gestão de Reservas:** Controle de ocupação para 20 quartos usando `BooleanArray`, com cálculo dinâmico de diárias e taxas (Standard, Executivo e Luxo).
* **Cadastro de Hóspedes (CRUD):** Gerenciamento em memória com busca por prefixo, exatidão, ordenação e registro de data/hora via `java.time`.
* **Gestão de Eventos:** Pipeline que seleciona auditórios, escala equipe de garçons e calcula custos de buffet.
* **Manutenção (Ar-Condicionado):** Analisador comparativo de orçamentos para identificar o menor valor de serviço.
* **Logística (Abastecimento):** Analisador econômico de combustíveis (Álcool vs Gasolina) baseado na regra de eficiência de 70%.
* **Relatórios Operacionais:** Dashboard consolidado com total de reservas, hóspedes e receita acumulada.

## Conceitos Técnicos Aplicados
* **Data Classes:** Uso de `data class Hospede` para modelagem de dados limpa e eficiente.
* **Programação Funcional:** Aplicação de funções de ordem superior como `.filter`, `.any`, `.forEach` e `.sortedBy`.
* **Robustez:** Tratamento de entradas nulas ou inválidas com `toIntOrNull()` e `toDoubleOrNull()`.
* **Modularização:** Funções independentes que garantem que o sistema retorne sempre ao menu principal sem encerrar abruptamente.

## Tecnologias Utilizadas
* **Linguagem:** [Kotlin](https://kotlinlang.org/)
* **Ferramentas:** IntelliJ IDEA / Kotlin Compiler
* **Bibliotecas:** `java.time` (Data/Hora), `kotlin.math` (Cálculos de teto e piso).

---

## Como utilizar

### Pré-requisitos
* Ter o JDK (Java Development Kit) instalado.
* Ter o compilador do Kotlin configurado.

### Execução
1. Clone o repositório.
2. Compile os arquivos `.kt`:
   ```bash
   kotlinc *.kt -include-runtime -d Hotel.jar
3 - Execute o sistema
```bash
java -jar Hotel.jar
