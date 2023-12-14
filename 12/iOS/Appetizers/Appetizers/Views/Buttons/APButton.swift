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
            Text(title)
                .font(.title3)
                .fontWeight(.semibold)
                .frame(width: 260, height: 50)
                .foregroundColor(.white)
                .background(Color.brandP)
                .cornerRadius(10)
    }
}

#Preview {
    APButton(title: "Test Title")
}
