---
layout: docs
title: Writer Monad
permalink: docs/writer-monad/
---

# Writer Monad

## Functor
```scala
type Writer[A] = (A, String)

def >=>[A, B, C](m1: A => Writer[B], m2: B => Writer[C]): A => Writer[C] = a => {
    val (b, s1) = m1(a)
    val (c, s2) = m2(b)
    (c, s1 + s2)
}

// identity
def pure[A](x: A): Writer[A] = (x, "")

// fmap
def fmap[A, B](f: A => B): Writer[A] => Writer[B] = {
    /**
     * First argument of fish requires A => Writer[B]
     * id satisfies this since A can be any type, inluding the same as the return
     */
    val id: Writer[A] => Writer[A] = identity[Writer[A]] _
    
    /**
     * Applies function to get back B. pure is used to create a writer out of it.
     */
    val map: A => Writer[B] = x => pure(f(x))

    /**
     * Note that this doesn't append anything to the log because A => B was not a Writer function
     */
    >=>(id, map)
}
```