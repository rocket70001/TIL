//
//  ViewController.swift
//  HelloSwiftProject
//
//  Created by go on 12/3/23.
//

import UIKit

class ViewController: UIViewController {
  
    
    @IBOutlet weak var valueLabel: UILabel!
    
    
    @IBOutlet weak var inputField: UITextField!
    
    
    @IBAction func showValue(_ sender: Any) {
        let name = inputField.text!
        valueLabel.text = "Hello, \(name)"
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }


}

