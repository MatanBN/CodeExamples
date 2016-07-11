import random 

class Card(object):
   DIAMOND = 1
   HEART = 2
   SPADE = 3
   CLUB = 4

   SUITE_NAMES = {1: "Diamonds", 2: "Hearts", 3: "Spades", 4: "Clubs"}
   NAMED_RANKS = {1: "Ace", 11: "Prince", 12: "Queen", 13: "King"}

   def __init__(self, rank, suite):
      if rank < 1 or rank > 13:
         raise ValueError("Illegal rank for Card: %s" % rank)
      if suite < 1 or suite > 4:
         raise ValueError("Illegal suite for Card: %s" % suite)
      self._rank = rank
      self._suite = suite

   def rank(self): return self._rank
   def suite(self): return self._suite

   def isDiamond(self): return self._suite == Card.DIAMOND
   def isHeart(self):   return self._suite == Card.HEART
   def isSpade(self):   return self._suite == Card.SPADE
   def isClub(self):    return self._suite == Card.CLUB

   def isRed(self):
      return self.isHeart() or self.isDiamond()

   def isBlack(self):
      return not self.isRed()

   def __str__(self):
      if self.rank in Card.NAMED_RANKS.keys():
         rank = NAMED_RANKS[self.rank]
      else:
         rank = str(self.rank)
      return "%s of %s" % (rank, Card.SUITE_NAMES[self.suite])

   def __cmp__(self, other):
      rank = self.rank()
      orank = other.rank()
      if rank == 1: rank = 14
      if orank == 1: orank = 14
      return rank - orank
      

class Deck(object):
   def __init__(self):
      self._cards = []
      self._fill()

   def _fill(self):
      self._cards = []
      for rank in xrange(1, 14):
         self._cards.append(Card(rank, Card.DIAMOND))
      for rank in xrange(1, 14):
         self._cards.append(Card(rank, Card.HEART))
      for rank in xrange(1, 14):
         self._cards.append(Card(rank, Card.CLUB))
      for rank in xrange(1, 14):
         self._cards.append(Card(rank, Card.SPADE))

   def size(self):
      return len(self._cards)

   def topCard(self):
      if not self._cards: return None
      return self._cards[-1]

   def removeTop(self):
      if not self._cards: return
      self._cards.pop()

   def takeTopCard(self):
      top = self.topCard()
      self.removeTop()
      return top

   def isEmpty(self):
      return len(self._cards) == 0

   def shuffle(self):
      random.shuffle(self._cards)

class HalfHalfDealer(object):
   def deal(self, player1, player2):
      deck = Deck()
      deck.shuffle()
      while not deck.isEmpty():
         player1.collectCard(deck.takeTopCard())
         player2.collectCard(deck.takeTopCard())

class Pile(object):
   def __init__(self):
      self._cards = []

   def size(self): return len(self._cards)

   def top(self):  return self._cards[-1]

   def removeTop(self): self._cards.pop()

   def isEmpty(self): return len(self._cards) == 0

   def addCard(self, card): self._cards.append(card)

   def reversePile(self): self._cards.reverse()

   def addCardsFromPile(self, other):
      while not other.isEmpty():
         self.addCard(other.top())
         other.removeTop()

class Player(object):
   def __init__(self, name):
      self._name = name
      self._playingPile = Pile()
      self._winPile = Pile()

   def __str__(self):
      return "Player:%s" % self._name

   def hasCards(self):
      return self.numOfCards() > 0

   def numOfCards(self):
      return self._playingPile.size() + self._winPile.size()

   def playTopCard(self):
      top = self.getTopCard()
      self.removeTopCard()
      return top

   def getTopCard(self):
      if not self.hasCards(): return None
      if self._playingPile.isEmpty():
         self._switchToWinningPile()
      return self._playingPile.top()

   def removeTopCard(self):
      if self.hasCards():
         if self._playingPile.isEmpty():
            self._switchToWinningPile()
         self._playingPile.removeTop()

   def _switchToWinningPile(self):
      self._winPile.reversePile()
      self._playingPile = self._winPile
      self._winPile = Pile()

   def collectWinningCards(self, pile):
      self._winPile.addCardsFromPile(pile)

   def collectWinningCard(self, card):
      self._winPile.addCard(card)

   def collectCard(self, card):
      self._playingPile.addCard(card)

class WarGameLogic(object):
   def playOneTurn(self, player1, player2):
      """
      Play one round and return the winner.
      After the round, the winner has more cards and the loser has less.
      """
      pile = Pile()
      winner = self._drawCardsAndAnnounceWinner(player1, player2, pile)
      winner.collectWinningCards(pile)
      return winner

   def _drawCardsAndAnnounceWinner(self, player1, player2, pile):
      if not player1.hasCards(): return player2
      if not player2.hasCards(): return player1
      c1 = player1.playTopCard()
      c2 = player2.playTopCard()
      pile.addCard(c1)
      pile.addCard(c2)
      if (c1 > c2): return player1
      elif (c2 > c1): return player2
      else: return self._doWar(player1, player2, pile)

   def _doWar(self, player1, player2, pile):
      for i in xrange(2):
         c = player1.playTopCard()
         if c is not None: pile.addCard(c)
      for i in xrange(2):
         c = player2.playTopCard()
         if c is not None: pile.addCard(c)
      return self._drawCardsAndAnnounceWinner(player1, player2, pile)

class WarGame(object):
   def __init__(self, player1name, player2name, dealer = None):
      if dealer is None:
         dealer = HalfHalfDealer()
      self._playerOne = Player(player1name)
      self._playerTwo = Player(player2name)
      self._dealer = dealer
      self._numRounds = 0
      self._logic = WarGameLogic()

   def play(self, maxRounds):
      self._numRounds = 0
      self._dealer.deal(self._playerOne, self._playerTwo)
      while ((not self.isGameOver()) and self._numRounds < maxRounds):
         winner = self._logic.playOneTurn(self._playerOne, self._playerTwo)
         print "Round winner is: %s"  % winner
         self._numRounds += 1

   def isGameOver(self):
      return not (self._playerOne.hasCards() and self._playerTwo.hasCards())

   def getWinner(self):
      if self._playerOne.numOfCards() > self._playerTwo.numOfCards():
         return self._playerOne
      elif self._playerOne.numOfCards() < self._playerTwo.numOfCards():
         return self._playerTwo
      else:
         return None

   def numberOfRounds(self): return self._numRounds


def main(args):
   if (len(args) != 3):
      print "Error: need to supply two player names."
      return
   playerOneName = args[1]
   playerTwoName = args[2]
   dealer = HalfHalfDealer()
   game = WarGame(playerOneName, playerTwoName, dealer)
   game.play(100)
   winner = game.getWinner()
   if winner is None:
      print "Game ended in a draw."
   else:
      print "The winner is %s with %s cards." % (winner, winner.numOfCards())
   print "The game lasted %s rounds." % (game.numberOfRounds())

if __name__ == '__main__':
   import sys
   main(sys.argv)




