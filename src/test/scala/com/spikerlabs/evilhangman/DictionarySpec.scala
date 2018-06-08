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

  it should "list available difficulties" in {
    Dictionary(List(Word("a"), Word("ab"), Word("abcd"))).availableDifficulties shouldBe List(1, 2, 4)
  }

  behavior of "Dictionary with Difficulty"

  it should "return current difficulty" in {
    Dictionary.WithDifficulty(List(Word("a")), 1).chosenDifficulty shouldBe 1
  }

  it should "print the initial state" in {
    val dictionary = Dictionary(List(Word("abc"))).chooseDifficulty(3)
    dictionary.toString shouldBe "_ _ _"
  }

  it should "filter out the words, which do not match by revealed pattern" in new SampleDictionary {
    val dictionaryAfterAGuess = dictionary.guess('a')
    dictionaryAfterAGuess.words.length shouldBe 2
  }

  it should "choose the words group with highest difficulty if biggest groups are of the same size" in new SampleDictionary {
    val dictionaryAfterAGuess = dictionary.guess('a')
    dictionaryAfterAGuess.toString shouldBe "_ _ _ _"
  }

  it should "be guessed when the top word is guessed" in new SampleDictionary {
    val guessedDictionary = dictionary.guess('a').guess('b').guess('c').guess('d').guess('e')
    guessedDictionary.guessed shouldBe true
  }

  it should "keep the biggest group of possible solutions after a guess, even if it means that the score will be lower" in new SampleDictionary {
    val dictionaryAfterAGuess = dictionary.guess('b')
    dictionaryAfterAGuess.words.length shouldBe 2
  }

  trait SampleDictionary {
    val dictionary = Dictionary(
      List(
        Word("abba"), // a: 2 a__a | b: 2 _bb_
        Word("abab"), // a: 2 a_a_ | b: 2 _b_b
        Word("abcb"), // a: 1 a___ | b: 2 _b_b
        Word("adbb"), // a: 1 a___ | b: 2 __bb
        Word("bcde"), // a: 0 ____ | b: 1 b___
        Word("bdcb"), // a: 0 ____ | b: 2 b__b
      )
    ).chooseDifficulty(4)
  }

}
