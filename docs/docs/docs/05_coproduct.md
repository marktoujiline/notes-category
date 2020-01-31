---
layout: docs
title: Coproduct
permalink: docs/coproduct/
---
# Coproduct
> The coproduct is the dual of product; simply inverse the arrows. It is the product in the opposite category.

A categorical coproduct __candidate__ is an object c with morphisms p: a->c and q: b->c

{% graphviz %}
digraph {
    a -> c [label=p]
    b -> c [label=q]
}
{% endgraphviz %}

There can be many such candidates.

{% graphviz %}
digraph {
    a -> c [label=p]
    b -> c [label="p1"]
    a -> "c1" [label=q]
    b -> "c1" [label="q1"] 
}
{% endgraphviz %}

We want to rank the candidates to determine which is the coproduct.

The coproduct (if it exists) is the object with two incoming morphisms AND a unique outgoing morphism to every other categorical coproduct with projections coming from the same objects.

{% graphviz %}
digraph {
    a -> c [label=p]
    b -> c [label="p1"]
    a -> "c1" [label=q]
    b -> "c1" [label="q1"] 
    c -> "c1" [label=m]
}
{% endgraphviz %}

In the above exapmle, c is the coproduct. It is unique up to unique isomorphism.

## Programming View
A categorical coproduct is the equivalent of the Either type.
```scala
type A = String
type B = Int
type Coproduct = Either[A, B]
```

The coproduct has incoming projections from the other types:

```scala
def p(a: A): Coproduct = Left(a)
def q(b: B): Coproduct = Right(b)
```

We can have other coproduct candidates:
```scala
type CandidateA = Either[Either[A, B], B]
type CandidateB = Either[B, A]
```

Candidate B can be created from our coproduct with function m:
```scala
val m: Coproduct => CandidateA = {
    case Left(l) = Left(Left(l))
    case Right(r) => Right(r)
}
```

Same goes for candidateB, except the coroduct can also be transformed into candidateB.
```scala
val f: CandidateB => Coproduct = {
    case Left(l) => Right(l)
    case Right(r) => Left(r)
}

val g: Coproduct => CandidateB = {
    case Left(l) => Right(l)
    case Right(r) => Left(r)
}
Right("a") == f(g(Right("a")))
```

This indicates that those two coproducts are isomorphic; they contain the same information, just described differently.
Therefore we still have a unique coproduct, but up to unique isomorphism.