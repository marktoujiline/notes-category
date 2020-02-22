---
layout: docs
title: Functoriality
permalink: docs/functoriality/
---
# Functoriality

Functoriality is the ability for something to follow the functor laws.
For example, to prove a bifunctor is functorial, we need to show that it maps objects and morphisms and preserves structure.

A bifunctor can be seen as having a separate functor for each type argument (first, second).
In general, separate functoriality is __not__ enough to prove joint functoriality.
Categories in which joint functoriality fails are called premonoidal.

## Product Bifunctor

Product (`Tuple2` in Scala) is a bifunctor.
If the product exists for any pair of objects, the mapping from those objects to the product is bifunctorial.

## Coproduct Bifunctor

Coproduct (`Either` in Scala) is a bifunctor.
A coproduct, if it’s defined for every pair of objects in a cat- egory, is also a bifunctor.

## ADT Functoriality

ADTs come in 2 forms: parameterized and unparameterized.

```scala
// unparameterized
// algebra: 1 + 1 + 1
trait Color
case object Blue extends Color
case object Red extends Color
case object Yellow extends Color

// parameterized
// algebra: 1 + a
trait Option[A]
case object None extends Option[Nothing]
case class Some[A](a: A) extends Option[A]
```

A parameterixed ADT is the equivalent of an algebraic expression containing a variable.

Some parameterized ADTs also turn out to be functor: List, Option, etc.

Some others:
```scala
// Id Functor
type Id[A] = A

val idFunctor = new Functor[Id] {
    def fmap[A, B](f: A => B)(a: Id[A]): Id[B] = f(a)
}

// Const Functor
case class Const[C, A](v: C)
val constFunctor[C] = new Functor[Const[C, ?]] {
    def fmap[A, B](f: A => B)(ca: Const[C, A]): Const[C, B] = Const(ca.v) 
}
```
The const functor maps every object in a category C to a single object in another category.

{% graphviz %}
digraph {
    subgraph cluster_0 {
        label=C
        a
        b
        c
    }

    subgraph cluster_1 {
        label="Const(C)"
        a -> d
        b -> d
        c -> d
    }
}
{% endgraphviz %}

The identity functor is the simplest way of containing a value, akin to Some[A].

The const functor ignores the type parameter, akin to None.

Looking at Option again:
- None can be represented as Const(Unit) (functorial)
- Some can be represented as Id[A] (functorial)
- Sum of None and Some is functorial

Could have defined Option as:
```scala
type Option[A] = Either[Const[Unit, A], Id[A]]
```

Option is composition of bifunctor Either with two functors, Const and Id. Note that although Const is a bifunctor, it is being used partially.

Bifunctors compose just like functors compose.

Functors can be derived from algabraic data types.

## Covariant and Contravariant Functors
{% graphviz %}
digraph {
    subgraph cluster_0 {
        label=C
        b -> a [label=f]
    }

    subgraph cluster_1 {
        label=Cop
        "a'" -> "b'" [label=fop]
    }

    subgraph cluster_2 {
        label=D
        "a''" -> "b''" [label=Ffop]
        "a'" -> "a''" [label="Fa'"]
        "b'" -> "b''" [label="Fb'"]

        a -> "a''" [label="Ga'"]
        b -> "b''" [label="Gb'"] 
    }
}
{% endgraphviz %}

F is identical to G except it reverses the morphisms. A mapping of categories that inverts the direction of morphisms is a contravariant functor. A functor that preserves direction is a covariant functor.

```scala
trait Contravariant[F[_]] {
    def contramap[A, B](f: B => A)(fa: F[A]): F[B]
}
```

## Profunctor
A bifunctor whose first argument is contravariant and second argument is convariant is a profunctor. One such example is the Reader functor. 

## Hom-Functor
Mapping that:
- takes pair of objects a and b 
- assigns it to set of morphisms between them, home-set C(a,b)

is a functor, specifically a functor from product category Cop x C to __Set__.
Hom-functor is special case of profunctor.