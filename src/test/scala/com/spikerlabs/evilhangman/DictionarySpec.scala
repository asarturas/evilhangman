package com.spikerlabs.evilhangman

import org.scalatest.{FlatSpec, Matchers}

class DictionarySpec extends FlatSpec with Matchers {

  behavior of "Dictionary Factory"

  it should "not allow to create an empty dictionary" in {
    an [IllegalArgumentException] should be thrownBy Dictionary(Nil)
  }

  it should "create a raw dictionary" in {
    Dictionary(List(Word("a"))) shouldBe Dictionary.Raw(List(Word("a")))
  }

  behavior of "Raw Dictionary"

  it should "allow to choose a difficulty once" in {
    val dictionaryWithDifficulty = Dictionary(List(Word("a"), Word("ab"))).chooseDifficulty(1)
    dictionaryWithDifficulty shouldBe Dictionary.WithDifficulty(List(Word("a")), 1)
  }

  behavior of "Dictionary with Difficulty"

  it should "return current difficulty" in {
    Dictionary.WithDifficulty(List(Word("a")), 1).chosenDifficulty shouldBe 1
  }

  it should "print the initial state" in {
    val dictionary = Dictionary(List(Word("abc"))).chooseDifficulty(3)
    dictionary.toString shouldBe "_ _ _"
  }

  it should "keep the least guessed word on top" in new SampleDictionary {
    val dictionaryAfterAGuess = dictionary.guess('a')
    dictionaryAfterAGuess.words shouldBe List(Word("abcd", List('a')), Word("abba", List('a')))
    dictionaryAfterAGuess.toString shouldBe "a _ _ _"
  }

  it should "be guessed when the top word is guessed" in new SampleDictionary {
    val guessedDictionary = dictionary.guess('a').guess('b').guess('c').guess('d')
    guessedDictionary.guessed shouldBe true
  }

  trait SampleDictionary {
    val dictionary = Dictionary(
      List(
        Word("abba"),
        Word("abcd"),
      )
    ).chooseDifficulty(4)
  }

}
