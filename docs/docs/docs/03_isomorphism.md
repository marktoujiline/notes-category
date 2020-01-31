---
layout: docs
title: Isomorphism
permalink: docs/isomorphism/
---
# Isomorphism
Any two objects a, b that have morphisms going to each other are said to be isomorphic.

{% graphviz %}
digraph {
    a:sw -> b:nw [label=f]
    b:ne -> a:se [label=g]
    a -> a [label=idA]
    b -> b [label=idB]
}
{% endgraphviz %}

Generally, a morphism is an isomorphism if there is another morphism which composed with results in identiy.
```
f o g = idB
g o f = idA
```

The existence of an isomorphism means that in some sense objects a and b are the same. Isomorphisms play an important role in universal constructions.

## Initial Object
An initial object is an object that has exactly 1 arrow going to every other object in category. This object is not guaranteed to exist. Here's what it could look like

{% graphviz %}
digraph {
    initial -> a [label=f]
    initial -> b [label=g]
    b -> c [label=h]
    initial -> c [label="h.g"]
}
{% endgraphviz %}

According to the above definition, there could be multiple such objects. But we can also prove that any 2 initial objects must also be isomorphic. If objects a and b are both initial, that means they must have morphisms to each other by definition of an initial object. That is also the definition of an isomorphism. 

Note that there can only be one such isomorphism because initial object restricts a single morphism from going to an object, including the identity.

If an initial object exists, it will be unique __up to unique ismorophism__.

## Terminal Object
Dual of initial object. An object which has exactly 1 incoming arrow from each object. Can be constructed by reversing all the arrows.

{% graphviz %}
digraph {
    a -> terminal [label=f]
    b -> terminal [label=g]
    c -> b [label=h]
    c -> terminal [label="g.h"]
}
{% endgraphviz %}

I renamed initial object to terminal, but they are actually still the same object. The initial object is the terminal object in the opposite category (category with arrows reverse) and vice versa.

Terminal object is also unique up to unique isomorphism.