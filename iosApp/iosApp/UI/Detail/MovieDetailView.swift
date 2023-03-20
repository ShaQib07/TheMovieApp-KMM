//
//  MovieDetailView.swift
//  iosApp
//
//  Created by Golam Shakib Khan on 19/3/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import Kingfisher

struct MovieDetailView: View {
    @StateObject private var viewModel = MovieDetailViewModel()
    
    let movie: Movie
    
    var body: some View {
        ScrollView(.vertical, showsIndicators: false) {
            VStack(alignment: .leading) {
                ZStack {
                    KFImage(URL(string: movie.getPoster()))
                        .resizable()
                        .scaledToFit()
                    
                    Rectangle()
                        .opacity(0.0)
                        .frame(maxWidth: .infinity, maxHeight: .infinity)
                        .background(LinearGradient(gradient: Gradient(colors: [Color("Whitish"), Color.clear]), startPoint: .top, endPoint: .center))
                }
                
                Text(movie.title.uppercased())
                    .kerning(2)
                    .font(.title)
                    .fontWeight(.black)
                    .padding(.leading, 16.0)
                    .padding(.bottom, 2.0)
                
                Text(movie.overview)
                    .font(.subheadline)
                    .fontWeight(.light)
                    .multilineTextAlignment(.leading)
                    .padding(.horizontal, 12.0)
                
                Text("RECOMMENDED MOVIES")
                    .fontWeight(.black)
                    .font(.title3)
                    .padding(.leading, 16.0)
                    .padding(.top, 2.0)
                
                ScrollView(.horizontal, showsIndicators: false) {
                    HStack {
                        ForEach(viewModel.movieList, id: \.id) { movie in
                            RecommendedItemView(movie: movie)
                                .padding(EdgeInsets(top: 0.0, leading: 4.0, bottom: 8.0, trailing: 0.0))
                        }
                    }
                }
                .padding(.horizontal, 8.0)
            }
        }
        .navigationBarTitle(movie.title)
        .navigationBarTitleDisplayMode(.inline)
        .ignoresSafeArea(.all, edges: .top)
        .onAppear {
            Task {
                await viewModel.getRecommendations(id: movie.id)
            }
        }
    }
}

struct MovieDetail_Previews: PreviewProvider {
    static var previews: some View {
        MovieDetailView(movie: Movie())
    }
}
