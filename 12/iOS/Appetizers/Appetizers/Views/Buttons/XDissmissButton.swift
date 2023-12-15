//
//  XDissmissButton.swift
//  Appetizers
//
//  Created by go on 12/13/23.
//
import SwiftUI

struct XDissmissButton: View {
    
    var body: some View {
        ZStack {
            Circle()
                .frame(width: 30, height: 30)
                .foregroundColor(.white)
//                .foregroundColor(.green)
                .opacity(0.6)
            
            Image(systemName: "xmark")
                .imageScale(.small)
                .frame(width: 44, height: 44)
                .foregroundStyle(.black)
        }
    }
    
}

#Preview {
    XDissmissButton()
}




//작성
//struct XDismissButton: View {
//    
//    @Binding var isShowingDetailView: Bool
//    
//    var body: some View {
//        HStack {
//            Spacer()
//            
//            Button {
//                isShowingDetailView = false
//            } label: {
//                Image(systemName: "xmark")
//                    .foregroundColor(Color(.label))
//                    .imageScale(.large)
//                    .frame(width: 44, height: 44)
//            }
//        }
//        .padding()
//    }
//}
//
//#Preview {
//    XDismissButton(isShowingDetailView: .constant(false))
//}
