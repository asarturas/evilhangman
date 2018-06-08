import com.spikerlabs.evilhangman.{Dictionary, Game, Hangman, Word}

import scala.annotation.tailrec

object Main extends App {
  val dictionary = Dictionary.Raw(
    List(
      Word("ant"),
      Word("bat"),
      Word("cat"),
      Word("clam"),
      Word("bear"),
      Word("camel"),
      Word("cobra"),
      Word("baboon"),
      Word("badger"),
      Word("beaver"),
      Word("cougar"),
      Word("coyote"),
    )
  )
  play(Game(dictionary).start)

  @tailrec
  def play(game: Game): Unit = game match {
    case _: Game.InProgress =>
      if (System.getenv("DEBUG") == "true") {
        println(game.dictionary.words)
        println(
          game.dictionary.words.map {
            case Word(value, guesses) => (value, guesses)
          }
        )
      }
      println(Hangman.display(game.numberOfMissesLeft, game.dictionary.toString))
      val letter = scala.io.StdIn.readChar()
      play(game.guess(letter))
    case _: Game.Lost =>
      game.dictionary.words.head match {
        case Word(value, _) => println(Hangman.displayLoss(value))
      }
    case _: Game.Won =>
      println(Hangman.display(game.numberOfMissesLeft, game.dictionary.toString))
      println(Hangman.displayWin)
  }
}
