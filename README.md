## Nearest Aeroplane Finder

Small program which finds the nearest currently flying aeroplane to a set of given Lat-Long coordinates.
The program uses [OpenSky Network REST API](https://opensky-network.org/apidoc/rest.html) to get the currently
flying aeroplane information. 

The program has following dependencies: 
1. [Gson](https://github.com/google/gson) - Java library for encoding/decoding Java Objects into JSON and vice-versa.
2. [Unirest-Java](https://github.com/kong/unirest-java) - Lightweight HTTP client library used for API calls.