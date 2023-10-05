package com.quasa.harmony.resolvers

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import graphql.schema.DataFetchingEnvironment
import org.springframework.context.annotation.ComponentScan

@DgsComponent
class JsonResolver {
    @DgsData(parentType = "YourType", field = "fieldWithJsonData")
    fun resolveSpecifications(dfe: DataFetchingEnvironment): Map<String, Any> {
        return mapOf("key" to "value")
    }
}