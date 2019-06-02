package com.daniel.decryptor

import java.lang.Exception
import kotlin.system.exitProcess

class Cipher {
    private val qwerty = "1!23#4$5%6&7/8(9)0='?¿q @¡wertyuiop΅'+*~asdfghjklñ{[^}]`<z>xcvbnm,;.:-_".toList()

    fun decrypt (message: String): List<Char> {
        val decryptedMessage = mutableListOf<Char>()
        message.toList().forEach { character ->
            qwerty.forEachIndexed { position, charQwerty ->
                if (charQwerty == character) {
                    if (position == 0) {
                        decryptedMessage.add(qwerty[qwerty.size - 1])
                    } else {
                        decryptedMessage.add(qwerty[position - 1])
                    }
                }
            }
        }
        print("\nDecrypted message: ")
        return decryptedMessage
    }

    fun encrypt (message: String): List<Char> {
        val encryptedMessage = mutableListOf<Char>()
        message.toList().forEach { character ->
            qwerty.forEachIndexed { position, charQwerty ->
                if (charQwerty == character) {
                    if (position == qwerty.size - 1) {
                        encryptedMessage.add(qwerty[0])
                    } else {
                         encryptedMessage.add(qwerty[position + 1])
                    }
                }
            }
        }
        print("\nEncrypted message: ")
        return encryptedMessage
    }

    fun toPrint(message: List<Char>){
        println()
        message.forEach {
            print(it)
        }
        println("\n\n|-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-|")
    }
}

class Board {
    fun getOption (): Int{
        do {
            println()
            println("choose your option")
            println("1.Encrypt")
            println("2.Decrypt")
            println("3.Exit")
            println()
            print("Option: ")
            try {
                val option = readLine()?.toInt() ?: 0
                if(option in 1..3){
                    return option
                } else {
                    println("Wrong option")
                }
            } catch (e: Exception) {
                println("Oops!! try again!!")
            }
        }while (true)
    }

    fun getMessage (): String {
        do {
            println()
            println("Type your message:")
            try {
                return readLine() ?: ""
            } catch (e: Exception) {
                println("Oops!! something went wrong with your message. ")
            }
        }while (true)
    }
}

fun main (args: Array<String>) {
    println("     _     _       _                                   \n" +
            "  ___| |__ (_)_ __ | |__   ___ _ __    __ _ _ __  _ __  \n" +
            " / __| '_ \\| | '_ \\| '_ \\ / _ \\ '__|  / _` | '_ \\| '_ \\ \n" +
            "| (__| | | | | |_) | | | |  __/ |    | (_| | |_) | |_) |\n" +
            " \\___|_| |_|_| .__/|_| |_|\\___|_|     \\__,_| .__/| .__/ \n" +
            "             |_|                           |_|   |_|    ")
    println("By Dany - https://github.com/danialmanza")
    println()
    val board = Board()
    val cipher = Cipher()
    do {
        val option = board.getOption()
        val message = when (option) {
            1 -> cipher.encrypt(message = board.getMessage())
            2 -> cipher.decrypt(message = board.getMessage())
            else -> {
                println("Bye")
                exitProcess(1)
            }
        }
        //even faster than joinToString
        cipher.toPrint(message = message)
    } while (true)
}