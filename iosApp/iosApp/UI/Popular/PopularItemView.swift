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
    @EnvironmentObject var viewModel: PopularViewModel
    @State var isFavorite = false
    
    var body: some View {
        ZStack {
            KFImage(URL(string: movie.getBackdrop()))
                .resizable()
                .scaledToFit()
                .cornerRadius(8.0)
            VStack {
                HStack {
                    Spacer()
                    Button(
                      action: {
                        isFavorite = !isFavorite
                        if(isFavorite) {
                            viewModel.addToFavorite(movie: movie)
                        }
                        else {
                            viewModel.removeFromFavorite(movie: movie)
                        }
                      }
                    ) {
                      Image(systemName: isFavorite ? "star.fill" : "star")
                        .font(.system(size: 20))
                        .foregroundColor(Color("Yellow"))
                        .shadow(color: Color.black, radius: 4)
                        .padding(8.0)
                    }
                }
                Spacer()
                Text(movie.title)
                    .font(.title3)
                    .fontWeight(.black)
                    .accentColor(Color.white)
                    .padding(8.0)
            }
            .background(LinearGradient(gradient: Gradient(colors: [Color("Gradient"), Color.clear]), startPoint: .bottom, endPoint: .center))
            .cornerRadius(8.0)
        }
        .onAppear {
            if viewModel.favoriteList.contains(movie) {
                isFavorite = true
            } else {
                isFavorite = false
            }
        }
    }
}

struct PopularItem_Previews: PreviewProvider {
    static var previews: some View {
        PopularItemView(movie: Movie())
    }
}
