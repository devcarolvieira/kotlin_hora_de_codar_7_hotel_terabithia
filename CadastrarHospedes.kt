package Hotel

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Hospede(
    var nome: String,
    val data: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
)

val listaHospedes = mutableListOf<Hospede>()

fun CadastrarHospedes() {
    while (true) {
        println("\n[Cadastro de Hóspedes]")
        println("1-Cadastrar 2-Pesquisar exato 3-Pesquisar prefixo 4-Listar 5-Atualizar 6-Remover 7-Voltar")

        when (readln().toIntOrNull()) {
            1 -> {
                if (listaHospedes.size >= 15) println("Máximo de cadastros atingido")
                else {
                    print("Nome: "); val n = readln().trim()
                    if (listaHospedes.any { it.nome.equals(n, true) }) println("Hóspede já cadastrado")
                    else { listaHospedes.add(Hospede(n)); println("Sucesso!") }
                }
            }
            2 -> {
                print("Nome: "); val n = readln()
                if (listaHospedes.any { it.nome.equals(n, true) }) println("Hospede $n encontrado")
                else println("Não encontrado")
            }
            3 -> {
                print("Prefixo: "); val p = readln()
                listaHospedes.filter { it.nome.startsWith(p, true) }.forEach { println(it.nome) }
            }
            4 -> listaHospedes.sortedBy { it.nome }.forEachIndexed { i, h ->
                println("[${i+1}] ${h.nome} - ${h.data}")
            }
            5 -> {
                print("Índice: "); val i = (readln().toIntOrNull() ?: 0) - 1
                if (i in listaHospedes.indices) { print("Novo nome: "); listaHospedes[i].nome = readln() }
            }
            6 -> {
                print("Índice: "); val i = (readln().toIntOrNull() ?: 0) - 1
                if (i in listaHospedes.indices) listaHospedes.removeAt(i)
            }
            7 -> return
        }
    }
}