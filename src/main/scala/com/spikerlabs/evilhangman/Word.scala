package com.spikerlabs.evilhangman

case class Word private(private val value: String, alreadyGuessed: List[Char]) {
  require(value.nonEmpty)

  override def toString: String = spaceOut {
    value.map { (letter: Char) =>
      if (alreadyGuessed.contains(letter)) letter
      else '_'
    }
  }

  private def spaceOut(toStringSoFar: String): String =
    toStringSoFar.head + toStringSoFar.tail.foldLeft("")((acc: String, letter: Char) => acc + ' ' + letter)

  val size: Int = value.length

  lazy val complexity: Int = value.distinct.length - alreadyGuessed.length

  lazy val guessed: Boolean = complexity == 0

  def guessWeight(letter: Char): Int = value.count(_ == letter) - complexity

  def guess(letter: Char): Word =
    if (isIrrelevantGuess(letter)) this
    else this.copy(alreadyGuessed = alreadyGuessed :+ letter)

  private def isIrrelevantGuess(letter: Char): Boolean = alreadyGuessed.contains(letter) || !value.contains(letter)

}

object Word {

  def apply(value: String): Word = Word(value, Nil)

}