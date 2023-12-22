//
//  ContentView.swift
//  assignment1
//  Created by go on 12/10/23.
//

import SwiftUI

struct ContentView: View {
    let seaEmojis = ["🐢", "🐠", "🐡", "🐬", "🦭", "🦀", "🦈", "🐙", "🪼", "🐟", "🐳"]
    let xmasEmojis = ["⛪", "🎄", "🎅🏻", "🎅🏿", "🤶🏼", "❄️", "🛷", "🧦", "🎁", "🦌", "🔔", "🧸"]
    let faceEmojis = ["😆", "😇", "🤩", "🥸", "🤓", "😘", "🫨", "🧐", "😅", "😋", "🥳", "😮", "😐"]
    @ObservedObject var viewModel: EmojiMemoryGame
    @State var cardCount: Int = 4

    
    var body: some View {
        VStack{
            Color(.whiteSmoke).ignoresSafeArea(.all)
            ScrollView {
                cards
                    .animation(.default, value: viewModel.cards)
            }
        }
    }
    
    var cards: some View {
        LazyVGrid(columns: [GridItem(.adaptive(minimum: 85), spacing: 0)], spacing: 0) {
            ForEach(viewModel.cards) { card in
                CardView(card)
                    .aspectRatio(2/3, contentMode: .fit)
                    .padding()
            }
        }
        .foregroundColor(.cyan)
        }
    
}


struct CardView: View {
    
    let card: Game<String>.Card
    
    init(_ card: Game<String>.Card) {
        self.card = card
    }

    
    var body: some View {
        ZStack {
            let base = RoundedRectangle(cornerRadius: 12)
            Group {
                base.fill(.white)
                base.strokeBorder(lineWidth: 2)
                Text(card.content)
                    .font(.system(size: 200))
                    .minimumScaleFactor(0.01)
                    .aspectRatio(1, contentMode: .fit)
                
            }
            .opacity(card.isFaceUP ? 1 : 0)
            base.fill().opacity(card.isFaceUP ? 0 : 1)
        }
        .opacity(card.isFaceUP || !card.isMatched ? 1 : 0)
    }
}


#Preview {
    ContentView(viewModel: EmojiMemoryGame())
}
