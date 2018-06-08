package com.spikerlabs.evilhangman

import scala.util.Random

sealed trait Game {
  def dictionary: Dictionary
  def guess(letter: Char): Game = throw new IllegalStateException("can only guess when game is in progress")
  def numberOfMissesLeft: Int = 6
}

object Game {

  def apply(dictionary: Dictionary.Raw): Pending = Pending(dictionary)

  case class Pending(dictionary: Dictionary.Raw) extends Game {

    def start: InProgress = {
      val arbitraryDifficulty = Random.shuffle(dictionary.availableDifficulties).head
      InProgress(dictionary.chooseDifficulty(arbitraryDifficulty))
    }

  }

  case class InProgress private(dictionary: Dictionary.WithDifficulty, override val numberOfMissesLeft: Int = 6) extends Game {

    override def guess(letter: Char): Game = {
      val dictionaryAfterAGuess = dictionary.guess(letter)
      if (missed(dictionary, dictionaryAfterAGuess)) {
        val nextNumberOfMissesLeft = numberOfMissesLeft - 1
        if (nextNumberOfMissesLeft == 0) Game.Lost(dictionary)
        else this.copy(numberOfMissesLeft = numberOfMissesLeft - 1)
      }
      else if (dictionaryAfterAGuess.guessed) Game.Won(dictionaryAfterAGuess, numberOfMissesLeft)
      else this.copy(dictionary = dictionaryAfterAGuess)
    }

    private def missed(dictionaryBeforeAGuess: Dictionary, dictionaryAfterAGuess: Dictionary): Boolean =
      dictionaryBeforeAGuess.words.length == dictionaryAfterAGuess.words.length &&
        dictionaryBeforeAGuess.toString == dictionaryAfterAGuess.toString

  }

  case class Lost private(dictionary: Dictionary.WithDifficulty) extends Game {
    override val numberOfMissesLeft: Int = 0
  }

  case class Won private(dictionary: Dictionary.WithDifficulty, override val numberOfMissesLeft: Int) extends Game

}
