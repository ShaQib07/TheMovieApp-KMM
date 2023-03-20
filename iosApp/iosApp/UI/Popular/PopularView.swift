import SwiftUI
import shared

struct PopularView: View {
    @StateObject private var viewModel = PopularViewModel()
    
    var body: some View {
        NavigationView {
            ScrollView(.vertical, showsIndicators: false) {
                LazyVStack {
                    ForEach(viewModel.movieList, id: \.id) { movie in
                        NavigationLink(
                            destination: MovieDetailView(movie: movie),
                            label: {
                                PopularItemView(movie: movie)
                                    .environmentObject(viewModel)
                                    .padding(EdgeInsets(top: 0.0, leading: 10.0, bottom: 4.0, trailing: 10.0))
                            })
                    }
                    
                    if viewModel.hasMore {
                        ProgressView()
                            .onAppear {
                                Task {
                                    await viewModel.fetchPopularMovies()
                                }
                            }
                    }
                }
            }
            .onAppear {
                viewModel.fetchFavoriteMovies()
            }
            .navigationBarTitle("Popular")
                  .navigationBarItems(
                    trailing:
                        NavigationLink(destination: FavoriteView().environmentObject(viewModel), label: {
                        Image(systemName: "star.fill")
                              .foregroundColor(Color("Yellow"))
                      })
                  )
        }
        .accentColor(Color("BlackWhite"))
    }
}

struct Popular_Previews: PreviewProvider {
    static var previews: some View {
        PopularView()
    }
}
