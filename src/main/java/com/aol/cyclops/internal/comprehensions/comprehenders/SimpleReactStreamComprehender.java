package com.aol.cyclops.internal.comprehensions.comprehenders;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.aol.cyclops.control.SimpleReact;
import com.aol.cyclops.types.extensability.Comprehender;
import com.aol.cyclops.types.futurestream.BaseSimpleReactStream;
import com.aol.cyclops.types.futurestream.SimpleReactStream;
import com.aol.cyclops.util.stream.StreamUtils;

public class SimpleReactStreamComprehender implements Comprehender {

	public static int priority = 4;
	@Override
	public int priority(){
		return priority;
	}
	@Override
	public Object filter(Object t, Predicate p) {
		return ((SimpleReactStream)t).filter(p);
	}

	@Override
	public Object map(Object t, Function fn) {
		return ((SimpleReactStream)t).then(fn);
	}

	@Override
	public SimpleReactStream flatMap(Object in, Function fn) {
	    SimpleReactStream t = ((SimpleReactStream)in);

		return  SimpleReactStream.bind(t,input -> ( SimpleReactStream)unwrapOtherMonadTypes(t,this,fn.apply(input)));
	}

	@Override
	public SimpleReactStream of(Object o) {
	    return new SimpleReact().of(o);
	}
	public SimpleReactStream fromIterator(Iterator it){
        return new SimpleReact().fromIterable(()->it);
    }
	@Override
	public SimpleReactStream empty() {
	    return new SimpleReact().of();
	}

	@Override
	public Class getTargetClass() {
		return SimpleReactStream.class;
	}
	public Object resolveForCrossTypeFlatMap(Comprehender comp,SimpleReactStream apply){
		return comp.of(apply.block());
	}
	static SimpleReactStream unwrapOtherMonadTypes(SimpleReactStream t,Comprehender<SimpleReactStream> comp,Object apply){
		
		
		
		if(apply instanceof Collection){
			return t.fromStream(((Collection)apply).stream());
		}
		if(apply instanceof Iterable){
			 return t.fromStream(StreamSupport.stream(((Iterable)apply).spliterator(),
						false));
		}
		
		return Comprehender.unwrapOtherMonadTypes(comp,apply);
		
	}
}
