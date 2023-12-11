//
//  ContentView.swift
//  SwiftUI-Weather
//
//  Created by go on 12/11/23.
//

import SwiftUI

struct ContentView: View {
    
    //뷰는 계속해서 생성되고 폐기되지만 @State는 데이터는 유지된다.
    //ContentView가 자식 뷰인 BackgroundView에서 바인딩된 isNight을 가져온다.
    @State private var isNight = false
    
    var body: some View {
        ZStack {
            //$isNight를 통해 바인딩된 isNight를 사용한다.
            BackgroundView(isNight: isNight)
            VStack {
                CityTextView(cityName: "Seoul, Kor")
                MainWeatherStatusView(imageName: isNight ? "moon.stars.fill" : "cloud.sun.fill", temperature: 22)
                
                HStack(spacing: 20) {
                    WeatherDayView(dayOfWeek: "TUE", imageName: "cloud.sun.fill", temperature: 22)
                    WeatherDayView(dayOfWeek: "WED", imageName: "sun.max.fill", temperature: 26)
                    WeatherDayView(dayOfWeek: "THU", imageName: "wind.snow", temperature: 20)
                    WeatherDayView(dayOfWeek: "FRI", imageName: "sunset.fill", temperature: 32)
                    WeatherDayView(dayOfWeek: "SAT", imageName: "snow", temperature: -3)
                }
                
                Spacer()
                Button {
                    isNight.toggle()
                    print("tapped")
                } label: {
                    WeatherButton(title: "Change Day Time", textColor: .white, backgroundColor: .mint)
                }
                
                Spacer()
            }
        }
    }
}

#Preview {
    ContentView()
}

struct WeatherDayView: View {
    
    var dayOfWeek: String
    var imageName: String
    var temperature: Int
    
    var body: some View {
        VStack {
            Text(dayOfWeek)
                .font(.system(size: 16, weight: .medium,
                              design: .default))
                .foregroundStyle(.white)
            Image(systemName: imageName)
                .symbolRenderingMode(.multicolor)
                .resizable()
//                .foregroundColor(.pink)
//                .foregroundStyle(.pink, .orange, .green)
                .aspectRatio(contentMode: .fit)
                .frame(width: 40, height: 40)
            Text("\(temperature)°")
                .font(.system(size: 28, weight: .medium))
                .foregroundStyle(.white)
        }
    }
}

struct BackgroundView: View {
    
    //@Binding 기호를 통해 ContentView의 isNight와 항상 바인딩된다.
//    @Binding var isNight: Bool
    var isNight: Bool
    
    var body: some View {
//        LinearGradient(gradient: Gradient(colors: [isNight ? .black : .blue, isNight ? .gray : Color("lightBlue")]), startPoint: .topLeading,
//                       endPoint: .bottomTrailing)
//        .ignoresSafeArea()
        ContainerRelativeShape()
            .fill(isNight ? Color.black.gradient : Color.blue.gradient)
            .ignoresSafeArea()
    }
}

struct CityTextView: View {
    
    var cityName: String
    
    var body: some View {
        Text(cityName)
            .font(.system(size: 32, weight: .medium, design: .default))
            .foregroundColor(.white)
            .padding()
    }
}

struct MainWeatherStatusView: View {
    
    var imageName: String
    var temperature: Int
    
    var body: some View {
        VStack(spacing: 10) {
            Image(systemName: imageName)
                .renderingMode(.original)
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: 180, height: 180)
            
            Text("\(temperature)°")
                .font(.system(size: 70, weight: .medium))
                .foregroundColor(.white)
        }
        .padding(.bottom, 40)
    }
}
