package com.spikerlabs.evilhangman

import org.scalatest.{FlatSpec, Matchers}

class GameSpec extends FlatSpec with Matchers {

  behavior of "Game Factory"

  it should "start with raw dictionary" in {
    Game(Dictionary.Raw(List(Word("a"), Word("b"), Word("c")))) shouldBe a[Game.Pending]
  }

  behavior of "Gameplay"

  it should "start with arbitrary difficulty" in {
    val game = Game(Dictionary.Raw(List(Word("a")))).start

    game.dictionary.words.map(_.size).distinct.size shouldBe 1
    game shouldBe a[Game.InProgress]

  }

  it should "allow to for 6 misses initially" in {
    Game(Dictionary.Raw(List(Word("a")))).start.numberOfMissesLeft shouldBe 6
  }

  it should "allow to take a guess" in {
    Game.InProgress(Dictionary.WithDifficulty(List(Word("ab")), 1)).guess('b') shouldBe
      Game.InProgress(Dictionary.WithDifficulty(List(Word("ab", List('b'))), 1))
  }

  it should "decrease number of misses left after a missed guess" in {
    Game(Dictionary.Raw(List(Word("a")))).start.guess('b').numberOfMissesLeft shouldBe 5
  }

  it should "be lost by player after 6 missed guesses" in {
    Game(Dictionary.Raw(List(Word("a")))).start
      .guess('b').guess('b').guess('b').guess('b').guess('b').guess('g') shouldBe a[Game.Lost]
  }

  it should "be won by player when they guess the word" in {
    Game(Dictionary.Raw(List(Word("a")))).start
      .guess('a') shouldBe a[Game.Won]
  }

}
