package com.quasa.harmony.scalars

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsRuntimeWiring
import graphql.scalars.ExtendedScalars
import graphql.schema.idl.RuntimeWiring

/**
 * graphql-java provides optional scalars in the graphql-java-extended-scalars library.
 * We can wire a scalar from this library by adding the scalar to the RuntimeWiring.
 */
@DgsComponent
class JsonScalarRegistration {
    @DgsRuntimeWiring
    fun addJsonScalar(builder: RuntimeWiring.Builder): RuntimeWiring.Builder {
        return builder.scalar(ExtendedScalars.Json)
    }
}