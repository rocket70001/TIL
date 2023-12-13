//
//  User.swift
//  Appetizers
//
//  Created by go on 12/13/23.
//

import Foundation

struct User: Codable {
    
    var firstName = ""
    var lastName = ""
    var email = ""
    var birthdate = Date()
    var extraNapkins = false
    var frequentRefills = false
    
}
