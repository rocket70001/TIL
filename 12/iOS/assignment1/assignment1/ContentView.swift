//
//  ContentView.swift
//  assignment1
//  CS193p 첫 과제, 1~2강에서 시연한 내용 재구현, 보강하기
//  Created by go on 12/10/23.
//

import SwiftUI


enum WhatEmojis: String {
    case sea,xmas,face
}

struct ContentView: View {
    let seaEmojis = ["🐢", "🐠", "🐡", "🐬", "🦭", "🦀", "🦈", "🐙", "🪼", "🐟", "🐳"]
    let xmasEmojis = ["⛪", "🎄", "🎅🏻", "🎅🏿", "🤶🏼", "❄️", "🛷", "🧦", "🎁", "🦌", "🔔"]
    let faceEmojis = ["😆", "😇", "🤩", "🥸", "🤓", "😘", "🫨", "🧐", "😅", "😋", "🥳"  ]
    let head = "Memorize!"
    @State var selectedEmojis = WhatEmojis.sea
    @State var presentEmojis = [""]
    @Binding var faceState: Bool
    
    var cards: some View {
        LazyVGrid(columns: [GridItem(.adaptive(minimum: 65))]) {
            ForEach(0..<presentEmojis.count, id: \.self) { index in
               CardView(content: presentEmojis[index])
                    .aspectRatio(2/3, contentMode: .fit)
                Toggle()
            }
        }
    }

    
    var body: some View {
        ScrollView {
            Text(head).font(.largeTitle)
            if selectedEmojis == .sea {
                cards.foregroundColor(.cyan)
            } else if selectedEmojis == .xmas {
                cards.foregroundColor(.red)
            }
            else {
                cards.foregroundColor(.yellow)
            }
            
        }
        .padding()
        HStack {
            Button(action: {
                selectedEmojis = WhatEmojis.sea
                presentEmojis = seaEmojis
            }, label: {
                VStack{
                    Text("🐬")
                    Text("Sea")
                        .padding(10)
                        .background(Capsule().strokeBorder())
                }
            }).padding().foregroundColor(.cyan)
            Button(action: {
                selectedEmojis = WhatEmojis.xmas
                presentEmojis = xmasEmojis
            }, label: {
                VStack{
                    Text("🎄")
                    Text("Christmas")
                        .padding(10)
                        .background(Capsule().strokeBorder())
                }
            }).padding().foregroundColor(.red)
            Button(action: {
                selectedEmojis = WhatEmojis.face
                presentEmojis = faceEmojis
            }, label: {
                VStack{
                    Text("🥸")
                    Text("Face")
                        .padding(10)
                        .background(Capsule().strokeBorder())
                }
            }).padding().foregroundColor(.orange)
        }
    }
    

}

//5. The face up or face down state of the cards does not need to change when the user
//changes the theme. -> 불변 객체의 속성 상태를 관리해라
struct CardView: View {
    var content: String
    @State var isFaceUp = true
    
    var body: some View {
        ZStack {
            let base = RoundedRectangle(cornerRadius: 12)
            Group {
                base.fill(.white)
                base.strokeBorder(lineWidth: 2)
                Text(content).font(.largeTitle)
            }
            .opacity(isFaceUp ? 1 : 0)
            base.fill().opacity(isFaceUp ? 0 : 1)
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
