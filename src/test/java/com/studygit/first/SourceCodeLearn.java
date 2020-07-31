package com.studygit.first;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * FileName: SourceCodeLearn.java
 * Author:   machao
 * Date:     2020/07/30 10:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */

public class SourceCodeLearn {
	public static void main(String[] args) {
		WeakReference<M> m = new WeakReference<>(new M());
		System.out.println(m.get());
		System.gc();
		System.out.println(m.get());
		
		ThreadLocal<M> t1 = new ThreadLocal<>();
		t1.set(new M());
		t1.remove();
		
		WeakHashMap<String,M> whm = new WeakHashMap<>();
		whm.put("", new M());
	}
	
}
class M{
	@Override
	public void finalize() {
		System.out.println("finalize");
	}
}
