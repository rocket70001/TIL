//
//  AppetizerListViewModel.swift
//  Appetizers
//
//  Created by go on 12/13/23.
//

import SwiftUI

final class AppetizerListViewModel: ObservableObject {
    
    @Published var appetizers: [Appetizer] = []
    @Published var alertItem: AlertItem?
    @Published var isLoading = false
//작성    @Published var isShowingDetailView = false
    @Published var isShowingDetail = false
    @Published var selectedAppetizer: Appetizer?
    
//작성
//    var selectedAppetizer: Appetizer? {
//        didSet {isShowingDetailView = true}
//    }
    
    func getAppetizers() {
        isLoading = true
        NetworkManager.shared.getAppetizers {[self] result in
            DispatchQueue.main.async {
                self.isLoading = false
                switch result {
                case .success(let appetizers):
                    self.appetizers = appetizers
                case .failure(let error):
                    switch error {
                    case .invalidResponse:
                        alertItem = AlertContext.invalidResponse
                    case .invalidURL:
                        alertItem = AlertContext.invalidURL
                    case .invalidData:
                        alertItem = AlertContext.invalidData
                    case .unableToComplete:
                        alertItem = AlertContext.unableToComplete
                    }
                }
            }
        }
    }
}
