package com.spikerlabs.evilhangman

import org.scalatest.{FlatSpec, Matchers}

class WordSpec extends FlatSpec with Matchers {

  behavior of "Word Factory"

  it should "not allow empty word" in {
    an [IllegalArgumentException] should be thrownBy Word("")
  }

  behavior of "Word"

  it should "return the size" in {
    Word("abc").size should be(3)
    Word("abcd").size should be(4)
  }

  it should "return the complexity (number of unique letters)" in {
    Word("a").complexity should be(1)
    Word("abc").complexity should be(3)
    Word("abba").complexity should be(2)
  }

  it should "not be guessed initially" in {
    Word("a").guessed should be(false)
  }

  behavior of "Word guess"

  it should "return the same word when guess is not correct" in {
    val word = Word("a")
    word.guess('b') should be(word)
  }

  it should "decrease the complexity when guess is correct" in {
    val word = Word("ab")
    val attemptedGuess = word.guess('b')
    attemptedGuess.complexity should be(1)
  }

  it should "make word guessed when all it's letters are guessed" in {
    val word = Word("a")
    word.guess('a').guessed should be(true)
  }

  behavior of "Word printing"

  it should "output only underscores when none of the letters are guessed" in {
    Word("ab").toString should be("_ _")
  }

  it should "expose only guessed letters" in {
    Word("ab").guess('a').toString should be("a _")
    Word("abba").guess('b').toString should be("_ b b _")
  }

}
