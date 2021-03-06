package com.aol.cyclops.react.lazy.sequenceM;

import org.jooq.lambda.Collectable;

import com.aol.cyclops.control.ReactiveSeq;
import com.aol.cyclops.react.lazy.sequenceM.CollectableTest;
import com.aol.cyclops.types.futurestream.LazyFutureStream;

public class LazyFutureStreamCollectableTest extends CollectableTest {
    @Override
    public <T> Collectable<T> of(T... values) {
       return LazyFutureStream.of(values).collectable();
    }

}
