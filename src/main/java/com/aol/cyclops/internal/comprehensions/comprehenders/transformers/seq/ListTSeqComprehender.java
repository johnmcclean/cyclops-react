package com.aol.cyclops.internal.comprehensions.comprehenders.transformers.seq;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.aol.cyclops.control.monads.transformers.seq.ListTSeq;
import com.aol.cyclops.data.collections.extensions.standard.ListX;
import com.aol.cyclops.internal.comprehensions.comprehenders.MaterializedList;
import com.aol.cyclops.types.extensability.Comprehender;
import com.aol.cyclops.types.mixins.Printable;



public class ListTSeqComprehender implements Comprehender<ListTSeq>, Printable{
	
	@Override
	public Object resolveForCrossTypeFlatMap(Comprehender comp, ListTSeq apply) {
	    List list = (List) apply.stream().collect(Collectors.toCollection(MaterializedList::new));
		return list.size()>0 ? comp.of(list) : comp.empty();
	}
	@Override
    public Object filter(ListTSeq t, Predicate p){
        return t.filter(p);
    }
	@Override
	public Object map(ListTSeq t, Function fn) {
		return t.map(r->fn.apply(r));
	}

	@Override
	public Object flatMap(ListTSeq t, Function fn) {
		return t.flatMapT(r->fn.apply(r));
	}

	@Override
	public ListTSeq of(Object o) {
		return ListTSeq.of(ListX.of(o));
	}

	@Override
	public ListTSeq empty() {
		return ListTSeq.emptyList();
	}

	@Override
	public Class getTargetClass() {
		return ListTSeq.class;
	}
    @Override
    public ListTSeq fromIterator(Iterator o) {
        return ListTSeq.of(ListX.fromIterable(()->o));
    }

}
