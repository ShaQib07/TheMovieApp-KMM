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
                            destination: EmptyView(),
                            label: {
                                PopularItemView(movie: movie)
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
            .navigationBarTitle("Popular")
            //      .navigationBarItems(
            //        trailing:
            //          NavigationLink(destination: FavoriteView(), label: {
            //            Image(systemName: "star.fill")
            //          })
            //      )
        }
    }
}

struct Popular_Previews: PreviewProvider {
    static var previews: some View {
        PopularView()
    }
}
