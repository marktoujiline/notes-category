---
layout: docs
title: Monoid
permalink: docs/monoid/
---
# Monoid
A monoid is a set with:
- a value replresenting unit 
- a binary operator that's associative and whose result is also a member of the set

Example monoid:
- Set: Real numbers
- Unit: 0
- Op: Addition
- Associative: (a + b) + c == a + (b + c) = a + b + c

Example monoid:
- Set: Integers
- Unit: 1
- Op: Subtraction
- Associative: (a * b) * c == a * (b * c) == a * b * c
## Categorical View
Object with 1 category.

{% graphviz %}
digraph {
    label="C"
    a -> a [label=" f "]
    a -> a [label=" g "]
    a -> a [label=" g o f "]
}
{% endgraphviz %}

In the above category, Hom<sub>M</sub>(a, a) is the set of all morphisms from a to a. There's a set.

There is also a unit because a categorial object must contain an identity morphism. One of the morphisms in Hom<sub>M</sub>(a, a) must be idA.

The binary operator is composition. It takes two morphisms and produces a new morphisms. 

Morphisms in a category must be associative, therefore our operator is also associative.

## Programming View
```scala
trait Monoid[M] {
    def empty: M
    def combine(m1: M, m2: M): M
}

val listMonoid = new Monoid[List[Int]] {
    def empty: List()
    def combine(m1: List[Int], m2: List[Int]) = m1::m2
}
```
No way to programmaticaly enforce monoidal properties in trait.
Up to programmer to do implement correctly.