package com.spikerlabs.evilhangman

import org.scalatest.{FlatSpec, Matchers}

class HangmanDisplaySpec extends FlatSpec with Matchers {

  behavior of "a Hangman display"

  it should "display fresh game" in {
    Hangman.display(6, "_") shouldBe
      """
        |  +---+
        |  |   |
        |      |
        |      |
        |      |
        |      |
        |=========
        | Guess a letter in this word: _""".stripMargin
  }

  it should "display game after 1 miss" in {
    Hangman.display(5, "_") shouldBe
      """
        |  +---+
        |  |   |
        |  o   |
        |      |
        |      |
        |      |
        |=========
        | Guess a letter in this word: _""".stripMargin
  }

  it should "display game after 2 misses" in {
    Hangman.display(4, "_") shouldBe
      """
        |  +---+
        |  |   |
        |  o   |
        |  |   |
        |      |
        |      |
        |=========
        | Guess a letter in this word: _""".stripMargin
  }

  it should "display game after 3 misses" in {
    Hangman.display(3, "_") shouldBe
      """
        |  +---+
        |  |   |
        |  o   |
        | /|   |
        |      |
        |      |
        |=========
        | Guess a letter in this word: _""".stripMargin
  }

  it should "display game after 4 misses" in {
    Hangman.display(2, "_") shouldBe
      """
        |  +---+
        |  |   |
        |  o   |
        | /|\  |
        |      |
        |      |
        |=========
        | Guess a letter in this word: _""".stripMargin
  }

  it should "display game after 5 misses" in {
    Hangman.display(1, "_") shouldBe
      """
        |  +---+
        |  |   |
        |  o   |
        | /|\  |
        | /    |
        |      |
        |=========
        | Guess a letter in this word: _""".stripMargin
  }

  it should "display lost game" in {
    Hangman.displayLoss("a") shouldBe
      """
        |  +---+
        |  |   |
        |  o   |
        | /|\  |
        | / \  |
        |      |
        |=========
        | HANGED, the word was: a""".stripMargin
  }

  it should "display won game" in {
    Hangman.displayWin shouldBe
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

}
