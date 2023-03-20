//
//  MovieDetailViewModel.swift
//  iosApp
//
//  Created by Golam Shakib Khan on 19/3/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import KMPNativeCoroutinesAsync

class MovieDetailViewModel: ObservableObject {
    let useCase: UseCase = Koin.instance.get()
    
    @Published var movieList = [Movie]()
    
    func updateMovieList(movieList: [Movie]) {
        DispatchQueue.main.async { [weak self] in
            self?.movieList.append(contentsOf: movieList)
        }
    }
    
    func getRecommendations(id: Int32) async {
        do {
            let stream = asyncSequence(for: self.useCase.getRecommendations(id: id))
            for try await data in stream {
                if let recommendations = data.payload?.results {
                    self.updateMovieList(movieList: recommendations)
                }
            }
        } catch {
            MPLogger().e(tag: "getRecommendations", message: String(describing: error.localizedDescription))
        }
    }
}
