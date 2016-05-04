package br.com.loteria.app.infra;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;

public class DozerHelper {

	public static <T, U> List<U> map(final Mapper mapper,
			final Collection<T> source, final Class<U> destType) {

		final ArrayList<U> dest = new ArrayList<U>();

		if(source != null){
			for (T element : source) {
				if (element == null) {
					continue;
				}
				dest.add(mapper.map(element, destType));
			}
		}

		List<U> s1 = new ArrayList<U>();
		s1.add(null);
		dest.removeAll(s1);

		return dest;
	}

	public static <T, U> void map(final Mapper mapper, final T source, Collection<U> dest){
		if(dest != null){
			for(U iterator : dest)
				mapper.map(source, iterator);
		}
	}
}