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
  val shouldDebug = System.getenv("DEBUG") == "true"
  play(Game(dictionary).start)

  @tailrec
  def play(game: Game): Unit = game match {
    case _: Game.Pending => throw new IllegalStateException("does not supposed to end up in pending state")
    case _: Game.InProgress =>
      if (shouldDebug) debug(game.dictionary.words)
      println(Hangman.display(game.numberOfMissesLeft, game.dictionary.toString))
      play(game.guess(scala.io.StdIn.readChar()))
    case _: Game.Lost =>
      game.dictionary.words.head match {
        case Word(value, _) => println(Hangman.displayLoss(value))
      }
    case _: Game.Won =>
      println(Hangman.display(game.numberOfMissesLeft, game.dictionary.toString))
      println(Hangman.displayWin)
  }

  private def debug(words: List[Word]) = {
    println(words)
    println(
      words.map {
        case Word(value, guesses) => (value, guesses)
      }
    )
  }
}
