package com.quasa.harmony.datafetchers

import com.quasa.harmony.dataloaders.ProductsDataLoader
import com.quasa.harmony.generated.DgsConstants
import com.quasa.harmony.generated.types.ProductReview
import com.quasa.harmony.generated.types.Product
import com.quasa.harmony.generated.types.SubmittedReview
import com.quasa.harmony.services.ReviewsService
import com.netflix.graphql.dgs.*
import org.dataloader.DataLoader
import org.reactivestreams.Publisher
import java.util.concurrent.CompletableFuture

@DgsComponent
class ReviewDataFetcher(private val reviewsService: ReviewService) {
    @DgsData(parentType = DgsConstants.PRODUCT.TYPE_NAME, field = DgsConstants.PRODUCT.Reviews)
    fun reviews(dfe: DgsDataFetchingEnvironment): CompletableFuture<List<ProductReview>> {
        //Instead of loading a DataLoader by name, we can use the DgsDataFetchingEnvironment and pass in the DataLoader classname.
        val reviewsDataLoader: DataLoader<Int, List<ProductReview>> = dfe.getDataLoader(ReviewsDataLoader::class.java)

        // Because the reviews field is on Product, the getSource() method will return the Product instance
        val product: Product = dfe.getSource()

        // Load the reviews from the DataLoader. This call is async and will be batched by the DataLoader mechanism
        return reviewsDataLoader.load(product.id)
    }

    @DgsMutation
    fun addReview(@InputArgument review: SubmittedReview): List<ProductReview> {
        reviewsService.saveReview(review)

        return reviewsService.reviewsForShow(review.showId)?: emptyList()
    }

    @DgsSubscription
    fun reviewAdded(@InputArgument showId: Int): Publisher<ProductReview> {
        return reviewsService.getReviewsPublisher()
    }
}

