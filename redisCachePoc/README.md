# Spring Cacheable with Redis
This is a proof of concept to use the cacheable Spring implementation with Redis.

It has just one controller, which receives a list of Longs, these are the ids for cards from the MTG API.

With that information the API uses Feign to send a request and get those cards information, and returns the id, the name, the set name and the description of the cards.

The method which sends the request to the MTG API is cached, so if it receives an id that is already in the cache, it returns the information from the cache and doesn't need to comunicate with the MTG API, making the response time of the method much faster.
