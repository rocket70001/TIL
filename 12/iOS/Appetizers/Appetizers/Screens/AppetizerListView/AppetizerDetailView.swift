//
//  AppetizerDetailView.swift
//  Appetizers
//
//  Created by go on 12/13/23.
//

import SwiftUI

struct AppetizerDetailView: View {
    
    @EnvironmentObject var order: Order
    
    let appetizer: Appetizer
    @Binding var isShowingDetail: Bool
    
    var body: some View {
        VStack {
            AppetizerRemoteImage(urlString: appetizer.imageURL)
                .aspectRatio(contentMode: .fit)
                .frame(width: 320, height: 225)
            
            VStack {
                Text(appetizer.name)
                    .font(.title2)
                    .fontWeight(.semibold)
                
                Text(appetizer.description)
                    .multilineTextAlignment(.center)
                    .font(.body)
                    .padding()
                
                HStack(spacing: 40) {
                    NutritionInfo(title: "Calories", value: "\(appetizer.calories)cal")
                    NutritionInfo(title: "Carbs", value: "\(appetizer.carbs)g")
                    NutritionInfo(title: "Protein", value: "\(appetizer.protein)g")
                }
            }
            
            Spacer()
            
            Button {
                order.add(appetizer)
                isShowingDetail = false
            } label: {
//                APButton(title: "$\(appetizer.price, specifier: "%.2f") - Add to Order")
                Text("$\(appetizer.price, specifier: "%.2f") - Add to Order")
            }
            .modifier(StandardButtonStyle())
//            .standardButtonStyle()
//            .buttonStyle(.bordered)
//            .tint(.brandP)
//            .controlSize(.large)
            .padding(.bottom, 30)
            
        }
        .frame(width: 300, height: 525)
        .background(Color(.systemBackground))
        .cornerRadius(12)
        .shadow(radius: 40)
        .overlay(Button {
            isShowingDetail = false
        } label: {
           XDissmissButton()
        }, alignment: .topTrailing)
    }
}

#Preview {
    AppetizerDetailView(appetizer: MockData.sampleAppetizer, isShowingDetail: .constant(true))
}

struct NutritionInfo: View {
    
    let title: String
    let value: String
    
    var body: some View {
        VStack(spacing: 5) {
            Text(title)
                .font(.caption)
                .fontWeight(.bold)
            
            Text("\(value)")
                .foregroundStyle(.gray)
                .fontWeight(.semibold)
                .italic()
        }
    }
    
}

//작성한 코드
//struct AppetizerDetailView : View {
//    
//    let appetizer: Appetizer
//    @Binding var isShowingDetailView: Bool
//    @State private var isAddedToCart = false
//    
//    var body: some View {
//        VStack {
//            ZStack{
//                AppetizerRemoteImage(urlString: appetizer.imageURL)
//                    .frame(width: 300, height: 250)
//                    .aspectRatio(contentMode: /*@START_MENU_TOKEN@*/.fill/*@END_MENU_TOKEN@*/)
//                XDismissButton(isShowingDetailView: $isShowingDetailView)
//                    .frame(width:50, height: 50)
//                    .cornerRadius(25)
//                    .background(Color.white)
//            }
//            Spacer()
//            Text(appetizer.name)
//                .font(.title)
//                .fontWeight(.semibold)
//            Spacer()
//            Text(appetizer.description)
//                .font(.caption)
//                .fontWeight(.light)
//            HStack {
//                VStack{
//                    Text("Calories")
//                        .font(.headline)
//                        .fontWeight(.semibold)
//                    Text(String(appetizer.calories))
//                        .font(.caption)
//                        .foregroundColor(.gray)
//                        .padding()
//                }
//                VStack{
//                    Text("Carbs")
//                        .font(.headline)
//                        .fontWeight(.semibold)
//                    Text("\(appetizer.carbs)g")
//                        .font(.caption)
//                        .foregroundColor(.gray)
//                        .padding()
//                }
//                VStack{
//                    Text("Protein")
//                        .font(.headline)
//                        .fontWeight(.semibold)
//                    Text("\(appetizer.protein)g")
//                        .font(.caption)
//                        .foregroundColor(.gray)
//                        .padding()
//                }
//                
//            }
//            Button {
//                isAddedToCart = true
//            } label: {
//                Text("$\(appetizer.price, specifier: "%2.f") - Add To Order")
//                    .font(.headline)
//                    .foregroundStyle(.white)
//                    .frame(width: 260, height: 35)
//                    .background(Color.brandP)
//            }
//            .cornerRadius(8)
//            
//            Spacer()
//        }
//        .frame(width: 300, height: 500)
//        .background(Color.white, alignment: .center)
//        .cornerRadius(8)
//    }
//}
//
