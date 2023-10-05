package com.quasa.harmony.dataloaders

import com.quasa.harmony.generated.types.ProductReview
import com.quasa.harmony.services.ReviewsService
import com.netflix.graphql.dgs.DgsDataLoader
import org.dataloader.MappedBatchLoader
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
import kotlin.streams.toList

@DgsDataLoader(name = "reviews")
class ReviewsDataLoader(val reviewsService: ReviewsService): MappedBatchLoader<String, List<ProductReview>> {
    /**
     * This method will be called once, even if multiple datafetchers use the load() method on the DataLoader.
     * This way reviews can be loaded for all the Shows in a single call instead of per individual Show.
     */
    override fun load(keys: MutableSet<String>): CompletionStage<Map<String, List<ProductReview>>> {
        return CompletableFuture.supplyAsync { reviewsService.reviewsForProducts(keys.stream().toList()) }
    }

}