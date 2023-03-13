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
    let usecae: UseCase = Koin.instance.get()
    
    @Published var movieList = [Movie]()
    @Published var hasMore = true
    
    private var page = 1
    
    func updateMovieList(movieList: [Movie]) {
        DispatchQueue.main.async { [weak self] in
            self?.movieList.append(contentsOf: movieList)
            self?.page += 1
        }
    }
    
    func fetchPopularMovies() async {
        MPLogger().d(tag: "fetchPopularMovies", message: "Called with page \(page)")
        do {
            let stream = asyncSequence(for: self.usecae.getPopularMovies(page: Int32(page)))
            for try await data in stream {
                print("fetchPopularMovies | data from page \(page) - count \(String(describing: data.payload?.results.count))")
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
}
