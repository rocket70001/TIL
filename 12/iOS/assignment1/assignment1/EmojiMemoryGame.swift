//
//  EmojiMemoryGame.swift
//  assignment1
//
//  Created by go on 12/22/23.
//

import SwiftUI

class EmojiMemoryGame: ObservableObject {
    private static let emojis = ["🐢", "🐠", "🐡", "🐬", "🦭", "🦀", "🦈", "🐙", "🪼", "🐟", "🐳"]
    
    private static func createMemoryGame () -> Game<String> {
        return Game(numberOfPairsOfCards: 16) { pairIndex in
            if emojis.indices.contains(pairIndex) {
                return emojis[pairIndex]
            } else {
                return "🐋"
            }
        }
    }
    
    @Published private var model = createMemoryGame()
    
    var cards: Array<Game<String>.Card> {
        return model.cards
    }
    
    // MARK: - Intentes
    
    func shuffle() {
        model.shuffle()
    }
    
    func choose(_ card: Game<String>.Card) {
        model.choose(card)
    }
}
