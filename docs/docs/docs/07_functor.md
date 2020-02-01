---
layout: docs
title: Functor
permalink: docs/functor/
---
# Functor
A functor is a mapping between two categories that preserves structure.
This means that all objects and morphisms are mapped with composition preserved.

{% graphviz %}
digraph {
    subgraph cluster_0 {
        label=C
        a -> b [label=f]
        b -> c [label=g]
        a -> c [label="g.f"]
    }
    subgraph cluster_1 {
        label="C'"
        "a'" -> "b'" [label="f'"]
        "b'" -> "c'" [label="g'"]
        "a'" -> "c'" [label="g'.f'"]

        a -> "a'" [label="Fa"]
        b -> "b'" [label="Fb"]
        c -> "c'" [label="Fc"]
    }
}
{% endgraphviz %}

Since morphisms are mapped as well, that means that the functor applied to f: a->b must result in f': a'->b', F(f)

Functor laws:
1. Functors must preserve identity morphisms `fmap id = id`
2. Functors preserve composition of morphisms `fmap (f . g)  ==  fmap f . fmap g`

You can use equational reasoning to prove the laws.

## Programming View
You can think of a functor as a _function_. A type isn't a function, but a higher kinded type is since it takes a type as an argument. For example, Option can be viewed as a function that takes a type A and returns Option[A].

```scala
trait Functor[F[_]] {
    def fmap[A, B](a: A => B): F[A] => F[B]
}
```

An example implmentation is the Option functor:

```scala
val optionFunctor = new Functor[Option] {
    def fmap[A, B](f: A => B): Option[A] => Option[B] = {
        case None => None
        case Some(s) => Some(f(s))
    }
}
```

We can prove the functor laws with equational reasoning:
```scala
// 1. optionFunctor.fmap(id) == id
def id[A](a: A): A = a
// Two cases two prove, one for None, and one for Some

// None
optionFunctor.fmap(id)(None) == id(None)
((a: Option[A]) => None)(None) == id(None)
None == id(None)
id(None) == id(None)

// Some
optionFunctor.fmap(id)(Some(5)) == id(Some(5))
((a: Option[Int]) => a match {case Some(i) => Some(id(i))})(Some(5)) == id(Some(5))
Some(id(5)) == id(Some(5))
Some(5) == id(Some(5))
id(Some(5)) == id(Some(5))

// 2. fmap (f . g)  ==  fmap f . fmap g 
// Two cases two prove, one for None, and one for Some
val f: Int => String = (i: Int) => i.toString
val g: String => Boolean = (s: String) => s.length > 2
val fmap1 = optionFunctor.fmap[Int, String] _
val fmap2 = optionFunctor.fmap[String, Boolean] _
val fmap = optionFunctor.fmap[Int, Boolean] _

// None
(fmap (g compose f))(None) == (fmap2(g) compose fmap1(f))(None)
None == (fmap2(g) compose fmap1(f))(None)
(fmap2(g))(None) == (fmap2(g) compose fmap1(f))(None)
(fmap2(g))(fmap1(f)(None)) == (fmap2(g) compose fmap1(f))(None) 
(fmap2(g) compose fmap1(f))(None) == (fmap2(g) compose fmap1(f))(None)

// Some
(fmap (g compose f))(Some(5)) == (fmap2(g) compose fmap1(f))(Some(5))
//...
```