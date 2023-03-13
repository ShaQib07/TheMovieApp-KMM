//
//  ItemView.swift
//  iosApp
//
//  Created by Golam Shakib Khan on 3/3/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI
import Kingfisher

struct PopularItemView: View {
    let movie: Movie
    
    var body: some View {
        ZStack {
            KFImage(URL(string: movie.getBackdrop()))
                .resizable()
                .scaledToFit()
                .cornerRadius(8.0)
            VStack {
                HStack {
                    Spacer()
                }
                Spacer()
                Text(movie.title)
                    .font(.title3)
                    .fontWeight(.black)
                    .accentColor(Color.white)
                    .padding(8.0)
            }
            .background(LinearGradient(gradient: Gradient(colors: [Color(#colorLiteral(red: 0.124825187, green: 0.1294132769, blue: 0.1380611062, alpha: 1)), Color.clear]), startPoint: .bottom, endPoint: .center))
            .cornerRadius(8.0)
        }
    }
}

struct PopularItem_Previews: PreviewProvider {
    static var previews: some View {
        PopularItemView(movie: Movie())
    }
}
