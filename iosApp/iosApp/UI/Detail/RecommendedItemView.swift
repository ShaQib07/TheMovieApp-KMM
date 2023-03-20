//
//  RecommendedItemView.swift
//  iosApp
//
//  Created by Golam Shakib Khan on 19/3/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import Kingfisher

struct RecommendedItemView: View {
  let movie: Movie
  
  @State var isFavorite = false
  
  var body: some View {
    VStack {
      ZStack {
        KFImage(URL(string: movie.getPoster()))
          .resizable()
          .scaledToFit()
          .cornerRadius(8.0)
      }
      .frame(width: 200.0, height: 300.0)
      
      Text(movie.title)
        .lineLimit(1)
        .frame(width: 160.0)
        .padding(.horizontal)
    }
  }
}

struct RecommendedItem_Previews: PreviewProvider {
  static var previews: some View {
    RecommendedItemView(movie: Movie())
  }
}
