package com.aol.cyclops.types.stream;

import com.aol.cyclops.control.ReactiveSeq;

public interface HasStream<T> {
	ReactiveSeq<T> getStream();
}
