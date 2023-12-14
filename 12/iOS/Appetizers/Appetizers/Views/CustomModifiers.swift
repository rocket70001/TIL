//
//  CustomModifiers.swift
//  Appetizers
//
//  Created by go on 12/14/23.
//

import SwiftUI

struct StandardButtonStyle: ViewModifier {
    
    func body(content: Content) -> some View {
        content
            .buttonStyle(.bordered)
            .tint(.brandP)
            .controlSize(.large)
    }
}

extension View {
    
    func standardButtonStyle() -> some View {
        self.modifier(StandardButtonStyle())
    }
}
