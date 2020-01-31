---
layout: docs
title: Category
permalink: docs/
---

# Category
## Essence
Cateogry theory is all about relationship patterns of entities.
The description is vague because the topic is abstract.
An important finding (Curry-Howard-Lembek Isomorphism) proves that formal logic, type systems, and category theory all describe the same idea in different ways.
This is useful as once an idea can be described in category theory, it can automatically be mapped to many other fields of math.

## Defintion
A category can be thought of as a directed graph, except where a node is referred to as an __object__ and an edge is called a __morphism__.

{% graphviz %}
digraph {
    object_a -> object_b [label="morphism_f"]
}
{% endgraphviz %}

We can say f is a morphism from a to b, or f: a->b. There are some important properties that have to be respected in order to for the graph to be considered a category.

__Every object must have a morphism going from itself to itself__

The moprhism for an object a is called __id<sub>a</sub>__, or identity morphism of object a.

{% graphviz %}
digraph {
    subgraph cluster_a {
        label="BAD"
        a -> b [label=f]
    }

    subgraph cluster_b {
        label="GOOD"
        "a1" -> "b1" [label="f1"]
        "a1" -> "a1" [label="idA"]
        "b1" -> "b1" [label="idB"]
    }
}
{% endgraphviz %}

__Morphisms must compose__

For morphisms f: a->b and g: b->c, there __must__ be a morphism g.f: a->c

{% graphviz %}
digraph {
    subgraph cluster_a {
        label="BAD"
        a -> b [label=f]
        b -> c [label=g]
    }

    subgraph cluster_b {
        label="GOOD"
        "a1" -> "b1" [label=f1]
        "b1" -> "c1" [label=g1]
        "a1" -> "c1" [label="g1.f1"]
    }
}
{% endgraphviz %}

__Morphism composition must be associative__

For morphisms f: a->b, g: b->c, h: c->d, it implies (h.g).f == h.(g.f) == h.g.f

This property cannot be proven from the graph alone.
Compsition must be defined for the category in order to test associativity. The implication is also that to define a category, you must define composition.

__Moprhism composition with identity results in original morphism__

For morphisms f: a->b, idA: a->a, idB: b->b it implies idB.f == f.idA == f

Similar to the previous condition, it can only be evaluated using the composition definition, not graphically.
