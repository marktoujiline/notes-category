---
layout: docs
title: Product
permalink: docs/product/
---
# Product
A categorical product __candidate__ is an object c with morphisms p: c->a and q: c->b

{% graphviz %}
digraph {
    c -> a [label=p]
    c -> b [label=q]
}
{% endgraphviz %}

There can be many such candidates.

{% graphviz %}
digraph {
    c -> a [label=p]
    c -> b [label=q]
    "c1" -> a [label="p1"]
    "c1" -> b [label="q1"] 
}
{% endgraphviz %}

We want to rank the candidates to determine which is the product.

The product (if it exists) is the object with two projections AND a unique incoming morphism from every other categorical product with projections going to the same objects.

{% graphviz %}
digraph {
    c -> a [label=p]
    c -> b [label=q]
    "c1" -> a [label="p1"]
    "c1" -> b [label="q1"] 
    "c1" -> c [label=m]
}
{% endgraphviz %}

In the above exapmle, c is the product. It is unique up to unique isomorphism.

## Programming View
A categorical product is the equivalent of a tuple. In Scala, this can be reprsented by a case class:
```scala
type A = String
type B = Int
case class AuB(p: A, q: B)
```

The product class has projections going back to the other types:

```scala
val product = AuB("a", 1)
product.p
product.q
```

We can have other product candidates:
```scala
case class CandidateA(p1: A, q1: B, c: B)
case class CandidateB(p2: B, q2: A)
```

Candidate A can be transformed into our product with function m:
```scala
def m(candidate: CandidateA): AuB = AuB(candidate.p1, candidate.q1)
```

Same goes for candidateB, except the product can also be transformed into candidateB.
```scala
def f(candidate: CandidateB): AuB = AuB(candidate.q2, candidate.p2)
def g(product: AuB): CandidateB = CandidateB(product.q, product.p)

CandidateB(5, "a") == g(f(CandidateB(5, "a")))
```

This indicates that those two case classes are isomorphic; they contain the same information, just described differently.
Therefore we still have a unique product, but up to unique isomorphism.