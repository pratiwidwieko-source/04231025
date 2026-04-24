import java.util.*

class User(var username: String, var password: String, var saldo: Int)

class Transaction(val tipe: String, val jumlah: Int)

class KantinPay {
    private val scanner = Scanner(System.`in`)
    private val user = User("Dwi Pratiwi", "uwing2005", 50000)
    private val history = mutableListOf<Transaction>()

    fun start() {
        println("=== KANTINPAY ITK ===")
        login()
    }

    private fun login() {
        print("Username: ")
        val inputUser = scanner.nextLine()
        print("Password: ")
        val inputPass = scanner.nextLine()

        if (inputUser == user.username && inputPass == user.password) {
            println("Login berhasil!\n")
            menu()
        } else {
            println("Login gagal!\n")
            login()
        }
    }

    private fun menu() {
        while (true) {
            println("=== MENU ===")
            println("1. Cek Saldo")
            println("2. Top Up")
            println("3. Bayar")
            println("4. Riwayat Transaksi")
            println("5. Exit")
            print("Pilih: ")

            when (scanner.nextLine()) {
                "1" -> cekSaldo()
                "2" -> topUp()
                "3" -> bayar()
                "4" -> lihatRiwayat()
                "5" -> {
                    println("Terima kasih telah menggunakan KantinPay ITK")
                    break
                }
                else -> println("Pilihan tidak valid\n")
            }
        }
    }

    private fun cekSaldo() {
        println("Saldo Anda: Rp ${user.saldo}\n")
    }

    private fun topUp() {
        print("Masukkan jumlah top up: ")
        val amount = scanner.nextLine().toIntOrNull()

        if (amount != null && amount > 0) {
            user.saldo += amount
            history.add(Transaction("Top Up", amount))
            println("Top up berhasil!\n")
        } else {
            println("Input tidak valid\n")
        }
    }

    private fun bayar() {
        print("Masukkan jumlah pembayaran: ")
        val amount = scanner.nextLine().toIntOrNull()

        if (amount != null && amount > 0) {
            if (user.saldo >= amount) {
                user.saldo -= amount
                history.add(Transaction("Bayar", amount))
                println("Pembayaran berhasil!\n")
            } else {
                println("Saldo tidak cukup\n")
            }
        } else {
            println("Input tidak valid\n")
        }
    }

    private fun lihatRiwayat() {
        println("=== RIWAYAT TRANSAKSI ===")
        if (history.isEmpty()) {
            println("Belum ada transaksi\n")
        } else {
            for ((index, t) in history.withIndex()) {
                println("${index + 1}. ${t.tipe} Rp ${t.jumlah}")
            }
            println()
        }
    }
}

fun main() {
    val app = KantinPay()
    app.start()
}