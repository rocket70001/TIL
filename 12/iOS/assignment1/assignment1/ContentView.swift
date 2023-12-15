//
//  ContentView.swift
//  assignment1
//  CS193p 첫 과제, 1~2강에서 시연한 내용 재구현, 보강하기
//  Created by go on 12/10/23.
//

import SwiftUI


enum EmojiName: String {
    case sea = "Sea"
    case xmas = "Christmas"
    case face = "Face"
}

struct ContentView: View {
    let seaEmojis = ["🐢", "🐠", "🐡", "🐬", "🦭", "🦀", "🦈", "🐙", "🪼", "🐟", "🐳"]
    let xmasEmojis = ["⛪", "🎄", "🎅🏻", "🎅🏿", "🤶🏼", "❄️", "🛷", "🧦", "🎁", "🦌", "🔔", "🧸"]
    let faceEmojis = ["😆", "😇", "🤩", "🥸", "🤓", "😘", "🫨", "🧐", "😅", "😋", "🥳", "😮", "😐"]
    let head = "Memorize!"
    @State private var presentEmojis = [""]
    @State private var name = EmojiName.sea

    
    var body: some View {
        ZStack{
            Color(.whiteSmoke).ignoresSafeArea(.all)
            ScrollView {
                Text(head)
                    .font(.largeTitle)
                    .fontWeight(.semibold)
                    .padding()
                
                cards
                
                HStack {
                    Button(action: {
                        presentEmojis = setDeck(seaEmojis)
                        name = EmojiName.sea
                    }, label: {
                        CardButton(style: "water.waves", name: EmojiName.sea)
                    })
                    Button(action: {
                        presentEmojis = setDeck(xmasEmojis)
                        name = EmojiName.xmas
                    }, label: {
                        CardButton(style: "gift", name: EmojiName.xmas)
                    })
                    Button(action: {
                        presentEmojis = setDeck(faceEmojis)
                        name = EmojiName.face
                    }, label: {
                        CardButton(style: "face.smiling", name: EmojiName.face)
                    })
                }
            }
        }
    }
    
    var cards: some View {
        LazyVGrid(columns: [GridItem(.adaptive(minimum: 85))]) {
            ForEach(presentEmojis.indices, id: \.self) { index in
                CardView(content: presentEmojis[index], name: $name)
                    .aspectRatio(2/3, contentMode: .fit)
            }
        }
        .foregroundColor(name == EmojiName.sea ? .cyan : (name == EmojiName.xmas ? .red : .yellow))
        }
    
    func setDeck(_ present: [String]) -> [String] {
        var arr = present
        arr += arr
        arr.shuffle()
        return arr
    }
}


struct CardView: View {
    var content: String
    @State private var isFaceUp = false
    @Binding var name : EmojiName
    
    func getImage(name: EmojiName) -> String {
        switch name {
        case .sea:
            return "water.waves"
        case .xmas:
            return "gift"
        case .face:
            return "face.smiling"
        
        }
    }
    
    var body: some View {
        ZStack {
            let base = RoundedRectangle(cornerRadius: 12)
            Group {
                base
                    .fill(name == EmojiName.sea ? Color.cyan.gradient : (name == EmojiName.xmas ? Color.green.gradient : Color.yellow.gradient))
                base.strokeBorder(lineWidth: 2)
                Text(content).font(.system(size: 60))
                
            }
            .opacity(isFaceUp ? 1 : 0)
            base.fill().opacity(isFaceUp ? 0 : 1)
            Image(systemName: getImage(name: self.name))
                .resizable()
                .frame(alignment: .center)
                .aspectRatio(contentMode: .fit)
                .foregroundStyle(.cardBack)
                .opacity(isFaceUp ? 0 : 1)
            
        }
        .onTapGesture {
            isFaceUp.toggle()
            print("Tapped")
        }
    }
}


#Preview {
    ContentView()
}
