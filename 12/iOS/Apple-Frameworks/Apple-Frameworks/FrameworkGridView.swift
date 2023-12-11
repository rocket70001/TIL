//
//  FrameworkGridView.swift
//  Apple-Frameworks
//
//  Created by go on 12/11/23.
//

import SwiftUI

struct FrameworkGridView: View {
    var body: some View {
        FrameworkTitleView(name: "App-Clips", imageName: "app-clip")
    }
}

#Preview {
    FrameworkGridView()
}

struct FrameworkTitleView: View {
    
    let name: String
    let imageName: String
    
    var body: some View {
        VStack {
            Image(imageName)
                .resizable()
                .frame(width: 80, height: 80)
            Text(name)
                .font(.title2)
                .fontWeight(.semibold)
                .scaledToFit()
                .minimumScaleFactor(0.6)
        }
    }
}
