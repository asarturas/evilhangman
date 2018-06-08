package com.spikerlabs.evilhangman

object Hangman {

  def display(numberOfGuessesLeft: Int, word: String): String = numberOfGuessesLeft match {
    case 6 =>
      """
        |  +---+
        |  |   |
        |      |
        |      |
        |      |
        |      |
        |=========
        | Guess a letter in this word: """.stripMargin + word
    case 5 =>
      """
        |  +---+
        |  |   |
        |  o   |
        |      |
        |      |
        |      |
        |=========
        | Guess a letter in this word: """.stripMargin + word
    case 4 =>
      """
        |  +---+
        |  |   |
        |  o   |
        |  |   |
        |      |
        |      |
        |=========
        | Guess a letter in this word: """.stripMargin + word
    case 3 =>
      """
        |  +---+
        |  |   |
        |  o   |
        | /|   |
        |      |
        |      |
        |=========
        | Guess a letter in this word: """.stripMargin + word
    case 2 =>
      """
        |  +---+
        |  |   |
        |  o   |
        | /|\  |
        |      |
        |      |
        |=========
        | Guess a letter in this word: """.stripMargin + word
    case 1 =>
      """
        |  +---+
        |  |   |
        |  o   |
        | /|\  |
        | /    |
        |      |
        |=========
        | Guess a letter in this word: """.stripMargin + word

  }

  def displayLoss(word: String): String =
    """
      |  +---+
      |  |   |
      |  o   |
      | /|\  |
      | / \  |
      |      |
      |=========
      | HANGED, the word was: """.stripMargin + word

  def displayWin: String =
    """
        |                                 .''.
        |       .''.             *''*    :_\/_:     .
        |      :_\/_:   .    .:.*_\/_*   : /\ :  .'.:.'.
        |  .''.: /\ : _\(/_  ':'* /\ *  : '..'.  -=:o:=-
        | :_\/_:'.:::. /)\*''*  .|.* '.\'/.'_\(/_'.':'.'
        | : /\ : :::::  '*_\/_* | |  -= o =- /)\    '  *
        |  '..'  ':::'   * /\ * |'|  .'/.\'.  '._____
        |      *        __*..* |  |     :      |.   |' .---"|
        |       _*   .-'   '-. |  |     .--'|  ||   | _|    |
        |    .-'|  _.|  |    ||   '-__  |   |  |    ||      |
        |    |' | |.    |    ||       | |   |  |    ||      |
        | ___|  '-'     '    ""       '-'   '-.'    '`      |____
        |jgs~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        |                        YOU WON!
      """.stripMargin

}
