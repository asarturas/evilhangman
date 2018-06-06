package com.spikerlabs.evilhangman

sealed trait Dictionary {

  def words: List[Word]

}

object Dictionary {

  def apply(words: List[Word]): Raw = {
    require(words.nonEmpty)
    Raw(words)
  }

  case class Raw private(words: List[Word]) extends Dictionary {
    def chooseDifficulty(level: Int): WithDifficulty =
      WithDifficulty(
        words = words.filter(_.size == level),
        level = level
      )
  }

  case class WithDifficulty private(words: List[Word], private val level: Int) extends Dictionary {

    val chosenDifficulty: Int = level

    override def toString: String = words.head.toString

    def guess(letter: Char): WithDifficulty = this.copy(words = words.map(_.guess(letter)).sortBy(0 - _.complexity))

    def guessed: Boolean = words.head.guessed

  }

}
