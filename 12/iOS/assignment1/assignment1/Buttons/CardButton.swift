//
//  CardButtons.swift
//  assignment1
//
//  Created by go on 12/15/23.
//

import SwiftUI

struct CardButton: View {
    
    let style: String
    let name: EmojiName
    
    var body: some View {
        HStack{
            Image(systemName: "\(style)")
                .resizable()
                .frame(width: 23, height: 20)
                .aspectRatio(contentMode: .fit)
            Text("\(name.rawValue)")
                .bold()
        }
        .foregroundStyle(.white)
        .padding(10)
        .background(Capsule().strokeBorder()
            .fill(name == EmojiName.sea ? .cyan : (name == EmojiName.xmas ? .red : .orange)))
        .padding(10)
    }
}

#Preview {
    CardButton(style: "water.waves",name: EmojiName.sea)
}
