//
//  ContentView.swift
//  Appetizers
//
//  Created by go on 12/12/23.
//

import SwiftUI

struct AppetizerTabView: View {
    var body: some View {
        TabView {
            AppetizerListView()
                .tabItem {
                    Image(systemName: "house")
                    Text("Home")
                }
            AppetizerListView()
                .tabItem {
                    Image(systemName: "person")
                    Text("Account")
                }
            AppetizerListView()
                .tabItem {
                    Image(systemName: "bag")
                    Text("Order")
                }
            
        }
        .accentColor(Color("brandPrimary"))
    }
}

#Preview {
    AppetizerTabView()
}
