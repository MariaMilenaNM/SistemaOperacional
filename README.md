# SistemaOperacional

## Gerenciamento de Memória Virtual e Substituição de Páginas
O objetivo principal é desenvolver um simulador de gerenciamento de memória, implementando o mapeamento entre Memória Virtual e Memória Física e tratando faltas de página.

O simulador utiliza Threads para representar processos independentes que executam em paralelo. Cada thread acessa endereços de memória com base em uma sequência de operações (Leitura/Escrita) pré-definida.

## Funcionalidades Principais

* Simulação de Concorrência: Uso de threads para simular processos lendo e escrevendo na memória simultaneamente.
* Estruturação de Memória: Arrays parametrizados simulando a Memória Física, Memória Virtual e o armazenamento em Disco (SWAP).
* Entradas de Tabela de Página: Cada página virtual contém os bits de controle necessários para o sistema operacional:
  * Bit Referenciada.
  * Bit Modificada.
  * Bit Presente/Ausente.
  * Número da moldura de página.
* Log de Acessos: O sistema imprime no console a sequência de acesso de cada Thread, notificando visualmente quando ocorre uma falta de página.
* Persistência SWAP: Arquivo dedicado para salvar dados de páginas removidas da memória física e enviadas para o disco.

## Algoritmo de Substituição
O projeto possui a implementação do algoritmos de substituição de páginas LRU (Least Recently Used) para gerenciar as molduras cheias na Memória.

## Tecnologias e Ferramentas

* Linguagem Principal: Java
* Controle de Versão: Git & GitHub
