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

    def availableDifficulties: List[Int] = words.map(_.size).distinct.sorted

    def chooseDifficulty(level: Int): WithDifficulty =
      WithDifficulty(
        words = words.filter(_.size == level),
        level = level
      )

  }

  case class WithDifficulty private(words: List[Word], private val level: Int) extends Dictionary {

    val chosenDifficulty: Int = level

    override def toString: String = words.head.toString

    def guess(letter: Char): WithDifficulty = {
      this.copy(
        words = words
          .map(_.guess(letter)) // pass a guess to each word
          .groupBy(_.toString).values.toList // group words by same "reveal pattern" after a guess
          .groupBy(_.size).values // group "reveal pattern" groups by size of the group
          .maxBy(_.head.size) // get the "reveal pattern" groups which have most words in them
          .maxBy(_.foldLeft(0)(_ + _.complexity)) // pick the "reveal pattern" group, in which words combine to biggest complexity
      )
    }

    def guessed: Boolean = words.head.guessed

  }

}
