package Hotel

import kotlin.system.exitProcess
import kotlin.math.ceil
import kotlin.math.floor

var totalReservasConfirmadas = 0
var totalEventosConfirmados = 0
var receitaHospedagem = 0.0
var receitaEventos = 0.0

val nomeHotel = "Terabithia"
var seuUsuario = ""
val quartosOcupados = BooleanArray(20) { false }

fun main() = inicio()

fun inicio() {
    println("Bem-vindo ao $nomeHotel")
    print("Digite o seu nome de usuário: ")
    seuUsuario = readln()

    var tentativas = 0
    while (tentativas < 3) {
        print("Digite a sua senha: ")
        if (readln() == "2678") {
            println("Bem-vindo ao Hotel $nomeHotel, $seuUsuario. É um imenso prazer ter você por aqui!")
            menuPrincipal()
            return
        }
        tentativas++
        println("Senha incorreta!")
    }
    println("SISTEMA BLOQUEADO!")
    exitProcess(0)
}

fun menuPrincipal() {
    while (true) {
        println("\n--- MENU PRINCIPAL ---")
        println("1. Reservas de Quartos\n2. Cadastro de Hóspedes\n3. Eventos\n4. Ar-Condicionado\n5. Abastecimento\n6. Relatórios Operacionais\n7. Sair")

        when (readln().toIntOrNull()) {
            1 -> subReservas()
            2 -> CadastrarHospedes() // No outro arquivo
            3 -> subEventos()
            4 -> subArCondicionado()
            5 -> subAbastecimento()
            6 -> subRelatorios()
            7 -> {
                println("Muito obrigado e até logo, $seuUsuario.")
                exitProcess(0)
            }
            else -> println("Opção inválida! Por favor, informe um número entre 1 e 7.")
        }
    }
}


fun subReservas() {
    print("Informe o valor da diária: "); val valor = readln().toDoubleOrNull() ?: -1.0
    print("Quantidade de diárias (1-30): "); val dias = readln().toIntOrNull() ?: -1

    if (valor <= 0 || dias !in 1..30) {
        println("Valor inválido, $seuUsuario")
        return
    }

    print("Nome do hóspede: "); val nomeH = readln()
    println("Tipo: (S)tandard, (E)xecutivo, (L)uxo")
    val fator = when(readln().uppercase()) { "S" -> 1.0; "E" -> 1.35; "L" -> 1.65; else -> return }

    var q: Int
    while (true) {
        print("Quarto (1-20): "); q = readln().toIntOrNull() ?: 0
        if (q in 1..20 && !quartosOcupados[q-1]) break
        println("Quarto já está ocupado ou inválido.")
    }

    val total = (valor * dias * fator) * 1.10
    println("Total: R$ %.2f. Confirmar? (S/N)".format(total))
    if (readln().uppercase() == "S") {
        quartosOcupados[q-1] = true
        totalReservasConfirmadas++
        receitaHospedagem += total
        println("Reserva efetuada com sucesso.")
    }
}


fun subEventos() {
    print("Convidados: "); val conv = readln().toIntOrNull() ?: -1
    if (conv < 0 || conv > 350) { println("Número de convidados inválido"); return }

    val aud = if (conv <= 220) "Laranja" else "Colorado"
    if (aud == "Laranja" && conv > 150) println("Auditório Laranja (necessário ${conv-150} cadeiras extras)")
    else println("Auditório selecionado: $aud")

    print("Dia: "); val dia = readln().lowercase()
    print("Hora: "); val hora = readln().toIntOrNull() ?: 0

    val disp = when(dia) {
        "sabado", "domingo" -> hora in 7..15
        else -> hora in 7..23
    }

    if (!disp) { println("Auditório indisponível"); return }
    print("Empresa: "); val emp = readln()

    val garcons = ceil(conv/12.0).toInt() + floor(8/2.0).toInt() // Exemplo 8h
    val custoG = garcons * 8 * 10.50
    val custoB = (conv * 0.2 * 0.8) + (conv * 0.5 * 0.4) + ((conv * 7 / 100.0) * 34.0)

    println("Total do evento: R$ %.2f. Confirmar? (S/N)".format(custoG + custoB))
    if (readln().uppercase() == "S") {
        totalEventosConfirmados++
        receitaEventos += (custoG + custoB)
        println("Reserva efetuada com sucesso.")
    }
}


fun subArCondicionado() {
    var menorValor = Double.MAX_VALUE
    var melhorEmpresa = ""

    do {
        print("Empresa: "); val nome = readln()
        print("Valor por aparelho: "); val v = readln().toDoubleOrNull() ?: 0.0
        print("Quantidade: "); val q = readln().toIntOrNull() ?: 0
        print("Desconto (%): "); val d = (readln().toDoubleOrNull() ?: 0.0) / 100
        print("Mínimo para desconto: "); val min = readln().toIntOrNull() ?: 0
        print("Deslocamento: "); val desl = readln().toDoubleOrNull() ?: 0.0

        var total = v * q
        if (q >= min) total -= (total * d)
        total += desl

        println("O serviço de $nome custará R$ %.2f".format(total))
        if (total < menorValor) { menorValor = total; melhorEmpresa = nome }

        print("Deseja informar novos dados? (S/N): ")
    } while (readln().uppercase() == "S")

    println("Melhor orçamento: $melhorEmpresa por R$ %.2f".format(menorValor))
}


fun subAbastecimento() {
    print("Wayne Oil - Álcool: "); val wa = readln().toDoubleOrNull() ?: 0.0
    print("Wayne Oil - Gasolina: "); val wg = readln().toDoubleOrNull() ?: 0.0
    print("Stark Petrol - Álcool: "); val sa = readln().toDoubleOrNull() ?: 0.0
    print("Stark Petrol - Gasolina: "); val sg = readln().toDoubleOrNull() ?: 0.0

    val custoW = if (wa <= wg * 0.7) wa * 42 else wg * 42
    val custoS = if (sa <= sg * 0.7) sa * 42 else sg * 42

    if (custoW < custoS) println("Mais barato no posto Wayne Oil.")
    else println("Mais barato no posto Stark Petrol.")
}


fun subRelatorios() {
    println("\n--- RELATÓRIO OPERACIONAL ---")
    println("Reservas: $totalReservasConfirmadas")
    println("Hóspedes: ${listaHospedes.size}")
    println("Eventos: $totalEventosConfirmados")
    println("Receita Total: R$ %.2f".format(receitaHospedagem + receitaEventos))
}