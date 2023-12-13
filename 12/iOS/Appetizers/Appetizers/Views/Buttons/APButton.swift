//
//  APButton.swift
//  Appetizers
//
//  Created by go on 12/13/23.
//

import SwiftUI

struct APButton: View {
    
    let title: LocalizedStringKey
    var body: some View {
        Button {
            print("tapped")
        } label: {
            Text(title)
                .font(.title3)
                .fontWeight(.semibold)
                .frame(width: 260, height: 50)
                .foregroundColor(.white)
                .background(Color.brandP)
                .cornerRadius(10)
        }
        .padding(.bottom, 30)
    }
}

#Preview {
    APButton(title: "Test Title")
}
