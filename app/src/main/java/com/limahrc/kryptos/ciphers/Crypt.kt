private val puncts = charArrayOf(' ', ',', '.', ';', ':', '?')

class Alphabet {
    companion object {
        fun getAlphabet(): StringBuilder {
            val alph = StringBuilder()
            for (i in 'a'..'z') alph.append(i)
            return alph
        }

        fun getInvertedAlphabet(): StringBuilder {
            val invAlph = getAlphabet()
            return getAlphabet().reverse()
        }
    }
}


/*  Encripta e desencripta uma mensagem através de cifra de Atbash.
*   @message: mensagem a ser criptografada
*/  fun atbash(message: String): String {
    val res = StringBuilder()
    val alphabet = Alphabet.getAlphabet()
    val inv = Alphabet.getInvertedAlphabet()
    for (i in message) {
        if (puncts.contains(i)) res.append(i)
        for (j in alphabet) {
            if (i == j) res.append(inv[alphabet.indexOf(j)])
        }
    }
    return res.toString()
}

/*  Encripta e desencripta uma mensagem através da Cifra de César.
*   @offset: deslocamento/chave de troca para a criptografia
*   @encrypt: indicador da operação; encriptar = true, desencriptar = false
*/  fun caesar(message: String, key: Int = 3, encrypt: Boolean = true): String {
    val res = StringBuilder()
    val alphabet = Alphabet.getAlphabet()
    for (i in message) {
        if (puncts.contains(i)) res.append(i)
        else {
            var pos = if (encrypt) (alphabet.indexOf(i) + key) % 26
            else (alphabet.indexOf(i) - key) % 26
            if (pos < 0) pos += 26
            else if (pos > 25) pos -= 26
            res.append(alphabet[pos])
        }
    }
    return res.toString()
}

/*
*   Encripta e desencripta uma mensagem através da cifra de Vigenère.
*   @key: palavra-chave para a criptografia
*   @encrypt: indicador da operação; encriptar = true, desencriptar = false
*/ fun vigenere(message: String, key: String, encrypt: Boolean = true): String {
    val res = StringBuilder()
    val alphabet = Alphabet.getAlphabet()
    var e = 0 //counter for helping with letters position in the alphabet
    var treatedkey = key
    if (key.length < message.length) { //repeats the key itself in order to get the size of the message
        treatedkey = key.repeat(message.length - key.length)
    }
    for (i in message) {
        if (puncts.contains(i)) res.append(i)
        if (encrypt) {
            res.append(caesar(i.toString(), alphabet.indexOf(treatedkey[e++])))
        } else {
            res.append(caesar(i.toString(), alphabet.indexOf(treatedkey[e++]), false))
        }
    }
    return res.toString()
}

/*  Testa e exibe as 25 possíveis chaves de encriptação aplicadas sobre uma
*   mensagem utilizando a cifra de César.
*/  fun caesarBruteForce(message: String) {
    var key = 1
    while (key < 26) {
        println("attempt with shift-key #$key: ${caesar(message, key, false)}")
        key++
    }
}

/* Famoso algoritmo utilizado em sistemas UNIX */
fun ROT13(message: String): String {
    return caesar(message, 13)
}