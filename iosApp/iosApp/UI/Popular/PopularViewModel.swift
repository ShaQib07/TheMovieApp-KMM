//
//  SplashViewModel.swift
//  iosApp
//
//  Created by Golam Shakib Khan on 3/3/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import KMPNativeCoroutinesAsync

class PopularViewModel : ObservableObject {
    let useCase: UseCase = Koin.instance.get()
    
    @Published var movieList = [Movie]()
    @Published var favoriteList = [Movie]()
    @Published var hasMore = true
    
    private var page = 1
    
    func updateMovieList(movieList: [Movie]) {
        DispatchQueue.main.async { [weak self] in
            self?.movieList.append(contentsOf: movieList)
            self?.page += 1
        }
    }
    
    func fetchPopularMovies() async {
        do {
            let stream = asyncSequence(for: self.useCase.getPopularMovies(page: Int32(page)))
            for try await data in stream {
                if let movieList = data.payload?.results {
                    updateMovieList(movieList: movieList)
                } else {
                    DispatchQueue.main.async { [weak self] in
                        self?.hasMore = false
                    }
                }
            }
        } catch {
            MPLogger().e(tag: "fetchPopularMovies", message: String(describing: error.localizedDescription))
        }
    }
    
    func fetchFavoriteMovies() {
        favoriteList = self.useCase.getFavoriteMovies()
    }
    
    func addToFavorite(movie: Movie) {
        self.useCase.addToFavorites(movie: movie)
        favoriteList.append(movie)
    }
    
    func removeFromFavorite(movie: Movie) {
        self.useCase.removeFromFavorites(movie: movie)
        if let index = favoriteList.firstIndex(of: movie) {
            favoriteList.remove(at: index)
        }
    }
}
