[![Build Status](https://travis-ci.org/binig/geomatching.svg?branch=master)](https://travis-ci.org/binig/geomatching)
[![Coverage Status](https://img.shields.io/coveralls/binig/geomatching.svg)](https://coveralls.io/r/binig/geomatching)

geomatching
===========

you may not now how geo index work for 2d space well if u don't look here http://docs.mongodb.org/manual/core/geospatial-indexes/
With those index you can efficiently get all the data in a square.

Now we gonna try to get all the data that are in a n-dimensionnal cube and use the same principal as the geo index but in a space of
dimension n.
Why ?
Well first case you have a large pool of offer maybe mobile abonnement, services or even a dating site and you want to give
them the closest offer to what they want, not the exact offer because it may not exist but the closest.
For example i want a mobile abo with iphone 5 or better phone, for 40euro a month and 12 month period.
We can put each criteria on an axis even use some weight on the data and then just use a geo n dimension index to get the closest offer.

