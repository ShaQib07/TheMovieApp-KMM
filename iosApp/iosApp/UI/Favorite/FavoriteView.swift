//
//  FavoriteView.swift
//  iosApp
//
//  Created by Golam Shakib Khan on 14/3/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct FavoriteView: View {
    @EnvironmentObject var viewModel: PopularViewModel
    
    var body: some View {
        ScrollView(.vertical, showsIndicators: false) {
            withAnimation {
                ForEach(viewModel.favoriteList, id: \.id) { movie in
                    NavigationLink(
                        destination: MovieDetailView(movie: movie),
                        label: {
                            PopularItemView(movie: movie)
                                .environmentObject(viewModel)
                                .padding(EdgeInsets(top: 0.0, leading: 10.0, bottom: 4.0, trailing: 10.0))
                        })
                }
            }
        }
        .navigationBarTitle("Favorite")
        .onAppear {
            viewModel.fetchFavoriteMovies()
        }
    }
}

struct Favorite_Previews: PreviewProvider {
    static var previews: some View {
        FavoriteView()
    }
}
